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


export interface injuryData{
  injury: string,
  date: string,
  file: string
}

@Component({
  selector: 'app-injury-history',
  templateUrl: './injury-history.component.html',
  styleUrls: ['./injury-history.component.css']
})
export class InjuryHistoryComponent implements OnInit {

  constructor(public dialog: MatDialog,
    private userService: UserService,
    private _snackbar: MatSnackBar) { }

  displayedColumns: string[] = ['injury', 'date', 'file'];
  dataSource!: MatTableDataSource<injuryData>;
  patientId = '';

  ngOnInit(){
    this.patientId = sessionStorage.getItem('userId') || '';
    this.getInjuryData();
  }

  getInjuryData(){
    this.userService.getInjuryData(this.patientId).subscribe((response) => {
      console.log(response);
      this.dataSource = response;
    });
  }
  openDialog() {
    this.dialog.open(InjuryDataDialogComponent, {
      height: '600px',
      width: '600px',
    });
    
  }

  viewInjuryData(element: injuryData) {
      this.dialog.open(viewInjuryDetails, {
        height: '600px',
        width: '600px',
        data: element,
      });
  }
}

@Component({
  templateUrl: './view-injury-history-data-dialog.component.html',
  styleUrls: ['./injury-history.component.css']
})
export class viewInjuryDetails implements OnInit{
  form: FormGroup = new FormGroup({
    injury: new FormControl(''),
    date: new FormControl(''),
    file: new FormControl('')
  });

  constructor(
    public dialogRef: MatDialogRef<viewInjuryDetails>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private userService: UserService,
    private _snackbar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.form.get('injury')!.setValue(this.data.injury);
    this.form.get('date')!.setValue(this.data.date);
    this.form.get('file')!.setValue(this.data.file);
  }

  editInfo(){
    const user = {
      patientId: sessionStorage.getItem('userId'),
      injury: this.form.get('injury')!.value,
      date: this.form.get('date')!.value,
      file: this.form.get('file')!.value,
    };
    this.userService.editInjuryData(user).subscribe((response) => {
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
  templateUrl: './injury-history-data-dialog.component.html',
  styleUrls: ['./injury-history.component.css']
})

export class InjuryDataDialogComponent implements OnInit{

  constructor(
    public dialogRef: MatDialogRef<InjuryDataDialogComponent>,
    private userService: UserService,
    private _snackbar: MatSnackBar,
  ) {}
  
  form: FormGroup = new FormGroup({
    injury: new FormControl(''),
    date: new FormControl(''),
    file: new FormControl('')
  });

  ngOnInit(): void {
   
  }

  submit() {
    const user = {
      patientId: sessionStorage.getItem("userId"),
      injury: this.form.get('injury')!.value,
      date: this.form.get('date')!.value,
      file: this.form.get('file')!.value,
    }
    this.userService.submitInjuryData(user).subscribe(() => {
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