import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import {
  MatDialog,
  MatDialogRef,
  MAT_DIALOG_DATA,
} from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { UserService } from '../services/user.service';

export interface MedicationData {
  disease: string;
  medicine: string;
  doctor: string;
  start_Date: string;
  end_Date: string;
  file: string;
}

@Component({
  selector: 'app-medication',
  templateUrl: './medication.component.html',
  styleUrls: ['./medication.component.css'],
})
export class MedicationComponent implements OnInit {
  constructor(
    public dialog: MatDialog,
    private userService: UserService,
    private _snackbar: MatSnackBar
  ) {}

  displayedColumns: string[] = [
    'disease',
    'medicine',
    'doctor',
    'start_Date',
    'end_Date',
    'file',
  ];
  dataSource!: MatTableDataSource<MedicationData>;
  patientId = '';
  ngOnInit() {
    this.patientId = sessionStorage.getItem('userId') || '';
    this.getMedicationData();
  }

  getMedicationData() {
    this.userService.getMedicationData(this.patientId).subscribe((response) => {
      console.log(response);
      this.dataSource = response;
    });
  }

  openDialog() {
    this.dialog.open(MedicationDataDialogComponent, {
      height: '600px',
      width: '600px',
      
    });
  }

  viewMedicationData(element: MedicationData) {
    // this.userService.fetchMedication(this.patientId).subscribe((response) => {
      this.dialog.open(viewMedicationDetails, {
        height: '600px',
        width: '600px',
        data: element,
      });
    // });
  }
}

@Component({
  templateUrl: './viewMedication-data-dialog.html',
  styleUrls: ['./medication.component.css'],
})
export class viewMedicationDetails implements OnInit {
  // dialog: any;
  // dialogRef: any;
  // userService: any;
  // _snackbar: any;
  form: FormGroup = new FormGroup({
    disease: new FormControl(''),
    medicine: new FormControl(''),
    doctor: new FormControl(''),
    start_Date: new FormControl(''),
    end_Date: new FormControl(''),
    reports: new FormControl(''),
  });
  // Variable to store shortLink from api response
  // shortLink: string = "";
  // loading: boolean = false; // Flag variable
  // file: File = null; // Variable to store file
  // fileUploadService: any;

  constructor(
    public dialogRef: MatDialogRef<viewMedicationDetails>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private userService: UserService,
    private _snackbar: MatSnackBar
  ) {}
  ngOnInit(): void {
    this.form.get('disease')!.setValue(this.data.disease);
    this.form.get('medicine')!.setValue(this.data.medicine);
    this.form.get('doctor')!.setValue(this.data.doctor);
    this.form.get('start_Date')!.setValue(this.data.start_Date);
    this.form.get('end_Date')!.setValue(this.data.end_Date);
    this.form.get('reports')!.setValue(this.data.reports);

    //  this.form.get('start_Date')!.disable();
    //  this.form.get('end_Date')!.disable();
  }

  editInfo() {
    const user = {
      patientId: sessionStorage.getItem('userId'),
      disease: this.form.get('disease')!.value,
      medicine: this.form.get('medicine')!.value,
      doctor: this.form.get('doctor')!.value,
      start_Date: this.form.get('start_Date')!.value,
      end_Date: this.form.get('end_Date')!.value,
      reports: this.form.get('reports')!.value,
    };
    this.userService.editMedicationData(user).subscribe((response) => {
      this._snackbar.open(response, 'Close', {
        duration: 1500,
      });
      this.dialogRef.close();
    });
  }

  close() {
    this.dialogRef.close();
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
}

@Component({
  selector: 'app-medication',
  templateUrl: './medication-data-dialog.component.html',
  styleUrls: ['./medication.component.css'],
})
export class MedicationDataDialogComponent implements OnInit {
  constructor(
    public dialogRef: MatDialogRef<MedicationDataDialogComponent>,
    private userService: UserService,
    private _snackbar: MatSnackBar,
  ) {}

  
  form: FormGroup = new FormGroup({
    disease: new FormControl(''),
    medicine: new FormControl(''),
    doctor: new FormControl(''),
    start_Date: new FormControl(''),
    end_Date: new FormControl(''),
    reports: new FormControl(''),
  });

  ngOnInit(): void {
  }

  submit() {
    const user = {
      patientId: sessionStorage.getItem('userId'),
      disease: this.form.get('disease')!.value,
      medicine: this.form.get('medicine')!.value,
      doctor: this.form.get('doctor')!.value,
      start_Date: this.form.get('start_Date')!.value,
      end_Date: this.form.get('end_Date')!.value,
      reports: this.form.get('reports')!.value,
    };
    this.userService.submitMedicationData(user).subscribe((response) => {
      this._snackbar.open('Successfully submitted info', 'Close', {
        duration: 1500,
      });
      this.dialogRef.close();
    });
  }
  close() {
    this.dialogRef.close();
  }
}
