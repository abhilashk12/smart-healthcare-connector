import { Component, inject, Inject } from '@angular/core';
import { AIReviewResponse } from '../models/ai-review.model';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { AuthorizationService } from '../authorization.service';
import { MatDialogModule } from '@angular/material/dialog';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-ai-review-dialog',
  imports: [MatDialogModule,
    CommonModule,
    MatDialogModule,
    MatButtonModule,
    MatProgressSpinnerModule
  ],
  templateUrl: './ai-review-dialog.component.html',
  styleUrl: './ai-review-dialog.component.scss'
})
export class AiReviewDialogComponent {

  readonly dialogRef = inject(MatDialogRef<AiReviewDialogComponent>);
  readonly data = inject(MAT_DIALOG_DATA);

  loading = true;

  review!:AIReviewResponse;

  constructor(private authorizationService: AuthorizationService) {}

  ngOnInit() {
    this.authorizationService
      .review(this.data.id)
      .subscribe((response) => {
        this.review = response;
      });


    this.authorizationService.review(this.data.id)
      .subscribe({

        next: response => {

          this.review = response;
          this.loading = false;

        },

        error: err => {

          console.error(err);
          this.loading = false;

        }

      });

  }

}
