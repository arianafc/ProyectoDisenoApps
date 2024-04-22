/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controller;

import com.proyecto.domain.Rol;
import com.proyecto.domain.Usuario;
import com.proyecto.service.FacturaService;
import com.proyecto.service.FirebaseStorageService;
import com.proyecto.service.RolService;
import com.proyecto.service.UsuarioService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Ariana
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RolService rolService;
    @Autowired
    private FacturaService facturaService;
    
    @GetMapping("/inicio")
    private String inicio() {
        return "/usuario/inicio";
    }

    @GetMapping("/adminInicio")
    private String adminInicio(Model model, Authentication authentication) {
        String username = authentication.getName();
        Usuario usuario = usuarioService.getUsuarioPorUsername(username);
        model.addAttribute("usuario", usuario);
        var facturas = facturaService.getRols();
        var facturasPendientes = facturaService.findByEstado("Pendiente");
        var facturasNuevas =  facturaService.findByEstado("Nueva Orden");
        var facturasEntregadas =  facturaService.findByEstado("Entregada");
        model.addAttribute("facturas", facturas);
        model.addAttribute("totalFacturas", facturas.size());
        model.addAttribute("totalFacturasPendientes", facturasPendientes.size()+facturasNuevas.size());
        model.addAttribute("totalFacturasEntregadas", facturasEntregadas.size());
        return "/usuario/adminInicio";
    }

    @GetMapping("/vendedorInicio")
    private String vendedorInicio() {
        return "/usuario/vendedorInicio";
    }

    @GetMapping("/miCuenta")
    private String miCuenta(Model model, Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioService.getUsuarioPorUsername(username);
        model.addAttribute("usuario", usuario);
        return "/usuario/miCuenta";
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        var roles = rolService.getRols();
        var usuarios = usuarioService.getUsuarios();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("roles", roles);
        return "usuario/listado";
    }

    @PostMapping("/guardar")
    public String rolGuardar(Rol rol) {
        rolService.saveRol(rol);
        return "/usuario/listado";
    }

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @PostMapping("/guardarUsuario")
    public String usuarioGuardar(Usuario usuario,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        // Obtener el usuario existente desde la base de datos
        Usuario usuarioExistente = usuarioService.getUsuarioPorUsername(usuario.getUsername());

        // Verificar si el usuario existe
        if (usuarioExistente != null) {
            // Copiar los datos del usuario enviado al usuario existente
            usuarioExistente.setCedula(usuario.getCedula());
            usuarioExistente.setUsername(usuario.getUsername());
            usuarioExistente.setApellidos(usuario.getApellidos());
            usuarioExistente.setNombre(usuario.getNombre());
            usuarioExistente.setEmail(usuario.getEmail());
            usuarioExistente.setTelefono(usuario.getTelefono());
            usuarioExistente.setDireccion(usuario.getDireccion());

            // Verificar si se envió una nueva imagen
            if (!imagenFile.isEmpty()) {
                // Actualizar la ruta de la imagen y guardar la imagen
                usuarioExistente.setRutaImagen(firebaseStorageService.cargaImagen(
                        imagenFile, "usuario", usuario.getIdUsuario()));
            }

            // Guardar los cambios en el usuario
            usuarioService.saveUsuario(usuarioExistente);
        }

        return "redirect:/usuario/miCuenta";
    }
    
        @PostMapping("/actualizarUsuario")
    public String actualizarUsuario(Usuario usuario,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        // Obtener el usuario existente desde la base de datos
        Usuario usuarioExistente = usuarioService.getUsuarioPorUsername(usuario.getUsername());

        // Verificar si el usuario existe
        if (usuarioExistente != null) {
            // Copiar los datos del usuario enviado al usuario existente
            usuarioExistente.setCedula(usuario.getCedula());
            usuarioExistente.setUsername(usuario.getUsername());
            usuarioExistente.setApellidos(usuario.getApellidos());
            usuarioExistente.setNombre(usuario.getNombre());
            usuarioExistente.setEmail(usuario.getEmail());
            usuarioExistente.setTelefono(usuario.getTelefono());
            usuarioExistente.setDireccion(usuario.getDireccion());

            // Verificar si se envió una nueva imagen
            if (!imagenFile.isEmpty()) {
                // Actualizar la ruta de la imagen y guardar la imagen
                usuarioExistente.setRutaImagen(firebaseStorageService.cargaImagen(
                        imagenFile, "usuario", usuario.getIdUsuario()));
            }

            // Guardar los cambios en el usuario
            usuarioService.saveUsuario(usuarioExistente);
        }

        return "redirect:/usuario/adminInicio";
    }
    
    
    @GetMapping("/eliminar/{idRol}")
    public String rolEliminar(@PathVariable("idRol") Long idRol) {
        rolService.delete(idRol);
        return "redirect:usuario/listado";
    }
//

    @GetMapping("/eliminarUsuario/{idUsuario}")
    public String usurarioEliminar(@PathVariable("idUsuario") Long idUsuario) {
        usuarioService.delete(idUsuario);
        return "usuario/listado";
    }

    @GetMapping("/modificar/{idRol}")
    public String rolModificar(@PathVariable("idRol") Rol rol, Model model) {
        rol = rolService.getRol(rol);

        var roles = rolService.getRols();
        model.addAttribute("rol", rol);
        model.addAttribute("roles", roles);

        return "usuario/modificar";
    }

    @GetMapping("/modificarUsuario/{idUsuario}")
    public String usuarioModificar(@PathVariable("idUsuario") Usuario usuario, Model model) {
        usuario = usuarioService.getUsuario(usuario);

        var usuarios = usuarioService.getUsuarios();
        model.addAttribute("usuario", usuario);
        model.addAttribute("usuarios", usuarios);

        return "usuario/modificarUsuario";
    }

 @GetMapping("/dashboard")
    private String dashboard(Model model, Authentication authentication) {
        String username = authentication.getName();
        Usuario usuario = usuarioService.getUsuarioPorUsername(username);
        model.addAttribute("usuario", usuario);
        var facturas = facturaService.findByIdUsuario(usuario.getIdUsuario());
        var facturasPendientes = facturaService.findByIdUsuarioYEstado(usuario.getIdUsuario(), "Pendiente");
        var facturasNuevas = facturaService.findByIdUsuarioYEstado(usuario.getIdUsuario(), "Nueva Orden");
        var facturasEntregadas = facturaService.findByIdUsuarioYEstado(usuario.getIdUsuario(), "Entregada");
        model.addAttribute("facturas", facturas);
        model.addAttribute("totalFacturas", facturas.size());
        model.addAttribute("totalFacturasPendientes", facturasPendientes.size()+facturasNuevas.size());
        model.addAttribute("totalFacturasEntregadas", facturasEntregadas.size());
        return "/usuario/dashboard";
    }
}
