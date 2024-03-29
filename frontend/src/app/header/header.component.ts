import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  role = sessionStorage.getItem('role');
  userId = sessionStorage.getItem('userId');
  constructor(private router: Router) { }

  ngOnInit(): void {
    if (sessionStorage.getItem('role') != null) {
      this.role = sessionStorage.getItem('role');
    }
  }

  logout() {
    sessionStorage.removeItem('userId');
    sessionStorage.removeItem('role');
    this.router.navigate(['/login'])
  }

  profileMethod(str:string){
    if(str == 'medication'){
      this.router.navigate(['/medication']);
    }
    if(str == 'allergies'){
      this.router.navigate(['/allergies']);
    }
    if(str == 'injury-history'){
      this.router.navigate(['/injury-history']);
    }
    if(str == 'other-reports'){
      this.router.navigate(['/other-reports']);
    }
  }

}
