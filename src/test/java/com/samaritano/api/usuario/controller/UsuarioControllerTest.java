package com.samaritano.api.usuario.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samaritano.api.configs.SecurityConfigTest;
import com.samaritano.api.configs.jwt.JwtAuthenticationFilter;
import com.samaritano.api.configs.jwt.JwtTokenUtil;
import com.samaritano.api.usuario.model.Usuario;
import com.samaritano.api.usuario.service.UsuarioService;
import com.samaritano.api.usuario.utils.UsuarioUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
@Import(SecurityConfigTest.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;
    
    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private ObjectMapper objectMapper;
    
    private String token;

    @BeforeEach
    public void setUp() {
        token = JwtTokenUtil.generateToken("12345678901");
    }

    @Test
    @WithMockUser(username = "12345678901")
    void listarTodos() throws Exception {    	
        List<Usuario> usuarios = Arrays.asList(
                UsuarioUtils.createComId(1L, "12345678901", "João", null),
                UsuarioUtils.createComId(2L, "98765432109", "Maria", null)
        );

        Mockito.when(usuarioService.findAll()).thenReturn(usuarios);

        mockMvc.perform(get("/api/usuarios")
        		.header("Authorization", token))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "12345678901")
    void listarTodosAtivos() throws Exception {        
        List<Usuario> usuariosAtivos = Arrays.asList(
                UsuarioUtils.createComId(1L, "12345678901", "João", null)
        );

        Mockito.when(usuarioService.findAllActive()).thenReturn(usuariosAtivos);

        mockMvc.perform(get("/api/usuarios/ativos")
        		.header("Authorization", token))
                .andExpect(status().isOk());
    }

    @Test
    void buscarPorId() throws Exception {        
        Usuario usuario = UsuarioUtils.createComId(1L, "12345678901", "João", null);

        Mockito.when(usuarioService.findById(1L)).thenReturn(usuario);

        mockMvc.perform(get("/api/usuarios/1")
        		.header("Authorization", token))
                .andExpect(status().isOk());
    }

    @Test
    void buscarPorCpf() throws Exception {        
        Usuario usuario = UsuarioUtils.createComId(1L, "12345678901", "João", null);

        Mockito.when(usuarioService.findByCpf("12345678901")).thenReturn(usuario);

        mockMvc.perform(get("/api/usuarios/cpf/12345678901")
        		.header("Authorization", token))
                .andExpect(status().isOk());
    }

    @Test
    void criarUsuario() throws Exception {
        Usuario usuarioCriado = UsuarioUtils.createComId(1L, "12345678901", "João", null);
        Mockito.when(usuarioService.create(any())).thenReturn(usuarioCriado);

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioCriado))
                		.header("Authorization", token))
                .andExpect(status().isOk());
    }

    @Test
    void atualizarUsuario() throws Exception {        
        Usuario usuarioAtualizado = UsuarioUtils.createComId(1L, "12345678901", "João Atualizado", null);

        Mockito.when(usuarioService.update(eq(1L), any())).thenReturn(usuarioAtualizado);

        mockMvc.perform(put("/api/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioAtualizado))
                		.header("Authorization", token))
                .andExpect(status().isOk());
    }

    @Test
    void removerUsuario() throws Exception {        
        Mockito.doNothing().when(usuarioService).delete(1L);

        mockMvc.perform(delete("/api/usuarios/1")
        		.header("Authorization", token))
                .andExpect(status().isOk());
    }
}
