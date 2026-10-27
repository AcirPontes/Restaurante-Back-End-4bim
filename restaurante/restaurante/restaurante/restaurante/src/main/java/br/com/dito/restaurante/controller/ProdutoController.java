package br.com.dito.restaurante.controller;
import dto.ProdutoDTO;
import br.com.dito.restaurante.service.ProdutoService;
//import br.com.dito.restaurante.model.Produto;
//import br.com.dito.restaurante.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//import java.util.List;
//import java.util.stream.Collectors;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    //private final ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public String listar(Model model){
        model.addAttribute("produtos", produtoService.listarTodos());
        return "produto/lista";
    }

    @GetMapping("/cadastrar")
    public String novoProduto(Model model){
        model.addAttribute("produto", new ProdutoDTO());
        return "produto/formCadastrar";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute ProdutoDTO dto) {
        if (dto.getId() == null) {
            produtoService.adicionarProduto(dto);
        } else {
            produtoService.atualizarProduto(dto.getId(), dto);
        }
        return "redirect:/produtos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        ProdutoDTO dto = produtoService.buscarPorId(id);
        model.addAttribute("produto", dto);
        return "produto/formCadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return "redirect:/produtos";
    }

}
