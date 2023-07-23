export interface EmpresaPage {
    status:   string;
    code:     string;
    message:  string;
    companies: Companies;
    data: Empresa;
}

export interface Companies {
    content:          Empresa[];
    pageable:         Pageable;
    last:             boolean;
    totalPages:       number;
    totalElements:    number;
    size:             number;
    number:           number;
    sort:             Sort;
    first:            boolean;
    numberOfElements: number;
    empty:            boolean;
}

export interface Empresa {
    empresa_id:   number;
    ruc:          string;
    razon_social: string;
    direccion:    string;
    estado:       string;
}

export interface Pageable {
    sort:       Sort;
    offset:     number;
    pageSize:   number;
    pageNumber: number;
    unpaged:    boolean;
    paged:      boolean;
}

export interface Sort {
    empty:    boolean;
    sorted:   boolean;
    unsorted: boolean;
}
