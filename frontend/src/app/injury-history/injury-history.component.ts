import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { FormGroup, FormControl } from '@angular/forms';

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
  displayedColumns: string[] = ['injury', 'date', 'file'];
  dataSource!: MatTableDataSource<injuryData>;

  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {
  }
  openDialog() {
    this.dialog.open(InjuryDataDialogComponent, {
      height: '600px',
      width: '600px',
    });
    // this.injuryData();
  }

}

@Component({
  templateUrl: './injury-history-data-dialog.component.html',
  styleUrls: ['./injury-history.component.css']
})

export class InjuryDataDialogComponent implements OnInit{
  dialog: any;
  dialogRef: any;
  userService: any;
  _snackbar: any;
  form: FormGroup = new FormGroup({
    injury: new FormControl(''),
    date: new FormControl(''),
    file: new FormControl('')
  });

  constructor(){}
  ngOnInit(): void {
   console.log("")
  }

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