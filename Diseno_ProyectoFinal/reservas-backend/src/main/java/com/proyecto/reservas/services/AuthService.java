package com.proyecto.reservas.services;

import java.time.Instant;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.reservas.dtos.auth.LoginRequest;
import com.proyecto.reservas.dtos.auth.LoginResponse;
import com.proyecto.reservas.dtos.auth.RegisterRequest;
import com.proyecto.reservas.dtos.auth.RegisterResponse;
import com.proyecto.reservas.exceptions.BadRequestException;
import com.proyecto.reservas.models.User;
import com.proyecto.reservas.repositories.UserRepository;
import com.proyecto.reservas.security.JwtTokenUtil;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getCorreo())) {
            throw new BadRequestException("El correo ya existe");
        }
        if (!request.getContraseña().equals(request.getConfirmacion())) {
            throw new BadRequestException("La confirmación no coincide");
        }
        User u = new User();
        u.setName(request.getNombre());
        u.setEmail(request.getCorreo());
        u.setPassword(passwordEncoder.encode(request.getContraseña()));
        u.setCreatedAt(Instant.now());
        u.getRoles().add("USER");
        userRepository.save(u);
        return new RegisterResponse(u.getId(), u.getName(), u.getEmail(), u.getCreatedAt());
    }

    public LoginResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getContraseña()));
            String token = jwtTokenUtil.generateToken(request.getCorreo());
            return new LoginResponse(token, "Bearer", jwtTokenUtil.getExpirationMs());
        } catch (AuthenticationException ex) {
            throw ex;
        }
    }
}
