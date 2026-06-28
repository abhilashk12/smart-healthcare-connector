export interface Patient {

  id: number;
  firstName: string;
  lastName: string;
  insuranceNumber: string;

}

export interface User {

  id: number;
  fullName: string;

}

export interface AuthorizationRequest {

  id: number;

  patient: Patient;

  provider: User;

  payer: User;

  diagnosis: string;

  procedureCode: string;

  doctorNotes: string;

  status: string;

}