import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup,Validators,ReactiveFormsModule } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  roles = ['Patient', 'Doctor'];
  constructor(private router: Router, private userService: UserService,
    private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
  }
  form: FormGroup = new FormGroup({
    username: new FormControl('',[Validators.required,Validators.pattern('[a-zA-z0-9]+$')]),
    password: new FormControl('',[Validators.required,Validators.pattern('[a-zA-z0-9]+$')]),
    role: new FormControl('',[Validators.required]),
    patientId: new FormControl('',[Validators.required,Validators.pattern('[a-zA-z0-9]+$')]),
    nomineeName: new FormControl('',[Validators.required,Validators.pattern('[a-zA-z]+$')]),
    license: new FormControl('',[Validators.required,Validators.pattern('[0-9]+$')])
  });

  submit() {
    if (this.form.get('role')!.value != 'Admin') {
      const user = {
        username: this.form.get('username')!.value || "",
        password: this.form.get('password')!.value || "",
        role: this.form.get('role')!.value || "",
        patientId: this.form.get('patientId')!.value || "",
        nomineeName: this.form.get('nomineeName')!.value || "",
        license: this.form.get('license')!.value || ""
      }
      this.userService.login(user).subscribe((response) => {

        if (user.role == "Patient") {
          if(this.username?.valid && this.password?.valid ){
          sessionStorage.setItem("userId",response)
          sessionStorage.setItem("role", user.role);
          this.router.navigate(['/home']);
          this._snackBar.open("Login Successful", "Close", {
            duration: 1500,
          });
        }
        }
        if (user.role == "Doctor") {
          if(this.username?.valid && this.password?.valid && this.license?.valid){ 
          if (response == "User Not Found!") {
            this._snackBar.open("User Not Found!", "Close", {
              duration: 1500,
            });
          } 
          if(response == "Status Pending"){
            this._snackBar.open("Registration not approved", "Close", {
              duration: 1500,
            });
          }
          else {
            this._snackBar.open("Login Successful", "Close", {
              duration: 1500,
            });
            sessionStorage.setItem("userId", response);
            sessionStorage.setItem("role", user.role);
            this.router.navigate(['/doctor-dashboard'])
          }

        }
        if (user.role == "Nominee") {
          if(this.patientId?.valid && this.nomineeName?.valid){
          this._snackBar.open("Login Successful", "Close", {
            duration: 1500,
          });
          sessionStorage.setItem("userId", user.patientId);
          sessionStorage.setItem("role", user.role);
          this.router.navigate(['/nominee-dashboard']);
        }
      }
      }
      })
    } else {
      if(this.form.get('username')!.value =="Admin" && this.form.get('password')!.value == "12345678"  ){
     
      sessionStorage.setItem("role", "Admin");
      
      this._snackBar.open("Login Successful", "Close", {
        duration: 1500,
      });
      this.router.navigate(['/admin-dashboard']);
    }
  }
    //  console.log()
  }

  register() {
    this.router.navigate(['/register']);
  }

get username()
{
  
  return this.form.get('username');
}

get password()
{
  return this.form.get('password');
}
get role()
{
  return this.form.get('role');
}
get patientId()
{
  return this.form.get('patientId');
}
get nomineeName()
{
  return this.form.get('nomineeName');
}
get license()
{
  return this.form.get('license');
}
}
