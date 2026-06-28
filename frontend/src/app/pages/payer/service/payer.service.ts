import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { AuthorizationRequest } from '../../authorization/models/authorization.model';


@Injectable({
  providedIn: 'root'
})
export class PayerService {

  private http = inject(HttpClient);

  private api = `${environment.apiUrl}/payer`;

  getPending(): Observable<AuthorizationRequest[]> {

    return this.http.get<AuthorizationRequest[]>(
      `${this.api}/pending`
    );

  }

  approve(id: number) {

    return this.http.put(
      `${this.api}/${id}/approve`,
      {}
    );

  }

  reject(id: number) {

    return this.http.put(
      `${this.api}/${id}/reject`,
      {}
    );

  }

  needMoreInfo(id: number) {

    return this.http.put(
      `${this.api}/${id}/need-more-info`,
      {}
    );

  }

}