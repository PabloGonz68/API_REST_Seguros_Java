package com.es.segurosinseguros.controller;

import com.es.segurosinseguros.dto.SeguroDTO;
import com.es.segurosinseguros.exception.BadRequestException;
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

    @GetMapping
    public ResponseEntity<List<SeguroDTO>> getAllSeguros() {
        try {
            List<SeguroDTO> seguros = seguroService.getAll();
            return new ResponseEntity<>(seguros, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeguroDTO> getById(@PathVariable String id) {
        try {
            SeguroDTO seguroDTO = seguroService.getById(id);
            return new ResponseEntity<>(seguroDTO, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<SeguroDTO> create(@RequestBody SeguroDTO seguroDTO) {
        try {
            SeguroDTO seguro = seguroService.create(seguroDTO);
            return new ResponseEntity<>(seguro, HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<SeguroDTO> update(@PathVariable String id, @RequestBody SeguroDTO seguroDTO) {
    try{
        SeguroDTO actSeguro = seguroService.update(id, seguroDTO);
        return new ResponseEntity<>(actSeguro, HttpStatus.OK);
    }catch (ResourceNotFoundException e){
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    } catch (BadRequestException e){
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SeguroDTO> delete(@PathVariable String id){
    try{
       seguroService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }catch (BadRequestException e){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }catch (ResourceNotFoundException e){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
}
