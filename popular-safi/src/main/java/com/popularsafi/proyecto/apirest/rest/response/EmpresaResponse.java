package com.popularsafi.proyecto.apirest.rest.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class EmpresaResponse<T> implements Serializable {

    private String status;

    private String code;

    private String message;

    private T data;

    public EmpresaResponse(String deleted, String code, String message) {
    }
}
