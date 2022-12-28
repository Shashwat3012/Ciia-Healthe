import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';

export interface MedicationData {
  disease: string;
  medicine: string;
  doctor: string;
  startDate: string;
  endDate: string;
  file: string;
}

@Component({
  selector: 'app-medication',
  templateUrl: './medication.component.html',
  styleUrls: ['./medication.component.css']
})
export class MedicationComponent implements OnInit {
  displayedColumns: string[] = ['disease', 'medicine', 'doctor', 'startDate', 'endDate', 'file'];
  dataSource!: MatTableDataSource<MedicationData>;
  
  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {
  }

  openDialog() {
    this.dialog.open(MedicationDataDialogComponent, {
      height: '600px',
      width: '600px',
    });
    // this.medicationData();
  }
  // medicationData() {

  // }
}

@Component({
  templateUrl: './medication-data-dialog.component.html',
  styleUrls: ['./medication.component.css']
})
export class MedicationDataDialogComponent implements OnInit{
  dialog: any;
  dialogRef: any;
  userService: any;
  _snackbar: any;
  form: FormGroup = new FormGroup({
    disease: new FormControl(''),
    medicine: new FormControl(''),
    doctor: new FormControl(''),
    startDate: new FormControl(''),
    endDate: new FormControl(''),
    reports: new FormControl(''),
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
