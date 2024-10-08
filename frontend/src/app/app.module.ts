import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { MatFormFieldModule, MAT_FORM_FIELD_DEFAULT_OPTIONS } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatRadioModule } from '@angular/material/radio';
import {
  HomeComponent,
  PatientInfoDialog,
  ViewPatientInfoDetails,
} from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { HeaderComponent } from './header/header.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatDialogModule } from '@angular/material/dialog';
import {
  DoctorDashboardComponent,
  DoctorRequestDialog,
  MaskedDetails,
  PatientInfoByDisease,
  PatientInfoDetails,
} from './doctor-dashboard/doctor-dashboard.component';
import { UserService } from './services/user.service';
import { HttpClientModule } from '@angular/common/http';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { NomineeDashboardComponent } from './nominee-dashboard/nominee-dashboard.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { HospitalComponent, ViewPatientInfoDetails11 } from './hospital/hospital.component';
import { SidenavComponent } from './sidenav/sidenav.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { LayoutComponent } from './layout/layout.component';
import { ProfileComponent } from './profile/profile.component';
import { MedicationComponent, MedicationDataDialogComponent } from './medication/medication.component';
import { AllergiesComponent, AllergiesDataDialogComponent } from './allergies/allergies.component';
import { InjuryDataDialogComponent, InjuryHistoryComponent } from './injury-history/injury-history.component';
import { OtherReportsComponent } from './other-reports/other-reports.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: '', redirectTo: 'register', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  //{ path: 'second-component', component: SecondComponent },
  { path: 'doctor-dashboard', component: DoctorDashboardComponent },
  { path: 'nominee-dashboard', component: NomineeDashboardComponent },
  { path: 'admin-dashboard', component: AdminDashboardComponent },
  {path: 'hospital', component: HospitalComponent},
  { path: 'profile', component: ProfileComponent },
  { path: 'medication', component: MedicationComponent },
  { path: 'allergies', component: AllergiesComponent },
  { path: 'injury-history', component: InjuryHistoryComponent },
  { path: 'other-reports', component: OtherReportsComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    RegisterComponent,
    HeaderComponent,
    PatientInfoDialog,
    DoctorDashboardComponent,
    DoctorRequestDialog,
    PatientInfoDetails,
    MaskedDetails,
    ViewPatientInfoDetails,
    PatientInfoByDisease,
    NomineeDashboardComponent,
    AdminDashboardComponent,
    HospitalComponent,
    ViewPatientInfoDetails11,
    SidenavComponent,
    LayoutComponent,
    ProfileComponent,
    MedicationComponent,
    AllergiesComponent,
    InjuryHistoryComponent,
    OtherReportsComponent,
    MedicationDataDialogComponent,
    AllergiesDataDialogComponent,
    InjuryDataDialogComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatCardModule,
    MatRadioModule,
    MatToolbarModule,
    MatMenuModule,
    MatTooltipModule,
    MatDialogModule,
    HttpClientModule,
    MatTableModule,
    MatPaginatorModule,
    MatSnackBarModule,
    MatSidenavModule,
    MatListModule,
  ],
  providers: [
    {
      provide: MAT_FORM_FIELD_DEFAULT_OPTIONS,
      useValue: { floatLabel: 'auto' },
    },
    UserService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
