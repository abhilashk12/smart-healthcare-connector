import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AiReviewDialogComponent } from './ai-review-dialog.component';

describe('AiReviewDialogComponent', () => {
  let component: AiReviewDialogComponent;
  let fixture: ComponentFixture<AiReviewDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AiReviewDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AiReviewDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
