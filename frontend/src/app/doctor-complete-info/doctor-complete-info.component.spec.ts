import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorCompleteInfoComponent } from './doctor-complete-info.component';

describe('DoctorCompleteInfoComponent', () => {
  let component: DoctorCompleteInfoComponent;
  let fixture: ComponentFixture<DoctorCompleteInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DoctorCompleteInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DoctorCompleteInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
