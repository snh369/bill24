import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ ReactiveFormsModule , FormsModule, CommonModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrl: '../../../styles/common.css'
})

export class LoginComponent {
  languages = [
    { id: 1, name: 'English', flag: 'united-kingdom-flag-icon.svg', disabled: false },
    { id: 2, name: 'ភាសាខ្មែរ', flag: 'cambodia-flag-icon.svg', disabled: false },
    { id: 3, name: '한국어', flag: 'south-korean-flag-icon.svg', disabled: true },
    { id: 4, name: '中文', flag: 'china-flag-icon.svg', disabled: true }
  ]; 

  selectedLanguage = this.languages[0];

  selectLanguage(language: any) {
    if (!language.disabled) {
      this.selectedLanguage = language;
    }
  }

  protected loginForm = new FormGroup({
    username: new FormControl('', [Validators.required, Validators.minLength(4)]),
    password: new FormControl('', [Validators.required])
  });

  get username() {
    return this.loginForm.get('username');
  }

  get password() {
    return this.loginForm.get('password');
  }

  onSubmit() {
    if (this.loginForm.valid) {
      console.table(this.loginForm.value);
    }
  }
}