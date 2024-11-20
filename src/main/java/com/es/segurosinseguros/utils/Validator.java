package com.es.segurosinseguros.utils;

import com.es.segurosinseguros.dto.AsistenciaMedicaDTO;
import com.es.segurosinseguros.dto.SeguroDTO;
import com.es.segurosinseguros.exception.ValidationException;

public class Validator {

    public static void validateSeguro(SeguroDTO seguroDTO) throws ValidationException {

        if (seguroDTO.getNif() == null || seguroDTO.getNif().isEmpty()) {
            throw new ValidationException("El campo NIF no tiene un formato válido");
        }

        if (seguroDTO.getNombre() == null ||seguroDTO.getApe1() == null || seguroDTO.getNombre().isEmpty() || seguroDTO.getApe1().isEmpty()) {
            throw new ValidationException("El nombre o el primer apellido no pueden estar vacío");
        }
        if (seguroDTO.getApe2() == null || seguroDTO.getApe2().isEmpty()) {
            throw new ValidationException("El segundo apellido no puede estar vacío");
        }

        if (seguroDTO.getEdad() <= 0 && seguroDTO.getEdad() <= 18) {
            throw new ValidationException("No es posible ser menor de edad para hacer un seguro");
        }

        if (!seguroDTO.isCasado() && seguroDTO.getNumHijos() > 0) {
            throw new ValidationException("Un seguro no puede registrar hijos si no está casado");
        }

        if (seguroDTO.getNumHijos() < 0) {
            throw new ValidationException("El número de hijos no puede ser negativo");
        }
        if (seguroDTO.isEmbarazada() && seguroDTO.getSexo().equals("Hombre")) {
            throw new ValidationException("El campo embarazada no puede ser true si el asegurado es hombre");
        }
    }

    public static void validateAsistenciaMedica(AsistenciaMedicaDTO asistenciaMedicaDTO) throws ValidationException {

        if(asistenciaMedicaDTO.getBreveDescripcion() == null || asistenciaMedicaDTO.getBreveDescripcion().isEmpty()) {
            throw new ValidationException("El campo breveDescripcion no puede estar vacío");
        }
        if(asistenciaMedicaDTO.getExplicacion() == null || asistenciaMedicaDTO.getExplicacion().isEmpty()) {
            throw new ValidationException("El campo explicacion no puede estar vacío");
        }
        if(asistenciaMedicaDTO.getTipoAsistencia() == null || asistenciaMedicaDTO.getTipoAsistencia().isEmpty()) {
            throw new ValidationException("El campo tipoAsistencia no puede estar vacío");
        }
        if(asistenciaMedicaDTO.getFecha() == null) {
            throw new ValidationException("El campo fecha no puede estar vacío");
        }
        if(asistenciaMedicaDTO.getHora() == null) {
            throw new ValidationException("El campo hora no puede estar vacío");
        }
        if(asistenciaMedicaDTO.getImporte() <= 0) {
            throw new ValidationException("El campo importe debe ser mayor que 0");
        }
    }
}
