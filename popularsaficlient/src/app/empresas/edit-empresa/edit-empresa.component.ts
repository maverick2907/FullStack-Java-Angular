import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EmpresaService } from '../shared/empresa.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Empresa, EmpresaPage } from '../shared/empresa.interface';

@Component({
  selector: 'app-edit-empresa',
  templateUrl: './edit-empresa.component.html'
})
export class EditEmpresaComponent implements OnInit {
  opciones: string[] = ['HABILITADO', 'DESHABILITADO'];

  errors?: string[];
  form?: FormGroup;
  empresa?: Empresa;

  constructor(
    private fb: FormBuilder,
    private empresaService: EmpresaService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    const empresaId = this.route.snapshot.paramMap.get('empresa_id')!;

    this.empresaService.get(parseInt(empresaId))
      .subscribe((empresaPage: EmpresaPage) => {
        this.empresa=empresaPage.data;
        this.form = this.fb.group({
          razon_social: [empresaPage.data.razon_social, [Validators.required, Validators.minLength(5), Validators.maxLength(100)]],
          ruc: [empresaPage.data.ruc, [Validators.required, Validators.minLength(11), Validators.maxLength(11)]],
          direccion: [empresaPage.data.direccion, [Validators.required]],
          estado: [empresaPage.data.estado]
        });
      });
  }

  controlHasError(control: string, error: string) {
    return this.form!.controls[control].hasError(error) && this.form!.controls[control].touched;
  }

  save() {
    if (this.form!.invalid) {
      this.form!.markAllAsTouched();
      return;
    }
    //Llamada al servicio para crear una empresa
    const formValue = this.form!.value;
    this.empresaService.update(this.empresa!.empresa_id,formValue)
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
