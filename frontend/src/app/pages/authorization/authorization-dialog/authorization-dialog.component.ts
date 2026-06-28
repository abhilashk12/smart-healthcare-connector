import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  ReactiveFormsModule,
  FormBuilder,
  FormGroup,
  Validators
} from '@angular/forms';

import {
  MatDialogModule,
  MatDialogRef
} from '@angular/material/dialog';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';

import { PatientService } from '../../patients/services/patient.service';
import { AuthorizationService } from '../authorization.service';
import { Patient } from '../../patients/patient.model';
@Component({
  selector: 'app-authorization-dialog',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule
  ],
  templateUrl: './authorization-dialog.component.html',
  styleUrls: ['./authorization-dialog.component.scss']
})
export class AuthorizationDialogComponent implements OnInit {

  private fb = inject(FormBuilder);
  private patientService = inject(PatientService);
  private authorizationService = inject(AuthorizationService);
  private dialogRef = inject(MatDialogRef<AuthorizationDialogComponent>);

  patients: Patient[] = [];

  form!: FormGroup;

  ngOnInit(): void {

    this.form = this.fb.group({

      patientId: ['', Validators.required],

      diagnosis: ['', Validators.required],

      procedureCode: ['', Validators.required],

      doctorNotes: ['', Validators.required]

    });

    this.loadPatients();

  }

  loadPatients() {

    this.patientService.getPatients()
      .subscribe(data => this.patients = data);

  }

  save() {

    if (this.form.invalid) {
      return;
    }

    const payload = {

      ...this.form.value,

      providerId: 1,

      payerId: 2

    };

    this.authorizationService.create(payload)
      .subscribe({

        next: () => {

          this.dialogRef.close(true);

        },

        error: err => console.error(err)

      });

  }

  close() {

    this.dialogRef.close();

  }

}