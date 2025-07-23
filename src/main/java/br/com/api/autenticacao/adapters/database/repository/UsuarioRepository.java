package br.com.api.autenticacao.adapters.database.repository;

import br.com.api.autenticacao.adapters.database.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}
