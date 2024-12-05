package com.es.segurosinseguros.controller;

import com.es.segurosinseguros.dto.SeguroDTO;
import com.es.segurosinseguros.exception.BadRequestException;
import com.es.segurosinseguros.exception.ErrorMessageForClient;
import com.es.segurosinseguros.exception.ResourceNotFoundException;
import com.es.segurosinseguros.service.SeguroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seguros")
public class SeguroController {

    @Autowired
    private SeguroService seguroService;

    @GetMapping("/")
    public ResponseEntity<?> getAllSeguros() {
        try {
            List<SeguroDTO> seguros = seguroService.getAll();
            return new ResponseEntity<>(seguros, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            ErrorMessageForClient errorMessageForClient = new ErrorMessageForClient(e.getMessage(), "/seguros");
            return new ResponseEntity<>(errorMessageForClient, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ErrorMessageForClient errorMessageForClient = new ErrorMessageForClient(e.getMessage(), "/seguros");
            return new ResponseEntity<>(errorMessageForClient, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        try {
            SeguroDTO seguroDTO = seguroService.getById(id);
            return new ResponseEntity<>(seguroDTO, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            ErrorMessageForClient errorMessageForClient = new ErrorMessageForClient(e.getMessage(), "/seguros");
            return new ResponseEntity<>(errorMessageForClient, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ErrorMessageForClient errorMessageForClient = new ErrorMessageForClient(e.getMessage(), "/seguros");
            return new ResponseEntity<>(errorMessageForClient, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody SeguroDTO seguroDTO) {
        try {
            SeguroDTO seguro = seguroService.create(seguroDTO);
            return new ResponseEntity<>(seguro, HttpStatus.OK);
        } catch (BadRequestException e) {
            ErrorMessageForClient errorMessageForClient = new ErrorMessageForClient(e.getMessage(), "/seguros");
            return new ResponseEntity<>(errorMessageForClient, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ErrorMessageForClient errorMessageForClient = new ErrorMessageForClient(e.getMessage(), "/seguros");
            return new ResponseEntity<>(errorMessageForClient, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody SeguroDTO seguroDTO) {
        try {
            SeguroDTO actSeguro = seguroService.update(id, seguroDTO);
            return new ResponseEntity<>(actSeguro, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            ErrorMessageForClient errorMessageForClient = new ErrorMessageForClient(e.getMessage(), "/seguros");
            return new ResponseEntity<>(errorMessageForClient, HttpStatus.NOT_FOUND);
        } catch (BadRequestException e) {
            ErrorMessageForClient errorMessageForClient = new ErrorMessageForClient(e.getMessage(), "/seguros");
            return new ResponseEntity<>(errorMessageForClient, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ErrorMessageForClient errorMessageForClient = new ErrorMessageForClient(e.getMessage(), "/seguros");
            return new ResponseEntity<>(errorMessageForClient, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            seguroService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (BadRequestException e) {
            ErrorMessageForClient errorMessageForClient = new ErrorMessageForClient(e.getMessage(), "/seguros");
            return new ResponseEntity<>(errorMessageForClient, HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException e) {
            ErrorMessageForClient errorMessageForClient = new ErrorMessageForClient(e.getMessage(), "/seguros");
            return new ResponseEntity<>(errorMessageForClient, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ErrorMessageForClient errorMessageForClient = new ErrorMessageForClient(e.getMessage(), "/seguros");
            return new ResponseEntity<>(errorMessageForClient,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
