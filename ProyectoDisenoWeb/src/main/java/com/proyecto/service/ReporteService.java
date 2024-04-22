/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.service;

import java.io.IOException;
import java.util.Map;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author perezer
 */
public interface ReporteService {

    public ResponseEntity<Resource>
            generaReporte(
                    String reporte, //El nombre del archivo llamado .jasper
                    Map<String, Object> parametros, //Llamar a los parametros que tenemos en el reporte
                    String tipo) throws IOException;//Cuales van a ser las opciones de reportes para mi reporte (csv, pdf, excel o un txt)

}
