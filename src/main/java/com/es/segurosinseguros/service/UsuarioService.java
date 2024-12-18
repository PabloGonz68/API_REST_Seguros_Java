package com.es.segurosinseguros.service;

import com.es.segurosinseguros.dto.UserRegisterDTO;
import com.es.segurosinseguros.exception.BadRequestException;
import com.es.segurosinseguros.exception.DuplicateException;
import com.es.segurosinseguros.model.Usuario;
import com.es.segurosinseguros.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // BUSCO EL USUARIO POR SU NOMBRE EN LA BDD
        Usuario usuario = usuarioRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario No encontrado"));

        /* RETORNAMOS UN USERDETAILS
        El método loadUserByUsername nos fuerza a devolver un objeto de tipo UserDetails.
        Tenemos que convertir nuestro objeto de tipo Usuario a un objeto de tipo UserDetails
        ¡No os preocupéis, esto es siempre igual!
         */
        List<GrantedAuthority> authorities = Arrays.stream(usuario.getRol().split(","))
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.trim()))
                .collect(Collectors.toList());

        UserDetails userDetails = User // User pertenece a SpringSecurity
                .builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .authorities(authorities)
                .build();

        return userDetails;
    }

    public UserRegisterDTO register(UserRegisterDTO user) {
        if (usuarioRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new DuplicateException("El usuario ya existe");
        }
        if (!user.getPassword1().equals(user.getPassword2())) {
            throw new BadRequestException("Las contraseñas no coinciden");
        }
        if (!user.getRol().equals("ADMIN") && !user.getRol().equals("USER") ) {
            throw new BadRequestException("El rol no es válido");
        }

        Usuario newUser = new Usuario();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword1()));
        newUser.setRol(user.getRol());
        usuarioRepository.save(newUser);
        return user;
    }
}
