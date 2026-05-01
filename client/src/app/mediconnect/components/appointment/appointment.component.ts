import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.scss']
})
export class AppointmentCreateComponent implements OnInit {
  appointmentForm!: FormGroup;
  successMessage: string | null = null;
  errorMessage: string | null = null;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.appointmentForm = this.fb.group({
      appointmentId: ['', [Validators.required, Validators.min(1)]],
      patientId: ['', [Validators.required, Validators.min(1)]],
      clinicId: ['', [Validators.required, Validators.min(1)]],
      appointmentDate: ['', [Validators.required]],
      status: ['', [Validators.required]],
      purpose: ['', [Validators.required, Validators.minLength(5)]]
    });
  }

  onSubmit(): void {
    this.successMessage = null;
    this.errorMessage = null;

    if (this.appointmentForm.valid) {
      this.successMessage = 'Appointment created successfully!';
      console.log('Form Data:', this.appointmentForm.value);
    } else {
      this.errorMessage = 'Please fill in all required fields correctly.';
      this.appointmentForm.markAllAsTouched();
    }
  }

  resetForm(): void {
    this.appointmentForm.reset({
            appointmentId: null,
            patientId: null,
            clinicId: null,
            appointmentDate: '',
            status: '',
            purpose: ''
        });
    this.successMessage = null;
    this.errorMessage = null;
  }
}