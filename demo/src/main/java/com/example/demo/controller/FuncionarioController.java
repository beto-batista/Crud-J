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

import com.example.demo.model.Funcionario;
import com.example.demo.model.repository.FuncionarioRepository;
import com.fasterxml.jackson.core.sym.Name;

import aj.org.objectweb.asm.Attribute;

import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.engine.AttributeName;


@Controller
@RequestMapping("funcionario")
public class FuncionarioController {
    
    @Autowired
    private FuncionarioRepository repFuncionario;

    @GetMapping("/listar")
    public String listar(Model model){
        List<Funcionario> funcionarios = repFuncionario.findAll();
        model.addAttribute("funcionarios", funcionarios);
        return "modulos/funcionario/listar";
    }

    @GetMapping("/adicionar")
    public String adicionar(){
        return "modulos/funcionario/adicionar";
    }

    @PostMapping("/salvar")
    public String salvar(Funcionario funcionario, BindingResult result){
        if(result.hasFieldErrors()){
            return "redirect:/funcionario/adicionar";
        }
        repFuncionario.save(funcionario);
        return "redirect:/funcionario/listar";
    }

    @GetMapping("/delete/{id}")
    public String delete (@PathVariable(value = "id") int id, Funcionario funcionario, BindingResult result){

        if (result.hasFieldErrors()){
            return "redirect:/funcionario/listar";
        }

        repFuncionario.delete(funcionario);
        return "redirect:/funcionario/listar";
    }

}
