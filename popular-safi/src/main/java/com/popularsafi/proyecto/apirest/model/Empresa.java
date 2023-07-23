package com.popularsafi.proyecto.apirest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Table(name = "EMPRESAS")
@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer idEmpresa;

    @NotEmpty
    @NotNull
    @Size(min = 11)
    @Column(name = "RUC")
    private String ruc;

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 100)
    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;

    @NotEmpty
    @NotNull
    @Column(name = "DIRECCION")
    private String direccion;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO")
    private EstadoEmpresa estado;

    public enum EstadoEmpresa {
        HABILITADO,
        DESHABILITADO
    }
}
