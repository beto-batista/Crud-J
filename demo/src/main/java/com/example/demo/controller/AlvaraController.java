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

import com.example.demo.model.Alvara;
import com.example.demo.model.repository.AlvaraRepository;
import com.fasterxml.jackson.core.sym.Name;

import aj.org.objectweb.asm.Attribute;

import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.engine.AttributeName;


@Controller
@RequestMapping("alvara")
public class AlvaraController {
    
    @Autowired
    private AlvaraRepository repAlvara;

    @GetMapping("/listar")
    public String listar(Model model){
        List<Alvara> alvaras = repAlvara.findAll();
        model.addAttribute("alvaras", alvaras);
        return "modulos/alvara/listar";
    }

    @GetMapping("/adicionar")
    public String adicionar(){
        return "modulos/alvara/adicionar";
    }

    @PostMapping("/salvar")
    public String salvar(Alvara alvara, BindingResult result){
        if(result.hasFieldErrors()){
            return "redirect:/alvara/adicionar";
        }
        repAlvara.save(alvara);
        return "redirect:/alvara/listar";
    }

    @GetMapping("/delete/{id}")
    public String delete (@PathVariable(value = "id") int id, Alvara alvara, BindingResult result){

        if (result.hasFieldErrors()){
            return "redirect:/alvara/listar";
        }

        repAlvara.delete(alvara);
        return "redirect:/alvara/listar";
    }
}
