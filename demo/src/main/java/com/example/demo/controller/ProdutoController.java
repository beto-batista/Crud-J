package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Produto;
import com.example.demo.model.repository.ProdutoRepository;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.core.sym.Name;

import aj.org.objectweb.asm.Attribute;

import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.engine.AttributeName;


@Controller
@RequestMapping("produto")
public class ProdutoController {
    
    @Autowired
    private ProdutoRepository repProduto;

    @GetMapping("/listar")
    public String listar(Model model){
        List<Produto> produtos = repProduto.findAll();
        model.addAttribute("produtos", produtos);
        return "modulos/produto/listar";
    }

    @GetMapping("/adicionar")
    public String adicionar(){
        return "modulos/produto/adicionar";
    }

    @PostMapping("/salvar")
    public String salvar(Produto produto, BindingResult result){
        if(result.hasFieldErrors()){
            return "redirect:/produto/adicionar";
        }
        repProduto.save(produto);
        return "redirect:/produto/listar";
    }

    @GetMapping("/delete/{id}")
    public String delete (@PathVariable(value = "id") int id, Produto produto, BindingResult result){

        if (result.hasFieldErrors()){
            return "redirect:/produto/listar";
        }

        repProduto.delete(produto);
        return "redirect:/produto/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable(value = "id") int id, Model model){
        Produto produto = repProduto.getById(id);
        model.addAttribute("produto", produto);
        return "modulos/produto/editar";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable(value = "id") int id, Produto produto, BindingResult result){
        if(result.hasFieldErrors()){
            return "redirect:/produto/editar/"+id;
        }
        repProduto.save(produto);
        return "redirect:/produto/listar";
    }

}
