package br.com.api.autenticacao.application.services;

import br.com.api.autenticacao.adapters.beans.JwtConfiguration;
import br.com.api.autenticacao.adapters.database.mapper.request.UsuarioEntityRequestMapper;
import br.com.api.autenticacao.adapters.database.mapper.response.UsuarioEntityResponseMapper;
import br.com.api.autenticacao.adapters.database.repository.UsuarioRepository;
import br.com.api.autenticacao.domain.entity.Usuario;
import br.com.api.autenticacao.domain.exception.UsuarioExistingException;
import br.com.api.autenticacao.domain.exception.UsuarioNotFoundException;
import br.com.api.autenticacao.domain.exception.UsuarioUnauthorizedException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioEntityRequestMapper usuarioEntityRequestMapper;
    private final UsuarioEntityResponseMapper usuarioEntityResponseMapper;
    private final JwtConfiguration jwtConfiguration;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public Usuario autenticar(Usuario usuario) {
        if (usuarioRepository.findById(usuario.getLogin()).isPresent()) {
            br.com.api.autenticacao.adapters.database.entity.Usuario usuarioEncontrado = usuarioRepository
                    .findById(usuario.getLogin()).get();

            if (encoder.matches(usuario.getSenha(), usuarioEncontrado.getSenha())) {
                Algorithm algorithm = Algorithm.HMAC256(jwtConfiguration.getSecret());

                String token = JWT.create()
                        .withIssuer(jwtConfiguration.getIssuer())
                        .withSubject(usuario.getLogin())
                        .withClaim("scope", usuarioEncontrado.getScope() != null ? usuarioEncontrado.getScope() : "default")
                        .withExpiresAt(LocalDateTime.now().plusHours(jwtConfiguration.getExpirationHours()).toInstant(ZoneOffset.of("-03:00")))
                        .sign(algorithm);

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
