/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.service.impl;

import com.proyecto.dao.TallasDao;

import com.proyecto.domain.Tallas;
import com.proyecto.service.TallasService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ariana
 */
public class TallasServiceImpl implements TallasService {

    @Autowired
    private TallasDao tallasDao;

    @Override
    public List<Tallas> findByidProducto(Long idProducto) {
        return tallasDao.encontrarPorID(idProducto);
    }

}
