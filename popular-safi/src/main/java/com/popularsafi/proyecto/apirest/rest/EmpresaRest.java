package com.popularsafi.proyecto.apirest.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.popularsafi.proyecto.apirest.model.Empresa;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmpresaRest {

    @JsonProperty("empresa_id")
    private String idEmpresa;

    @NotNull
    @Size(max = 11)
    @JsonProperty("ruc")
    private String ruc;

    @NotNull
    @Size(min = 3, max = 100)
    @JsonProperty("razon_social")
    private String razonSocial;

    @JsonProperty("direccion")
    private String direccion;

    @JsonProperty("estado")
    private Empresa.EstadoEmpresa estado;

}
