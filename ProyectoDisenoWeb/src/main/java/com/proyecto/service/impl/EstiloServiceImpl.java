package com.proyecto.service.impl;


import com.proyecto.dao.EstiloDao;
import com.proyecto.domain.Estilo;
import com.proyecto.service.EstiloService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service 
public class EstiloServiceImpl implements EstiloService { 

    @Autowired
    private EstiloDao estiloDao; //

    @Override
    @Transactional(readOnly = true)
    public List<Estilo> getEstilos() {
        var lista = estiloDao.findAll();
        return lista;
    }

    @Override
    @Transactional(readOnly = true) //solamente se lee
    public Estilo getEstilo(Estilo estilo) {
        return estiloDao.findById(estilo.getIdEstilo()).orElse(null);
    }

    @Override
    @Transactional //es porque va a hacer un request a la tabla
    public void save(Estilo estilo) {
        estiloDao.save(estilo);
    }

    @Override
    @Transactional
    public void delete(Estilo estilo) {
        estiloDao.delete(estilo);
    }

}