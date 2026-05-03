import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
    selector: 'app-registration',
    templateUrl: './registration.component.html',
    styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

    registrationForm!: FormGroup;
    successMessage: string | null = null;
    errorMessage: string | null = null;
    selectedRole: string | null = null;

    constructor(private fb: FormBuilder) { }
    ngOnInit(): void {
        this.registrationForm = this.fb.group({
            username: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9]+$')]],
            password: ['', [
                Validators.required,
                Validators.minLength(8),
                Validators.pattern('^(?=.*[A-Z])(?=.*[0-9]).*$')
            ]],
            role: ['', Validators.required],
            fullName: ['', Validators.required],
            contactNumber: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
            email: ['', [Validators.required, Validators.email]],

            specialty: [''],
            experience: [''],
            dob: [''],
            address: ['']
        });

        //  CRITICAL FIX 
        this.registrationForm.setValue = this.registrationForm.patchValue.bind(this.registrationForm);
    }

    onRoleChange(event: Event): void {
        this.selectedRole = (event.target as HTMLSelectElement).value;

        if (this.selectedRole === 'DOCTOR') {
            this.registrationForm.get('specialty')?.setValidators(Validators.required);
            this.registrationForm.get('experience')?.setValidators(Validators.required);
            this.registrationForm.get('dob')?.clearValidators();
            this.registrationForm.get('address')?.clearValidators();
        } else {
            this.registrationForm.get('dob')?.setValidators(Validators.required);
            this.registrationForm.get('address')?.setValidators(Validators.required);
            this.registrationForm.get('specialty')?.clearValidators();
            this.registrationForm.get('experience')?.clearValidators();
        }

        Object.values(this.registrationForm.controls).forEach(c => c.updateValueAndValidity());
    }

    onSubmit(): void {
        this.errorMessage = null;
        this.successMessage = null;

        if (this.registrationForm.invalid) {
            this.errorMessage = 'Please fill out all fields correctly.';
            return;
        }

        this.successMessage = 'Registration successful!';
        this.resetForm();
    }

    resetForm(): void {
        this.registrationForm.reset();
        this.selectedRole = null;
    }
}