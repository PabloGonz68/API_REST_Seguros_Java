package com.es.segurosinseguros.controller;

import com.es.segurosinseguros.dto.AsistenciaMedicaDTO;
import com.es.segurosinseguros.exception.BadRequestException;
import com.es.segurosinseguros.exception.ErrorMessageForClient;
import com.es.segurosinseguros.exception.ResourceNotFoundException;
import com.es.segurosinseguros.service.AsistenciaMedicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asistenciaMedica")
public class AsistenciaMedicaController {

    @Autowired
    private AsistenciaMedicaService asistenciaMedicaService;


    @GetMapping
    public ResponseEntity<?> getAllAsistenciaMedica() {
        try {
            List<AsistenciaMedicaDTO> asistenciaMedica = asistenciaMedicaService.getAll();
            return new ResponseEntity<>(asistenciaMedica, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            ErrorMessageForClient errorMessageForClient = new ErrorMessageForClient(e.getMessage(), "/asistenciaMedica");
            return new ResponseEntity<>(errorMessageForClient, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ErrorMessageForClient errorMessageForClient = new ErrorMessageForClient(e.getMessage(), "/asistenciaMedica");
            return new ResponseEntity<>(errorMessageForClient, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        try {
            AsistenciaMedicaDTO asistenciaMedicaDTO = asistenciaMedicaService.getById(id);
            return new ResponseEntity<>(asistenciaMedicaDTO, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            ErrorMessageForClient errorMessageForClient = new ErrorMessageForClient(e.getMessage(), "/asistenciaMedica");
            return new ResponseEntity<>(errorMessageForClient, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ErrorMessageForClient errorMessageForClient = new ErrorMessageForClient(e.getMessage(), "/asistenciaMedica");
            return new ResponseEntity<>(errorMessageForClient, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AsistenciaMedicaDTO asistenciaMedicaDTO) {
        try {
            AsistenciaMedicaDTO asistenciaMedica = asistenciaMedicaService.create(asistenciaMedicaDTO);
            return new ResponseEntity<>(asistenciaMedica, HttpStatus.OK);
        } catch (BadRequestException e) {
            ErrorMessageForClient errorMessageForClient = new ErrorMessageForClient(e.getMessage(), "/asistenciaMedica");
            return new ResponseEntity<>(errorMessageForClient, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ErrorMessageForClient errorMessageForClient = new ErrorMessageForClient(e.getMessage(), "/asistenciaMedica");
            return new ResponseEntity<>(errorMessageForClient, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody AsistenciaMedicaDTO asistenciaMedicaDTO) {
        try {
            AsistenciaMedicaDTO actAsistenciaMedica = asistenciaMedicaService.update(id, asistenciaMedicaDTO);
            return new ResponseEntity<>(actAsistenciaMedica, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            ErrorMessageForClient errorMessageForClient = new ErrorMessageForClient(e.getMessage(), "/asistenciaMedica/" + id);
            return new ResponseEntity<>(errorMessageForClient, HttpStatus.NOT_FOUND);
        } catch (BadRequestException e) {
            ErrorMessageForClient errorMessageForClient = new ErrorMessageForClient(e.getMessage(), "/asistenciaMedica/" + id);
            return new ResponseEntity<>(errorMessageForClient, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ErrorMessageForClient errorMessageForClient = new ErrorMessageForClient(e.getMessage(), "/asistenciaMedica/" + id);
            return new ResponseEntity<>(errorMessageForClient, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            asistenciaMedicaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (BadRequestException e) {
            ErrorMessageForClient errorMessageForClient = new ErrorMessageForClient(e.getMessage(), "/asistenciaMedica/" + id);
            return new ResponseEntity<>(errorMessageForClient,HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException e) {
            ErrorMessageForClient errorMessageForClient = new ErrorMessageForClient(e.getMessage(), "/asistenciaMedica/" + id);
            return new ResponseEntity<>(errorMessageForClient,HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ErrorMessageForClient errorMessageForClient = new ErrorMessageForClient(e.getMessage(), "/asistenciaMedica/" + id);
            return new ResponseEntity<>(errorMessageForClient, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
