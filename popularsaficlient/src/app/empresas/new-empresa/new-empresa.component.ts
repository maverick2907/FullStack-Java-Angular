import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EmpresaService } from '../shared/empresa.service';
import {  Router } from '@angular/router';

@Component({
  selector: 'app-new-empresa',
  templateUrl: './new-empresa.component.html'
})
export class NewEmpresaComponent {

  errors?: string[];

  form: FormGroup = this.fb.group({
    razon_social: [, [Validators.required, Validators.minLength(5), Validators.maxLength(100)]],
    ruc: [, [Validators.required, Validators.minLength(11), Validators.maxLength(11)]],
    direccion: [, [Validators.required]]
  });

  constructor(
    private fb: FormBuilder,
    private empresaService: EmpresaService,
    private router: Router
  ) { }

  controlHasError(control: string, error: string) {
    return this.form.controls[control].hasError(error) && this.form.controls[control].touched;
  }

  save() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    //Llamada al servicio para crear una empresa
    const formValue = this.form.value;
    this.empresaService.create(formValue)
      .subscribe({
        next: empresa => {
          this.router.navigate(['/']);
        },
        error: error => {
          this.errors = error.error.errors;
        }
      });
  }

}
