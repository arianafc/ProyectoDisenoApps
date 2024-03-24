package com.proyecto.service.impl;


import com.proyecto.dao.CategoriaDao;
import com.proyecto.domain.Categoria;
import com.proyecto.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service 
public class CategoriaServiceImpl implements CategoriaService { 

    @Autowired
    private CategoriaDao categoriaDao; //

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> getCategorias() {
        var lista = categoriaDao.findAll();
        return lista;
    }

    @Override
    @Transactional(readOnly = true) //solamente se lee
    public Categoria getCategoria(Categoria categoria) {
        return categoriaDao.findById(categoria.getIdCategoria()).orElse(null);
    }

    @Override
    @Transactional //es porque va a hacer un request a la tabla
    public void save(Categoria categoria) {
        categoriaDao.save(categoria);
    }

    @Override
    @Transactional
    public void delete(Categoria categoria) {
        categoriaDao.delete(categoria);
    }

}