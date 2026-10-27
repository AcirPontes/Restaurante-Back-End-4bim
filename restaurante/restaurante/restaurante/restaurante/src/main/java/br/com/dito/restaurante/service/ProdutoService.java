package br.com.dito.restaurante.service;

import org.springframework.beans.factory.annotation.Autowired;

import dto.ProdutoDTO;
import br.com.dito.restaurante.model.Produto;
import br.com.dito.restaurante.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService{

    @Autowired
    private ProdutoRepository produtoRepository;

    // Listar todos os produtos
    public List<ProdutoDTO> listarTodos(){
        return produtoRepository.findAll()
                .stream()
                .map((Produto p) -> new ProdutoDTO(p.getId(), p.getNome(), p.getPreco()))
                .collect(Collectors.toList());
    }

    // Adicionar novo produto
    public ProdutoDTO adicionarProduto(ProdutoDTO dto){
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto = produtoRepository.save(produto);
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco());
    }

    // Atualizar produto
    public ProdutoDTO atualizarProduto(Long id, ProdutoDTO dto){
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto = produtoRepository.save(produto);
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco());
    }

    // Deletar produto
    public void deletarProduto(Long id){
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produtoRepository.delete(produto);
    }

    // Buscar produtos pelo nome
    public List<ProdutoDTO> buscarPorNome(String nome){
        return produtoRepository.findByNomeContaining(nome)
                .stream()
                .map((Produto p) -> new ProdutoDTO(p.getId(), p.getNome(), p.getPreco()))
                .collect(Collectors.toList());
    }

    // Buscar produtos entre dois preços
    public List<ProdutoDTO> buscarProdutosEntrePrecos(Double min, Double max){
        return produtoRepository.findByPrecoBetween(min, max)
                .stream()
                .map((Produto p) -> new ProdutoDTO(p.getId(), p.getNome(), p.getPreco()))
                .collect(Collectors.toList());
    }

    // Buscar produtos pelo ID
    public ProdutoDTO buscarPorId(Long id) {
            Produto produto = produtoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco());
    }

}