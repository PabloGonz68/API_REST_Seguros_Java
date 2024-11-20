package com.es.segurosinseguros.utils;

import com.es.segurosinseguros.dto.AsistenciaMedicaDTO;
import com.es.segurosinseguros.dto.SeguroDTO;
import com.es.segurosinseguros.model.AsistenciaMedica;
import com.es.segurosinseguros.model.Seguro;
import com.es.segurosinseguros.repository.SeguroRepository;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public SeguroDTO mapToSeguroDTO(Seguro seguro) {
        SeguroDTO seguroDTO = new SeguroDTO();
        seguroDTO.setNif(seguro.getNif());
        seguroDTO.setApe1(seguro.getApe1());
        seguroDTO.setApe2(seguro.getApe2());
        seguroDTO.setNombre(seguro.getNombre());
        seguroDTO.setEdad(seguro.getEdad());
        seguroDTO.setCasado(seguro.isCasado());
        seguroDTO.setSexo(seguro.getSexo());
        return seguroDTO;
    }

    public Seguro mapToSeguro(SeguroDTO seguroDTO) {
        Seguro seguro = new Seguro();
        seguro.setNif(seguroDTO.getNif());
        seguro.setApe1(seguroDTO.getApe1());
        seguro.setApe2(seguroDTO.getApe2());
        seguro.setNombre(seguroDTO.getNombre());
        seguro.setEdad(seguroDTO.getEdad());
        seguro.setCasado(seguroDTO.isCasado());
        seguro.setSexo(seguroDTO.getSexo());
        return seguro;
    }

    public AsistenciaMedica mapToAsistenciaMedica(AsistenciaMedicaDTO asistenciaMedicaDTO) {
        AsistenciaMedica asistenciaMedica = new AsistenciaMedica();
        asistenciaMedica.setFecha(asistenciaMedicaDTO.getFecha());
        asistenciaMedica.setHora(asistenciaMedicaDTO.getHora());
        asistenciaMedica.setLugar(asistenciaMedicaDTO.getLugar());
        asistenciaMedica.setBreveDescripcion(asistenciaMedicaDTO.getBreveDescripcion());
        asistenciaMedica.setExplicacion(asistenciaMedicaDTO.getExplicacion());
        asistenciaMedica.setImporte(asistenciaMedicaDTO.getImporte());
        asistenciaMedica.setTipoAsistencia(asistenciaMedicaDTO.getTipoAsistencia());
        return asistenciaMedica;
    }

    public AsistenciaMedicaDTO mapToAsistenciaMedicaDTO(AsistenciaMedica asistenciaMedica) {
        AsistenciaMedicaDTO asistenciaMedicaDTO = new AsistenciaMedicaDTO();
        asistenciaMedicaDTO.setFecha(asistenciaMedica.getFecha());
        asistenciaMedicaDTO.setHora(asistenciaMedica.getHora());
        asistenciaMedicaDTO.setLugar(asistenciaMedica.getLugar());
        asistenciaMedicaDTO.setBreveDescripcion(asistenciaMedica.getBreveDescripcion());
        asistenciaMedicaDTO.setExplicacion(asistenciaMedica.getExplicacion());
        asistenciaMedicaDTO.setImporte(asistenciaMedica.getImporte());
        asistenciaMedicaDTO.setTipoAsistencia(asistenciaMedica.getTipoAsistencia());
        return asistenciaMedicaDTO;
    }
}
