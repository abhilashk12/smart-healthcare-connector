import { Component, OnInit, inject } from '@angular/core';

import { CommonModule } from '@angular/common';

import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

import { AuthorizationService } from './authorization.service';
import { AuthorizationRequest } from './models/authorization.model';

import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { AuthorizationDialogComponent } from './authorization-dialog/authorization-dialog.component';
import { AiReviewDialogComponent } from './ai-review-dialog/ai-review-dialog.component';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
  selector: 'app-authorization',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatButtonModule, MatIconModule, MatDialogModule, MatSnackBarModule],
  templateUrl: './authorization.component.html',
  styleUrls: ['./authorization.component.scss'],
})
export class AuthorizationComponent implements OnInit {
  private authorizationService = inject(AuthorizationService);
  private dialog = inject(MatDialog);
  private snackBar = inject(MatSnackBar);

  requests: AuthorizationRequest[] = [];

  displayedColumns = [
    'patientId',
    'diagnosis',
    'procedure',
    'status',
    'actions',
  ];

  ngOnInit(): void {
    this.load();
  }

  load() {
    this.authorizationService.getAll().subscribe({
      next: (data) => (this.requests = data),

      error: console.error,
    });
  }

  openDialog() {
    const dialogRef = this.dialog.open(AuthorizationDialogComponent);

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.load();
      }
    });
  }

  review(id:number){

    this.dialog.open(
        AiReviewDialogComponent,
        {
            width:'700px',
            data:{
                id:id
            }
        }
    ).afterClosed()
     .subscribe(()=>{

         this.load();

     });

  }

  submit(id: number): void {
  this.authorizationService.submit(id).subscribe({
    next: () => {

      this.load();

      this.snackBar.open(
        'Authorization request submitted successfully.',
        'Close',
        {
          duration: 3000,
          horizontalPosition: 'right',
          verticalPosition: 'top'
        }
      );
    },

    error: (error) => {
      console.error(error);

      this.snackBar.open(
        'Failed to submit authorization request.',
        'Close',
        {
          duration: 3000,
          horizontalPosition: 'right',
          verticalPosition: 'top'
        }
      );
    }
  });
}
}
