package com.example.mscliente.service;

import com.example.mscliente.entity.Cliente;

import java.util.List;

public interface ClienteService {
    public List<Cliente> listar();
    public Cliente guardar(Cliente cliente);
    public Cliente buscarPorId(Integer id);
    public Cliente editar(Cliente cliente);
    public void eliminar(Integer id);
}
