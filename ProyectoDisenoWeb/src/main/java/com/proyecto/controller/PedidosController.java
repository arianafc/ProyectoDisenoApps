/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controller;

import com.proyecto.domain.Factura;
import com.proyecto.domain.Usuario;
import com.proyecto.service.FacturaService;
import com.proyecto.service.UsuarioService;
import com.proyecto.service.VentaService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pedidos")
public class PedidosController {
    @Autowired
    private FacturaService facturaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private VentaService ventaService;
    
     @GetMapping("/listado")
    public String listado(Model model, Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioService.getUsuarioPorUsername(username);
        var facturas = facturaService.findByIdUsuario(usuario.getIdUsuario());
        model.addAttribute("facturas", facturas);
        model.addAttribute("totalFacturas", facturas.size());
        return "pedidos/listado";
    }
    
    @GetMapping("/rastrearPedido/{idFactura}")
    public String rastrearPedido(Model model, @PathVariable("idFactura") Long idFactura, Principal principal){
        String username = principal.getName();
        Usuario usuario = usuarioService.getUsuarioPorUsername(username);
        var ventas = ventaService.findByIdFactura(idFactura);
        Factura factura = facturaService.findByIdFactura(idFactura);
        model.addAttribute("factura", factura);
        model.addAttribute("ventas", ventas);
        model.addAttribute("usuario", usuario);
        return "pedidos/rastrearPedido";
    }
    
     @GetMapping("/vendedor")
    public String vendedor(Model model) {
        var facturas = facturaService.getRols();
        model.addAttribute("facturas", facturas);
        model.addAttribute("totalFacturas", facturas.size());
        return "pedidos/vendedor";
    }
    @GetMapping("/eliminar/{idFactura}")
    public String facturaEliminar(@PathVariable("idFactura") Long idFactura) {
        facturaService.delete(idFactura);
        return "redirect:/pedidos/vendedor";
    }
    
    @GetMapping("/modificar/{idFactura}")
    public String facturaModificar(@PathVariable("idFactura") Factura factura, Model model) {
        factura = facturaService.getFactura(factura);
        model.addAttribute("factura", factura);
        return "pedidos/modificar";
    }
    
    @PostMapping("/guardar")
    public String facturaGuardar(Factura factura) {
        facturaService.save(factura);
        return "redirect:/pedidos/vendedor";
    }
}
