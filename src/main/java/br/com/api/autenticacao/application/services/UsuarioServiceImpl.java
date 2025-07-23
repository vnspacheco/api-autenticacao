package br.com.api.autenticacao.application.services;

import br.com.api.autenticacao.adapters.database.mapper.request.UsuarioEntityRequestMapper;
import br.com.api.autenticacao.adapters.database.mapper.response.UsuarioEntityResponseMapper;
import br.com.api.autenticacao.adapters.database.repository.UsuarioRepository;
import br.com.api.autenticacao.domain.entity.Usuario;
import br.com.api.autenticacao.domain.exception.UsuarioExistingException;
import br.com.api.autenticacao.domain.exception.UsuarioUnauthorizedException;
import br.com.api.autenticacao.domain.exception.UsuarioNotFoundException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioEntityRequestMapper usuarioEntityRequestMapper;
    private final UsuarioEntityResponseMapper usuarioEntityResponseMapper;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private static final String SECRET_KEY = "uQhVYm3u9nViY6o3KrJ9T0VlKL/tQeDw";

    @Override
    public Usuario autenticar(Usuario usuario) {
        if (usuarioRepository.findById(usuario.getLogin()).isPresent()) {
            br.com.api.autenticacao.adapters.database.entity.Usuario usuarioEncontrado = usuarioRepository.findById(usuario.getLogin()).get();

            if (encoder.matches(usuario.getSenha(), usuarioEncontrado.getSenha())) {
                long expiracaoMillis = 1000 * 60 * 60;

                Key chave = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

                String token = Jwts.builder()
                        .setSubject(usuario.getLogin())
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + expiracaoMillis))
                        .signWith(SignatureAlgorithm.HS256, chave)
                        .compact();

                return usuarioEntityResponseMapper.execute(usuarioEncontrado, token);

            } else {
                throw new UsuarioUnauthorizedException("Senha inválida");
            }

        } else {
            throw new UsuarioNotFoundException("Usuário não encontrado");
        }
    }

    @Override
    public Usuario cadastrar(Usuario usuario) {
        if (usuarioRepository.findById(usuario.getLogin()).isEmpty()) {
            usuarioRepository.save(usuarioEntityRequestMapper.execute(usuario, encoder.encode(usuario.getSenha())));
            return usuario;

        } else {
            throw new UsuarioExistingException("Usuário já existente");
        }
    }
}
