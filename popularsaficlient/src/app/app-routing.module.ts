import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmpresaListComponent } from './empresas/empresa-list/empresa-list.component';
import { NewEmpresaComponent } from './empresas/new-empresa/new-empresa.component';
import { EditEmpresaComponent } from './empresas/edit-empresa/edit-empresa.component';

const routes: Routes = [
  {
    path: '',
    component: EmpresaListComponent
  },
  {
    path:'new',
    component: NewEmpresaComponent
  },
  {
    path:':empresa_id/edit',
    component: EditEmpresaComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
