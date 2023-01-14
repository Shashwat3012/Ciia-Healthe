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

export interface AllergiesData {
  allergic_To: string;
  symptoms: string;
  medicine: string;
  file: string;
}

@Component({
  selector: 'app-allergies',
  templateUrl: './allergies.component.html',
  styleUrls: ['./allergies.component.css'],
})
export class AllergiesComponent implements OnInit {
  constructor(
    public dialog: MatDialog,
    private userService: UserService,
    private _snackbar: MatSnackBar
  ) {}

  displayedColumns: string[] = ['allergic_To', 'symptoms', 'medicine', 'file'];
  dataSource!: MatTableDataSource<AllergiesData>;
  patientId = '';
  ngOnInit() {
    this.patientId = sessionStorage.getItem('userId') || '';
    this.getAllergiesData();
  }
  getAllergiesData() {
    this.userService.getAllergiesData(this.patientId).subscribe((response) => {
      console.log(response);
      this.dataSource = response;
    });
  }
  
  openDialog() {
    this.dialog.open(AllergiesDataDialogComponent, {
      height: '600px',
      width: '600px',
    });
  }

  viewAllergiesData(element: AllergiesData) {
    this.dialog.open(viewAllergiesDetails, {
      height: '600px',
      width: '600px',
      data: element,
    });
  }
}

@Component({
  templateUrl: './viewAllergies-data-dialog.html',
  styleUrls: ['./allergies.component.css'],
})
export class viewAllergiesDetails implements OnInit {
  form: FormGroup = new FormGroup({
    allergic_To: new FormControl(''),
    symptoms: new FormControl(''),
    medicine: new FormControl(''),
    file: new FormControl(''),
  });

  constructor(
    public dialogRef: MatDialogRef<viewAllergiesDetails>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private userService: UserService,
    private _snackbar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.form.get('allergic_To')!.setValue(this.data.allergic_To);
    this.form.get('symptoms')!.setValue(this.data.symptoms);
    this.form.get('medicine')!.setValue(this.data.medicine);
    this.form.get('file')!.setValue(this.data.file);
  }

  editInfo() {
    const user = {
      patientId: sessionStorage.getItem('userId'),
      allergicTo: this.form.get('allergic_To')!.value,
      symptoms: this.form.get('symptoms')!.value,
      medicine: this.form.get('medicine')!.value,
      file: this.form.get('file')!.value,
    };
    this.userService.editAllergiesData(user).subscribe((response) => {
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

@Component({
  selector: 'app-allergies',
  templateUrl: './allergies-data-dialog.component.html',
  styleUrls: ['./allergies.component.css'],
})
export class AllergiesDataDialogComponent implements OnInit {
  // dialog: any;
  // dialogRef: any;
  // userService: any;
  // _snackbar: any;
  
  // Variable to store shortLink from api response
  // shortLink: string = "";
  // loading: boolean = false; // Flag variable
  // file: File = null; // Variable to store file
  // fileUploadService: any;

  constructor(public dialogRef: MatDialogRef<AllergiesDataDialogComponent>,
    private userService: UserService,
    private _snackbar: MatSnackBar,) {}

  form: FormGroup = new FormGroup({
    allergic_To: new FormControl(''),
    symptoms: new FormControl(''),
    medicine: new FormControl(''),
    file: new FormControl(''),
  });

  ngOnInit(): void {
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
      patientId: sessionStorage.getItem('userId'),
      allergic_To: this.form.get('allergic_To')!.value,
      symptoms: this.form.get('symptoms')!.value,
      medicine: this.form.get('medicine')!.value,
      file: this.form.get('file')!.value,
    };
    this.userService.submitAllergiesData(user).subscribe(() => {
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
