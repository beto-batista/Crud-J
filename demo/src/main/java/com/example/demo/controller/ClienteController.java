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

import com.example.demo.model.Cliente;
import com.example.demo.model.repository.ClienteRepository;

@Controller
@RequestMapping("cliente")
public class ClienteController {
 
    @Autowired
    private ClienteRepository repCliente;

    @GetMapping("/listar")
    public String listar(Model model){
        List<Cliente> clientes = repCliente.findAll();
        model.addAttribute("clientes", clientes);
        return "modulos/cliente/listar";
    }

    @GetMapping("/adicionar")
    public String adicionar(){
        return "modulos/cliente/adicionar";
    }

    @PostMapping("/salvar")
    public String salvar(Cliente cliente, BindingResult result){
        if(result.hasFieldErrors()){
            return "redirect:/cliente/adicionar";
        }
        repCliente.save(cliente);
        return "redirect:/cliente/listar";
    }

    @GetMapping("/delete/{id}")
    public String delete (@PathVariable(value = "id") int id, Cliente cliente, BindingResult result){

        if (result.hasFieldErrors()){
            return "redirect:/cliente/listar";
        }

        repCliente.delete(cliente);
        return "redirect:/cliente/listar";
    }
}
