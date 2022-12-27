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

import com.example.demo.model.Marca;
import com.example.demo.model.repository.MarcaRepository;
import com.fasterxml.jackson.core.sym.Name;

import aj.org.objectweb.asm.Attribute;

import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.engine.AttributeName;


@Controller
@RequestMapping("marca")
public class MarcaController {
    
    @Autowired
    private MarcaRepository repMarca;

    @GetMapping("/listar")
    public String listar(Model model){
        List<Marca> marcas = repMarca.findAll();
        model.addAttribute("marcas", marcas);
        return "modulos/marca/listar";
    }

    @GetMapping("/adicionar")
    public String adicionar(){
        return "modulos/marca/adicionar";
    }

    @PostMapping("/salvar")
    public String salvar(Marca marca, BindingResult result){
        if(result.hasFieldErrors()){
            return "redirect:/marca/adicionar";
        }
        repMarca.save(marca);
        return "redirect:/marca/listar";
    }

    @GetMapping("/delete/{id}")
    public String delete (@PathVariable(value = "id") int id, Marca marca, BindingResult result){

        if (result.hasFieldErrors()){
            return "redirect:/marca/listar";
        }

        repMarca.delete(marca);
        return "redirect:/marca/listar";
    }

}
