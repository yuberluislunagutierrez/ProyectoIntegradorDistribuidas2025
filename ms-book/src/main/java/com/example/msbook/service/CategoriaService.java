package com.example.msbook.service;

import com.example.msbook.entity.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    public List<Categoria> listar();
    public Categoria guardar(Categoria categoria);
    public Categoria editar(Categoria categoria, Integer id);
    public void eliminar(Integer id);
    public Optional<Categoria> listarPorId(Integer id);


}
