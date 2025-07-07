package com.example.mscliente.service.impl;

import com.example.mscliente.entity.Vendedor;
import com.example.mscliente.repository.VendedorRepository;
import com.example.mscliente.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendedorServiceImpl implements VendedorService {
    @Autowired
    private VendedorRepository vendedorRepository;
    @Override
    public List<Vendedor> listar(){
        return vendedorRepository.findAll();
    }
    @Override
    public Vendedor guardar(Vendedor categoria){
        return vendedorRepository.save(categoria);
    }
    @Override
    public Vendedor editar(Vendedor vendedor, Integer id) {
        if (vendedorRepository.existsById(id)) {
            vendedor.setId(id);
            return vendedorRepository.save(vendedor);

        }
        return null;
    }
    @Override
    public void eliminar(Integer id){
        vendedorRepository.deleteById(id);

    }
}
