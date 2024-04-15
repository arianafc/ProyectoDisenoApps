/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.service;

import com.proyecto.domain.Item;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ariana
 */
public interface ItemService {
    List<Item> listaItems = new ArrayList<>(); //genera una lista con los items
    
    public List<Item> gets(); //obtiene todos los items

//recuperar el registro que tiene el IdItem pasado por parametro.
//Se obtiene por medio del idItem
    public Item get(Item item);
    
    //aqui eliminamos los items por medio de su id
    public void delete(Item item);
    
    public void save(Item item);
    
    public void actualiza(Item item);
    
    public void facturar();
}
