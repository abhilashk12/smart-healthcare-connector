import { Routes } from '@angular/router';

export const routes: Routes = [
     {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full'
  },

  {
    path: 'dashboard',
    loadComponent: () =>
      import('./pages/dashboard/dashboard.component')
        .then(m => m.DashboardComponent)
  },

  {
    path: 'patients',
    loadComponent: () =>
      import('./pages/patients/patients.component')
        .then(m => m.PatientsComponent)
  },

  {
    path: 'authorization',
    loadComponent: () =>
      import('./pages/authorization/authorization.component')
        .then(m => m.AuthorizationComponent)
  },

  {
    path: 'payer',
    loadComponent: () =>
      import('./pages/payer/payer.component')
        .then(m => m.PayerComponent)
  },

  {
    path: 'notifications',
    loadComponent: () =>
      import('./pages/notifications/notifications.component')
        .then(m => m.NotificationsComponent)
  }
];
