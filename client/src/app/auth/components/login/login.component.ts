import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    loginForm!: FormGroup;
    errorMessage: string | null = null;
    successMessage: string | null = null;

    constructor(private fb: FormBuilder) { }

    ngOnInit(): void {
        this.loginForm = this.fb.group({
            username: [
                '',
                [Validators.required, Validators.pattern('^[a-zA-Z0-9]+$')]
            ],
            password: [
                '',
                [
                    Validators.required,
                    Validators.minLength(8),
                    Validators.pattern('^(?=.*[A-Z])(?=.*[0-9]).*$')
                ]
            ]
        });
    }

    onSubmit(): void {
        this.errorMessage = null;
        this.successMessage = null;

        if (this.loginForm.invalid) {
            this.errorMessage = 'Please fill out all fields correctly.';
            return;
        }

        this.successMessage = 'Login successful!';
    }
}