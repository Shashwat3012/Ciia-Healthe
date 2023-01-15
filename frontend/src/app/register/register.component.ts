import { invalid } from '@angular/compiler/src/render3/view/util';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl,Validators} from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  template: '<button ejs-button [disabled]="true">Disabled</button>'
})
export class RegisterComponent implements OnInit {

  constructor(private userService: UserService, private router: Router,
    private _snackbar: MatSnackBar) { }

  ngOnInit(): void {
  }

  form: FormGroup = new FormGroup({
    firstName: new FormControl('',[Validators.required,Validators.pattern('[a-zA-z]+$')]),
    lastName: new FormControl('',[Validators.required,Validators.pattern('[a-zA-z]+$')]),
    username: new FormControl('',[Validators.required,Validators.pattern('[a-zA-z0-9]+$')]),
    password: new FormControl('',[Validators.required,Validators.pattern('[a-zA-z0-9]+$')]),
    role: new FormControl('',[Validators.required]),
    license: new FormControl('',[Validators.required,Validators.pattern('[0-9]+$')]),
    hospital: new FormControl('',[Validators.required,Validators.pattern('[a-zA-z0-9]+$')])
  });

  submit() {
    const user = {
      firstName: this.form.get('firstName')!.value,
      lastName: this.form.get('lastName')!.value,
      userName: this.form.get('username')!.value,
      password: this.form.get('password')!.value,
      role: this.form.get('role')!.value,
      license: this.form.get('license')!.value,
      hospital: this.form.get('hospital')!.value,
      status: "Pending"
    }
    
  
    if (this.form.get('role')!.value == 'Patient') {
      if(this.firstName?.valid && this.lastName?.valid && this.username?.valid && this.password?.valid)
      {
        this.userService.register(user).subscribe((response) => {
          this._snackbar.open("Register Successful", "Close", {
            duration: 1500,
          });
        })
      }
     
    }
    else if(this.form.get('role')!.value == 'Doctor')
    {
      if(this.firstName?.valid && this.lastName?.valid && this.username?.valid && this.password?.valid && this.hospital?.valid && this.license?.valid)
      {
        this.userService.register(user).subscribe((response) => {
          this._snackbar.open("Register Successful", "Close", {
            duration: 1500,
          });
        })
      }
      
    }    
    
  }

  login() {
    this.router.navigate(['/login']);
  }
  get firstName()
{
  return this.form.get('firstName');
}
get lastName()
{
   return this.form.get('lastName');
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
get license()
{
  return this.form.get('license');
}
get hospital()
{
   return this.form.get('username');
}
}
