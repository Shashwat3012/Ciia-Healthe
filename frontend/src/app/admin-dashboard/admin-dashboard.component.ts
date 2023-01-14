import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { UserService } from '../services/user.service';

export interface DoctorInfo {
  uuid: string;
  license: string;
  status: string;
  hospital: string;
}

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {
  displayedColumns: string[] = ['uuid', 'license', 'hospital','status', 'action'];
  dataSource!: MatTableDataSource<DoctorInfo>;

  constructor(private userService: UserService,private _snackbar:MatSnackBar) { }

  ngOnInit(): void {
    this.fetchAllDoctors();
  }
  fetchAllDoctors() {
    this.userService.fetchAllDoctors().subscribe((response) => { 
      console.log(response);
      this.dataSource = response;
    })
  }
  updateStatus(doctorId: string,) {
    this.userService.updateDoctorUser(doctorId).subscribe((response) => {
      this._snackbar.open("Successfully Updated Status", "Close", {
        duration: 1500,
      });
    })
    this.fetchAllDoctors();
  }
}

@Component({
  templateUrl: './view-admin-dashboard-dialog.component.html',
  styleUrls: ['./admin-dashboard.component.css'],
})
export class viewAdminDetails implements OnInit{
  form: FormGroup = new FormGroup({
    birth_Date: new FormControl(''),
    gender: new FormControl(''),
    doctor: new FormControl(''),
    hospital: new FormControl(''),
  });

  constructor(
    public dialogRef: MatDialogRef<viewAdminDetails>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private userService: UserService,
    private _snackbar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.form.get('birth_Date')!.setValue(this.data.birth_Date);
    this.form.get('gender')!.setValue(this.data.gender);
    this.form.get('doctor')!.setValue(this.data.doctor);
    this.form.get('hospital')!.setValue(this.data.hospital);

    //  this.form.get('start_Date')!.disable();
    //  this.form.get('end_Date')!.disable();
  }

  editInfo() {
    const user = {
      patientId: sessionStorage.getItem('userId'),
      birth_Date: this.form.get('birth_Date')!.value,
      gender: this.form.get('gender')!.value,
      doctor: this.form.get('doctor')!.value,
      hospital: this.form.get('hospital')!.value,
    };
    this.userService.saveAdminData(user).subscribe((response) => {
      this._snackbar.open(response, 'Close', {
        duration: 1500,
      });
      this.dialogRef.close();
    });
  }

  close() {
    this.dialogRef.close();
  }


}
