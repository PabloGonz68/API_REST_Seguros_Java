package com.es.segurosinseguros.service;

import com.es.segurosinseguros.dto.AsistenciaMedicaDTO;
import com.es.segurosinseguros.exception.BadRequestException;
import com.es.segurosinseguros.exception.ResourceNotFoundException;
import com.es.segurosinseguros.exception.ValidationException;
import com.es.segurosinseguros.model.AsistenciaMedica;
import com.es.segurosinseguros.repository.AsistenciaMedicaRepository;
import com.es.segurosinseguros.utils.Mapper;
import com.es.segurosinseguros.utils.StringToLong;
import com.es.segurosinseguros.utils.Validator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AsistenciaMedicaService {
    private AsistenciaMedicaRepository asistenciaMedicaRepository;
    private Mapper mapper;
    public AsistenciaMedicaService(AsistenciaMedicaRepository asistenciaMedicaRepository, Mapper mapper) {
        this.mapper = mapper;
        this.asistenciaMedicaRepository = asistenciaMedicaRepository;
    }


    public AsistenciaMedicaDTO create(AsistenciaMedicaDTO asistenciaMedicaDTO) {
        try{
            if(asistenciaMedicaDTO == null) {
                throw new BadRequestException("El asistenciaMedica no puede ser nulo");
            }
            Validator.validateAsistenciaMedica(asistenciaMedicaDTO);//validar los campos como dice el DTO
            AsistenciaMedica asistenciaMedica = mapper.mapToAsistenciaMedica(asistenciaMedicaDTO);//convertir el DTO a la entidad
            asistenciaMedicaRepository.save(asistenciaMedica);//guardar la entidad
            return mapper.mapToAsistenciaMedicaDTO(asistenciaMedica);//convertir la entidad a DTO para mostrar los datos en el Postman
        }catch (ValidationException e) {
            throw e;
        }catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public AsistenciaMedicaDTO getById(String id){
        try{
         Long idLong = StringToLong.stringToLong(id);
         if (id == null || idLong < 0) {
             throw new BadRequestException("El id no puede ser nulo o menor que cero");
         }
         AsistenciaMedica asistenciaMedica = asistenciaMedicaRepository.findById(idLong).orElse(null);
         return mapper.mapToAsistenciaMedicaDTO(asistenciaMedica);
        }catch (ResourceNotFoundException e) {
            throw e;
        }catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<AsistenciaMedicaDTO> getAll(){
        try {
            List<AsistenciaMedica> asistenciasMedicas = asistenciaMedicaRepository.findAll(); //Lista el contenido de la bbd
            if(asistenciasMedicas.isEmpty()){
                throw new BadRequestException("No existen asistencias médicas");
            }
            List<AsistenciaMedicaDTO> asistenciaMedicaDTOS = new ArrayList<>();//Lista para los DTO
            //Pasa por toda la primera lista y los mete en la segundopero convertidos en DTO
            asistenciasMedicas.forEach(asistenciaMedica -> asistenciaMedicaDTOS.add(mapper.mapToAsistenciaMedicaDTO(asistenciaMedica)));
            return asistenciaMedicaDTOS;
        }catch (ResourceNotFoundException e){
            throw e;
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public void delete(String id){
        try{
            Long idLong = StringToLong.stringToLong(id);
            if(!asistenciaMedicaRepository.existsById(idLong)){
                throw new BadRequestException("No existe la asistencia medica a eliminar.");
            }
            asistenciaMedicaRepository.deleteById(idLong);
        }catch (ResourceNotFoundException e){
            throw e;
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public AsistenciaMedicaDTO update(String id, AsistenciaMedicaDTO asistenciaMedicaDTO){
        try{
            Long idLong = StringToLong.stringToLong(id);
            if (!asistenciaMedicaRepository.existsById(idLong)){
                throw new ResourceNotFoundException("No se ha encontrado la asistencia medica con la id: "+idLong);
            }
            if (asistenciaMedicaDTO == null) {
                throw new BadRequestException("No existe la asistencia medica a actualizar.");
            }
            Validator.validateAsistenciaMedica(asistenciaMedicaDTO);
            AsistenciaMedica asistenciaMedica = mapper.mapToAsistenciaMedica(asistenciaMedicaDTO);//lo paso a entidad
            asistenciaMedica.setIdAsistenciaMedica(idLong);//PONEMOS SU ID
            asistenciaMedicaRepository.save(asistenciaMedica);//Se guarda de nuevo para actualizar
            return mapper.mapToAsistenciaMedicaDTO(asistenciaMedica);//lo paso a DTO
        }catch (ValidationException e) {
            throw e;
        }catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }



}
