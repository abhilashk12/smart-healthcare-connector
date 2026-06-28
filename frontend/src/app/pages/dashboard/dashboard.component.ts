import { Component } from '@angular/core';

import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { AuthorizationService } from '../authorization/authorization.service';

@Component({
  selector: 'app-dashboard',
  imports: [
    MatCardModule,
    MatGridListModule
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent {

  requests: any[] = [];

  totalRequests = 0;
  pending = 0;
  approved = 0;
  rejected = 0;

  constructor(private authorizationService: AuthorizationService) {}

  ngOnInit(): void {
    this.loadDashboard();
  }

  loadDashboard(): void {
    this.authorizationService.getAll().subscribe({
      next: (data) => {
        this.requests = data;
        this.calculateCounts();
      },
      error: (err) => console.error(err)
    });
  }

  calculateCounts(): void {

    this.totalRequests = this.requests.length;

    this.pending = this.requests.filter(r =>
      ['DRAFT', 'AI_REVIEWED', 'NEED_MORE_INFO', 'SUBMITTED'].includes(r.status)
    ).length;

    this.approved = this.requests.filter(r =>
      r.status === 'APPROVED'
    ).length;

    this.rejected = this.requests.filter(r =>
      r.status === 'REJECTED'
    ).length;
  }
}
