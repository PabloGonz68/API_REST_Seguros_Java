package com.es.segurosinseguros.controller;

import com.es.segurosinseguros.dto.UserLoginDTO;
import com.es.segurosinseguros.dto.UserRegisterDTO;
import com.es.segurosinseguros.exception.BadRequestException;
import com.es.segurosinseguros.exception.ErrorMessageForClient;
import com.es.segurosinseguros.exception.ResourceNotFoundException;
import com.es.segurosinseguros.model.Usuario;
import com.es.segurosinseguros.service.TokenService;
import com.es.segurosinseguros.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDTO userLoginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginDTO.getUsername(),
                            userLoginDTO.getPassword()
                    )
            );
            String token = "";
            try {
                token = tokenService.generateToken(authentication);
            } catch (Exception e) {
                throw e;
            }
            return token;
        } catch (AuthenticationException e) {
            return "Error de autenticación: " + e.getMessage();
        } catch (Exception e) {
            return "Error inesperado: " + e.getMessage();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        try {
            usuarioService.register(userRegisterDTO);
            return new ResponseEntity<>(userRegisterDTO, HttpStatus.OK);
        } catch (BadRequestException e) {
            ErrorMessageForClient errorMessageForClient = new ErrorMessageForClient(e.getMessage(), "/register");
            return new ResponseEntity<>(errorMessageForClient, HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException e) {
            ErrorMessageForClient errorMessageForClient = new ErrorMessageForClient(e.getMessage(), "/register");
            return new ResponseEntity<>(errorMessageForClient, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ErrorMessageForClient errorMessageForClient = new ErrorMessageForClient(e.getMessage(), "/register");
            return new ResponseEntity<>(errorMessageForClient, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
