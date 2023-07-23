import { Component, OnInit } from '@angular/core';
import { EmpresaService } from '../shared/empresa.service';
import { Empresa, EmpresaPage } from '../shared/empresa.interface';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-empresa-list',
  templateUrl: './empresa-list.component.html'
})
export class EmpresaListComponent implements OnInit {

  empresaPage?: EmpresaPage;
  displayedColumns = ['rsruc', 'dir', 'estado', 'acciones']

  constructor(
    private empresaService: EmpresaService
  ) { }

  ngOnInit(): void {
    this.loadEmpresas();
  }

  deleteEmpresa(empresa: Empresa) {
    this.empresaService.delete(empresa.empresa_id)
      .subscribe(empresa => {
        this.loadEmpresas();
      });
  }

  loadEmpresas() {
    this.empresaService.paginate()
      .subscribe((empresaPage) => {
        this.empresaPage = empresaPage;
      });
  }

  onPaginate(event: PageEvent){
    const page = event.pageIndex;
    const size = event.pageSize;

    this.empresaService.paginate(size, page)
    .subscribe((empresaPage) => {
      this.empresaPage = empresaPage;
    });
  }

}
