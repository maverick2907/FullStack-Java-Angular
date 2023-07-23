package com.popularsafi.proyecto.apirest.service;

import com.popularsafi.proyecto.apirest.rest.EmpresaRest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmpresaService {

    List<EmpresaRest> listarTodo();
    EmpresaRest listarPorId(Integer id);
    @Transactional
    EmpresaRest guardar(EmpresaRest empresaRest);
    @Transactional
    EmpresaRest actualizar(Integer id, EmpresaRest empresaRest);
    @Transactional
    EmpresaRest eliminar(Integer id);
    Page<EmpresaRest> listarTodoPaginado(Pageable pageable);

}
