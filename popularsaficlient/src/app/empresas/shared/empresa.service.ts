import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Empresa, EmpresaPage } from './empresa.interface';

@Injectable({
  providedIn: 'root'
})
export class EmpresaService {

  constructor(
    private http: HttpClient
  ) { }

  list(): Observable<Empresa[]> {
    return this.http.get<Empresa[]>('http://localhost:9090/api/empresas/list');
  }

  paginate(size: number = 5, page: number = 0): Observable<EmpresaPage> {
    let params = new HttpParams();
    params = params.append('size', size);
    params = params.append('page', page);
    params = params.append('sort', 'idEmpresa,desc');

    return this.http.get<EmpresaPage>('http://localhost:9090/api/empresas', { params });
  }

  get(id: number): Observable<EmpresaPage> {
    return this.http.get<EmpresaPage>(`http://localhost:9090/api/empresas/listarPorId/${id}`);
  }

  create(empresa: Empresa): Observable<Empresa> {
    return this.http.post<Empresa>('http://localhost:9090/api/empresas', empresa);
  }

  update(id: number, empresa: Empresa): Observable<Empresa> {
    return this.http.put<Empresa>(`http://localhost:9090/api/empresas/${id}`, empresa);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`http://localhost:9090/api/empresas/eliminar/${id}`);
  }

}
