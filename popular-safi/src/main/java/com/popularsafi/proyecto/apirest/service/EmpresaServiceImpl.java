package com.popularsafi.proyecto.apirest.service;

import com.popularsafi.proyecto.apirest.exception.BadRequestException;
import com.popularsafi.proyecto.apirest.model.Empresa;
import com.popularsafi.proyecto.apirest.repository.EmpresaRepository;
import com.popularsafi.proyecto.apirest.rest.EmpresaRest;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;
    public static final ModelMapper mapper = new ModelMapper();

    @Override
    public List<EmpresaRest> listarTodo()  {
        final List<Empresa> empresas = this.empresaRepository.findAll();
        return empresas.stream().map(this::convertToRest)
                .collect(Collectors.toList());
    }

    @Override
    public EmpresaRest listarPorId(final Integer id){
        final Empresa empresa = getEmpresaByIdHelper(id);
        return convertToRest(empresa);
    }

    @Override
    @Transactional
    public EmpresaRest guardar(final EmpresaRest empresaRest) {

        boolean rucExiste = this.empresaRepository
                .existsByRuc(empresaRest.getRuc());

        if (rucExiste)
            throw new BadRequestException("RUC already exists");

        boolean razonSocialExiste = this.empresaRepository
                .existsByRazonSocial(empresaRest.getRazonSocial());

        if (razonSocialExiste)
            throw new BadRequestException("Razón Social already exists");

        try{
            empresaRest.setEstado(Empresa.EstadoEmpresa.HABILITADO);
            Empresa empresaEntity = convertToEntity(empresaRest);
            Empresa guardarEmpresa = this.empresaRepository.save(empresaEntity);
            return convertToRest(guardarEmpresa);
        }catch (Exception e){
            throw new BadRequestException("ERROR DURING COMPANY SAVE");
        }

    }

    @Override
    @Transactional
    public EmpresaRest actualizar(final Integer id, final EmpresaRest empresaRest) {

        Empresa empresa = getEmpresaByIdHelper(id);

        boolean rucExiste = this.empresaRepository.existsByRucAndIdEmpresaNot(empresaRest.getRuc(), empresa.getIdEmpresa());
        if (rucExiste)
            throw new BadRequestException("RUC already exists");

        boolean razonSocialExiste = this.empresaRepository.existsByRazonSocialAndIdEmpresaNot(empresaRest.getRazonSocial(), empresa.getIdEmpresa());
        if (razonSocialExiste)
            throw new BadRequestException("Razón Social already exists");

        Empresa empresaEntity = convertToEntity(empresaRest);
        empresaEntity.setIdEmpresa(id);
        Empresa empresaUpdate = this.empresaRepository.save(empresaEntity);
        return convertToRest(empresaUpdate);

    }

    @Override
    @Transactional
    public EmpresaRest eliminar(final Integer id) {
        final Empresa empresa = getEmpresaByIdHelper(id);
        this.empresaRepository.deleteById(empresa.getIdEmpresa());
        return convertToRest(empresa);
    }

    @Override
    public Page<EmpresaRest> listarTodoPaginado(Pageable pageable) {
        final  Page<Empresa> empresas = this.empresaRepository.findAll(pageable);
        return empresas.map(this::convertToRest);
    }

    private Empresa getEmpresaByIdHelper(Integer id) {
        return this.empresaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public EmpresaRest convertToRest(Empresa empresa) {
        return mapper.map(empresa, EmpresaRest.class);
    }

    public Empresa convertToEntity(EmpresaRest empresaRest) {
        return mapper.map(empresaRest, Empresa.class);
    }
}
