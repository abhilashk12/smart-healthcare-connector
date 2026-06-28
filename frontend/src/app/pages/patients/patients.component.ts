import { Component, inject } from '@angular/core';
import { Patient } from './patient.model';
import { PatientService } from './services/patient.service';

import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-patients',
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './patients.component.html',
  styleUrl: './patients.component.scss'
})
export class PatientsComponent {

  private patientService = inject(PatientService);

  patients: Patient[] = [];

  displayedColumns = [
    'name',
    'insurance',
    'phone',
    'email',
    'actions'
  ];

  ngOnInit(): void {
    this.loadPatients();
  }

  loadPatients() {
    this.patientService.getPatients().subscribe({
      next: data => this.patients = data,
      error: err => console.error(err)
    });
  }

}
