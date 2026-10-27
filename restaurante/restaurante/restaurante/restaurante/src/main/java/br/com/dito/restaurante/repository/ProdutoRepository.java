package br.com.dito.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.dito.restaurante.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    // Métodos de consulta personalizados
    List<Produto> findByNomeContaining(String nome);
    List<Produto> findByPrecoBetween(Double min, Double max);
}
