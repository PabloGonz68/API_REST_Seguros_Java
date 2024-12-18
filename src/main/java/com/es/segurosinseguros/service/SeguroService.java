package com.es.segurosinseguros.service;

import com.es.segurosinseguros.dto.SeguroDTO;
import com.es.segurosinseguros.exception.BadRequestException;
import com.es.segurosinseguros.exception.ResourceNotFoundException;
import com.es.segurosinseguros.exception.ValidationException;
import com.es.segurosinseguros.model.Seguro;
import com.es.segurosinseguros.model.Usuario;
import com.es.segurosinseguros.repository.SeguroRepository;
import com.es.segurosinseguros.repository.UsuarioRepository;
import com.es.segurosinseguros.utils.Mapper;
import com.es.segurosinseguros.utils.StringToLong;
import com.es.segurosinseguros.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeguroService {
    @Autowired
    private Mapper mapper;
    @Autowired
    private SeguroRepository seguroRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public SeguroDTO create(SeguroDTO seguroDTO) {
        {
            try {
                if (seguroDTO == null) {
                    throw new BadRequestException("El seguro no puede ser nulo");
                }
                Usuario usuario = usuarioRepository.findById(seguroDTO.getId_usuario()).orElseThrow(() -> new ResourceNotFoundException("El usuario no existe"));
                Validator.validateSeguro(seguroDTO);
                Seguro seguro = mapper.mapToSeguro(seguroDTO, usuario);
                seguroRepository.save(seguro);
                return mapper.mapToSeguroDTO(seguro);
            } catch (ValidationException e) {
                throw e;
            } catch (Exception e) {
                throw new BadRequestException(e.getMessage());
            }
        }
    }

    public SeguroDTO getById(String id) {
        try {
            Long idLong = StringToLong.stringToLong(id);
            if (id == null || idLong < 0) {
                throw new BadRequestException("El id no puede ser nulo o menor que cero");
            }
            Seguro seguro = seguroRepository.findById(idLong).orElse(null);
            if (seguro == null) {
                throw new BadRequestException("El seguro no existe");
            }
            return mapper.mapToSeguroDTO(seguro);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<SeguroDTO> getAll() {
        try {
            List<Seguro> seguros = seguroRepository.findAll();
            if (seguros.isEmpty()) {
                throw new BadRequestException("No hay seguros");
            }
            List<SeguroDTO> seguroDTOS = new ArrayList<>();
            // Este for es para convertir una lista de seguros a una lista de segurosDTO
            seguros.forEach(seguro -> seguroDTOS.add(mapper.mapToSeguroDTO(seguro)));
            return seguroDTOS;
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public SeguroDTO update(String id, SeguroDTO seguroDTO) {
        try {
            Long idLong = StringToLong.stringToLong(id);
            Usuario usuario = usuarioRepository.findById(seguroDTO.getId_usuario()).orElseThrow(() -> new ResourceNotFoundException("El usuario no existe"));
            if (seguroDTO == null) {
                throw new BadRequestException("El seguro es nulo");
            }

            Validator.validateSeguro(seguroDTO);
            Seguro seguro = mapper.mapToSeguro(seguroDTO, usuario);
            seguro.setIdSeguro(idLong);
            seguroRepository.save(seguro);
            return mapper.mapToSeguroDTO(seguro);
        } catch (ValidationException e) {
            throw e;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public void delete(String id) {
        try {
            Long idLong = StringToLong.stringToLong(id);
            if (id == null || idLong < 0) {
                throw new BadRequestException("El id no puede ser nulo o menor que cero");
            }
            seguroRepository.deleteById(idLong);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}