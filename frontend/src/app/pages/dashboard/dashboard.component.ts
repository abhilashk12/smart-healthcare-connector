import { Component } from '@angular/core';

import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';

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

  totalRequests = 12;

  pending = 4;

  approved = 6;

  rejected = 2;
}
