import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Patient } from '../patient.model';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  private http = inject(HttpClient);

  private api = `${environment.apiUrl}/patients`;

  getPatients(): Observable<Patient[]> {

    return this.http.get<Patient[]>(this.api);

  }

  createPatient(patient: Patient): Observable<Patient> {

    return this.http.post<Patient>(this.api, patient);

  }

  updatePatient(id: number, patient: Patient): Observable<Patient> {

    return this.http.put<Patient>(`${this.api}/${id}`, patient);

  }

  deletePatient(id: number): Observable<void> {

    return this.http.delete<void>(`${this.api}/${id}`);

  }

}