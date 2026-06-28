import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../environments/environment';
import { AuthorizationRequest } from './models/authorization.model';
import { AIReviewResponse } from './models/ai-review.model';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {

  private http = inject(HttpClient);

  private api = `${environment.apiUrl}/authorizations`;

  getAll(): Observable<AuthorizationRequest[]> {

    return this.http.get<AuthorizationRequest[]>(this.api);

  }

  create(request: AuthorizationRequest): Observable<AuthorizationRequest> {

    return this.http.post<AuthorizationRequest>(this.api, request);

  }

  submit(id: number): Observable<AuthorizationRequest> {

    return this.http.post<AuthorizationRequest>(
      `${this.api}/${id}/submit`,
      {}
    );

  }

  delete(id: number) {

    return this.http.delete(`${this.api}/${id}`);

  }

  review(id:number){

    return this.http.post<AIReviewResponse>(
        `${environment.apiUrl}/ai/review/${id}`,
        {}
    );

    }



}