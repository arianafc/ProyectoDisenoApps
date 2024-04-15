/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.service.impl;

import com.proyecto.dao.ColorDao;
import com.proyecto.domain.Color;

import com.proyecto.service.ColorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ariana
 */
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorDao colorDao;

    @Override
    public List<Color> findByidProducto(Long idProducto) {
        return colorDao.encontrarPorID(idProducto);
    }

}
