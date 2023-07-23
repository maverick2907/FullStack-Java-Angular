package com.popularsafi.proyecto.apirest.repository;

import com.popularsafi.proyecto.apirest.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    //consulta para saber si existe al menos una empresa a partir de un ruc
    boolean existsByRuc(String ruc);

    // consulta para saber si existe al menos una empresa a partir de un ruc pero que tenga un ID distinto a uno dado
    boolean existsByRucAndIdEmpresaNot(String ruc, Integer idNot);

    //consulta para saber si existe al menos una empresa a partir de una razón social
    boolean existsByRazonSocial(String razonSocial);

    // consulta para saber si existe al menos una empresa a partir de su razon social pero que tenga un ID distinto a uno dado
    boolean existsByRazonSocialAndIdEmpresaNot(String razonSocial, Integer idNot);

}
