import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { PayerService } from './service/payer.service';
import { AuthorizationRequest } from '../authorization/models/authorization.model';


@Component({
  selector: 'app-payer',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './payer.component.html',
  styleUrls: ['./payer.component.scss']
})
export class PayerComponent implements OnInit {

  private payerService = inject(PayerService);

  requests: AuthorizationRequest[] = [];

  displayedColumns = [
    'patient',
    'diagnosis',
    'procedure',
    'status',
    'actions'
  ];

  ngOnInit(): void {

    this.load();

  }

  load() {

    this.payerService
      .getPending()
      .subscribe({

        next: data => this.requests = data,

        error: console.error

      });

  }

  approve(id: number) {

    this.payerService
      .approve(id)
      .subscribe({

        next: () => {this.load();
          alert('Request approved successfully.');
        },

        error: console.error

      });

  }

  reject(id: number) {

    this.payerService
      .reject(id)
      .subscribe({

        next: () => {this.load();
          alert('Request rejected successfully.');
        },

        error: console.error

      });

  }

  needMoreInfo(id: number) {

    this.payerService
      .needMoreInfo(id)
      .subscribe({

        next: () => {this.load();
          alert('More information requested successfully.');
        },

        error: console.error

      });

  }

}