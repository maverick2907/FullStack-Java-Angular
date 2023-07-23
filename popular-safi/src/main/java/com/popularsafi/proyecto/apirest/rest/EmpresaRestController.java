package com.popularsafi.proyecto.apirest.rest;

import com.popularsafi.proyecto.apirest.rest.response.EmpresaResponse;
import com.popularsafi.proyecto.apirest.rest.response.PaginatedEmpresaResponse;
import com.popularsafi.proyecto.apirest.service.EmpresaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
@AllArgsConstructor
public class EmpresaRestController {

    private final EmpresaService empresaService;

    @GetMapping(value = "/listar")
    EmpresaResponse<List<EmpresaRest>> listarEmpresas() {
        return new EmpresaResponse<>("SUCCESS",
                                            String.valueOf(HttpStatus.OK),
                                    "COMPANIES SUCCESSFULLY READED",
                                            this.empresaService.listarTodo());
    }

    @GetMapping(value = "/listarPorId/{idEmpresa}")
    EmpresaResponse<EmpresaRest> listarPorIdEmpresa(@PathVariable Integer idEmpresa) {
        return new EmpresaResponse<>("SUCCESS",
                                            String.valueOf(HttpStatus.OK),
                                    "COMPANY ID: " + idEmpresa + "SUCCESSFULLY READED",
                                            this.empresaService.listarPorId(idEmpresa));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    EmpresaResponse<EmpresaRest> insertarEmpresa(@Validated @RequestBody EmpresaRest empresa) {
        return new EmpresaResponse<>("SUCCESS",
                                            String.valueOf(HttpStatus.CREATED),
                                    "COMPANY SUCCESSFULLY SAVED",
                                            this.empresaService.guardar(empresa));
    }

    @PutMapping(value = "/{idEmpresa}", consumes = MediaType.APPLICATION_JSON_VALUE)
    EmpresaResponse<EmpresaRest> actualizarEmpresa(@PathVariable Integer idEmpresa, @Validated @RequestBody EmpresaRest empresaRest) {
        return new EmpresaResponse<>("SUCCESS",
                                            String.valueOf(HttpStatus.OK),
                                    "COMPANY ID: " + idEmpresa + " SUCCESSFULLY UPDATED",
                                            this.empresaService.actualizar(idEmpresa, empresaRest));
    }

    @DeleteMapping(value = "/eliminar/{idEmpresa}")
    EmpresaResponse<EmpresaRest> eliminarEmpresa(@PathVariable Integer idEmpresa) {
        return new EmpresaResponse<>("SUCCESS",
                                            String.valueOf(HttpStatus.NO_CONTENT),
                                    "COMPANY ID: " + idEmpresa + " SUCCESSFULLY DELETED",
                                            this.empresaService.eliminar(idEmpresa));
    }

    @GetMapping
    PaginatedEmpresaResponse paginate(
            @PageableDefault(size = 5, sort = "idEmpresa", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<EmpresaRest> empresasPaginadas = this.empresaService.listarTodoPaginado(pageable);
        return new PaginatedEmpresaResponse("SUCCESS",
                String.valueOf(HttpStatus.OK),
                "COMPANIES SUCCESSFULLY READED",
                empresasPaginadas);
    }

}
