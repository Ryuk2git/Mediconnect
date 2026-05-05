

// src/app/shared/shared.module.ts
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NavbarComponent } from './navbar/navbar.component';  
import { AuthModule } from '../auth/auth.module';

@NgModule({
  declarations: [
    NavbarComponent
  ],  
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    AuthModule
  ],
  exports: [
    NavbarComponent
  ]
})
export class SharedModule { }