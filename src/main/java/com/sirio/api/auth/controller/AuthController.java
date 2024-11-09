package com.sirio.api.auth.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sirio.api.auth.service.AuthService;
import com.sirio.api.configs.jwt.JwtTokenProvider;
import com.sirio.api.usuario.model.Usuario;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtTokenProvider tokenProvider;
    
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String cpf) {
    	Optional<Usuario> usuarioOpt = authService.loginByCpf(cpf);
    	
        if (usuarioOpt.isPresent()) {
            String token = tokenProvider.generateToken(usuarioOpt.get().getCpf());
            return ResponseEntity.ok(token);
        }
        
        return ResponseEntity.status(401).body("Credenciais inválidas");
    }
}
