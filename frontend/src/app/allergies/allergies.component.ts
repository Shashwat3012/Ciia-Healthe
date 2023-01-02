import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { FormGroup, FormControl } from '@angular/forms';

export interface AllergiesData{
  allergicTo: string;
  symptoms: string;
  medicine: string;
  file: string;
}

@Component({
  selector: 'app-allergies',
  templateUrl: './allergies.component.html',
  styleUrls: ['./allergies.component.css']
})
export class AllergiesComponent implements OnInit {
  displayedColumns: string[] = ['allergicTo', 'symptoms', 'medicine', 'file'];
  dataSource!: MatTableDataSource<AllergiesData>;

  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {
  }
  openDialog() {
    this.dialog.open(AllergiesDataDialogComponent, {
      height: '600px',
      width: '600px',
    });
    // this.allergiesData();
  }

}

@Component({
  templateUrl: './allergies-data-dialog.component.html',
  styleUrls: ['./allergies.component.css']
})
export class AllergiesDataDialogComponent implements OnInit{
  dialog: any;
  dialogRef: any;
  userService: any;
  _snackbar: any;
  form: FormGroup = new FormGroup({
    allergicTo: new FormControl(''),
    symptoms: new FormControl(''),
    medicine: new FormControl(''),
    file: new FormControl('')
  });
  // Variable to store shortLink from api response
  // shortLink: string = "";
  // loading: boolean = false; // Flag variable
  // file: File = null; // Variable to store file
  // fileUploadService: any;

  constructor(){}
  ngOnInit(): void {
   console.log("")
  }

  // onChange(event: { target: { files: File[]; }; }) {
  //   this.file = event.target.files[0];
  // }

  // onUpload() {
  //   this.loading = !this.loading;
  //   console.log(this.file);
  //   this.fileUploadService.upload(this.file).subscribe(
  //       (event: any) => {
  //           if (typeof (event) === 'object') {

  //               // Short link via api response
  //               this.shortLink = event.link;

  //               this.loading = false; // Flag variable 
  //           }
  //       }
  //   );
  // }

  submit() {
    const user = {
      patientName: this.form.get('firstName')!.value + " " + this.form.get('lastName')!.value,
      patientId: sessionStorage.getItem("userId"),
    }
    this.userService.submitPatientData(user).subscribe(() => {
      this._snackbar.open("Successfully submitted info", "Close", {
        duration: 1500,

      });
      this.dialogRef.close();
    })
  }

  close() {
    this.dialogRef.close();
  }

}


