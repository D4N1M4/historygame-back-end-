package br.com.ifpe.historygame.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.historygame.dto.UsuarioDTO;
import br.com.ifpe.historygame.entity.Usuario;
import br.com.ifpe.historygame.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody UsuarioDTO dto) {
        Optional<Usuario> existente = usuarioRepository.findByUid(dto.getUid());

        if (existente.isPresent()) {
            return ResponseEntity.ok("Usuário já registrado");
        }

        Usuario novo = new Usuario();
        novo.setUid(dto.getUid());
        novo.setNome(dto.getNome());
        novo.setEmail(dto.getEmail());
        if (dto.getSenha() != null) {
            novo.setSenha(dto.getSenha());
        }        
        usuarioRepository.save(novo);

        return ResponseEntity.ok("Usuário registrado com sucesso");
    }

}
