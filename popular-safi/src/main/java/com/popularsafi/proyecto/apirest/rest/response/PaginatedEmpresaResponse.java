package com.popularsafi.proyecto.apirest.rest.response;

import com.popularsafi.proyecto.apirest.rest.EmpresaRest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
public class PaginatedEmpresaResponse {

    private String status;

    private String code;

    private String message;

    private Page<EmpresaRest> companies;

}
