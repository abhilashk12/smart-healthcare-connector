import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

import {
  MAT_DIALOG_DATA,
  MatDialogModule,
  MatDialogRef
} from '@angular/material/dialog';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import {
  MatDatepickerModule
} from '@angular/material/datepicker';
import {
  MatNativeDateModule
} from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { finalize } from 'rxjs';

import { PatientService } from '../services/patient.service';
import { Patient } from '../patient.model';

@Component({
  selector: 'app-patient-dialog',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatButtonModule,
    MatSnackBarModule,
    MatProgressSpinnerModule
  ],
  templateUrl: './patient-dialog.component.html',
  styleUrl: './patient-dialog.component.scss'
})
export class PatientDialogComponent {

  private fb = inject(FormBuilder);
  private patientService = inject(PatientService);
  private snackBar = inject(MatSnackBar);

  dialogRef = inject(MatDialogRef<PatientDialogComponent>);
  data = inject(MAT_DIALOG_DATA);

  loading = false;

  genders = [
    'Male',
    'Female',
    'Other'
  ];

  form = this.fb.group({

    firstName: ['', Validators.required],

    lastName: ['', Validators.required],

    dateOfBirth: [null, Validators.required],

    gender: ['', Validators.required],

    insuranceNumber: ['', Validators.required],

    phoneNumber: [
      '',
      [
        Validators.required,
        Validators.pattern('^[0-9]{10}$')
      ]
    ],

    email: [
      '',
      [
        Validators.required,
        Validators.email
      ]
    ]

  });

  save() {

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.loading = true;

    const value = this.form.value;

    const patient: Patient = {

      firstName: value.firstName!,

      lastName: value.lastName!,

      dateOfBirth: this.formatDate(value.dateOfBirth!),

      gender: value.gender!,

      insuranceNumber: value.insuranceNumber!,

      phoneNumber: value.phoneNumber!,

      email: value.email!

    };

    this.patientService.createPatient(patient)
      .pipe(
        finalize(() => this.loading = false)
      )
      .subscribe({

        next: () => {

          this.snackBar.open(
            'Patient created successfully!',
            'Close',
            {
              duration: 3000
            }
          );

          this.dialogRef.close(true);

        },

        error: () => {

          this.snackBar.open(
            'Failed to create patient.',
            'Close',
            {
              duration: 3000
            }
          );

        }

      });

  }

  private formatDate(date: Date): string {

    return date.toISOString().split('T')[0];

  }

}