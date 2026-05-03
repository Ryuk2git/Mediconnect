import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';

type Role = 'DOCTOR' | 'PATIENT';
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html'
})
export class RegistrationComponent implements OnInit {
  registrationForm!: FormGroup;
  successMessage: string | null = null;
  errorMessage: string | null = null;
  selectedRole: string | null = null;

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {

    this.registrationForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9]*$')]],
      password: ['', [Validators.required, Validators.minLength(8), Validators.pattern('^(?=.*[A-Z])(?=.*\\d).+$')]],
      role: ['', Validators.required],
      fullName: ['', Validators.required],
      contactNumber: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      email: ['', [Validators.required, Validators.email]],
      specialty: [''],
      experience: [''],
      dateOfBirth: [''],
      address: ['']
    });
  }

  private setRequired(ctrl: AbstractControl | null, required: boolean): void {
    if (!ctrl) return;

    const validators = ctrl.validator ? [ctrl.validator] : [];
    const filtered = validators.filter((v: any) => v !== Validators.required);

    if (required) {
      filtered.push(Validators.required);
    }

    ctrl.setValidators(filtered);
    ctrl.updateValueAndValidity({ emitEvent: false });
  }

  private onRoleChangeInternal(role: Role): void {
    this.selectedRole = role;

    const specialty = this.registrationForm.get('specialty');
    const yoe = this.registrationForm.get('experience');
    const dob = this.registrationForm.get('dateOfBirth');
    const address = this.registrationForm.get('address');

    specialty?.reset();
    yoe?.reset();
    dob?.reset();
    address?.reset();

    yoe?.setValidators([Validators.min(0)]); 

    if (role === 'DOCTOR') {
      this.setRequired(specialty, true);
      this.setRequired(yoe, true);
      this.setRequired(dob, false);
      this.setRequired(address, false);
    } else if (role === 'PATIENT') {
      this.setRequired(specialty, false);
      this.setRequired(yoe, false);
      this.setRequired(dob, true);
      this.setRequired(address, true);
    } else {
      this.setRequired(specialty, false);
      this.setRequired(yoe, false);
      this.setRequired(dob, false);
      this.setRequired(address, false);
    }

    specialty?.updateValueAndValidity({ emitEvent: false });
    yoe?.updateValueAndValidity({ emitEvent: false });
    dob?.updateValueAndValidity({ emitEvent: false });
    address?.updateValueAndValidity({ emitEvent: false });
  }
  onSubmit(): void {
    this.successMessage = null;
    this.errorMessage = null;

    if (this.registrationForm.valid) {
      this.successMessage = 'Registration successful!';
      this.resetForm();
    } else {
      this.errorMessage = 'Please fill out all fields correctly.';
      this.registrationForm.markAllAsTouched();
    }
  }

  resetForm(): void {
    this.registrationForm.reset({
      username: '', password: '', role: '', fullName: '',
      contactNumber: '', email: '', specialty: '',
      experience: null, dateOfBirth: '', address: ''
    });
    this.selectedRole = null;
  }

  get username() { return this.registrationForm.get('username'); }
  get password() { return this.registrationForm.get('password'); }
  get role() { return this.registrationForm.get('role'); }
  get fullName() { return this.registrationForm.get('fullName'); }
  get contactNumber() { return this.registrationForm.get('contactNumber'); }
  get email() { return this.registrationForm.get('email'); }
  get specialty() { return this.registrationForm.get('specialty'); }
  get yearsOfExperience() { return this.registrationForm.get('yearsOfExperience'); }
  get dateOfBirth() { return this.registrationForm.get('dateOfBirth'); }
  get address() { return this.registrationForm.get('address'); }

}

export function dateValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const value = control.value;
    if (!value) return null;
    const dateRegex = /^\d{4}-\d{2}-\d{2}$/;
    const isValid = dateRegex.test(value) && !isNaN(Date.parse(value));
    return isValid ? null : { invalidDate: true };
  };
}

export function positiveNumberValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const value = control.value;
    if (value === null || value === '') return null;
    return value > 0 ? null : { nonPositive: true };
  };
}