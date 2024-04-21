package com.proyecto.controller;

import com.proyecto.domain.Usuario;
import com.proyecto.service.FirebaseStorageService;
import com.proyecto.service.RegistroService;
import com.proyecto.service.UsuarioService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
@RequestMapping("/registro")
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/nuevo")
    public String nuevo(Model model, Usuario usuario) {
        return "/registro/nuevo";
    }

    @GetMapping("/recordar")
    public String recordar(Model model, Usuario usuario) {
        return "/registro/recordar";
    }

    @PostMapping("/crearUsuario")
    public String crearUsuario(Model model, Usuario usuario)
            throws MessagingException {
        model = registroService.crear(model, usuario);
        return "/registro/salida";
    }

    @GetMapping("/activacion/{usuario}/{id}")
    public String activar(
            Model model,
            @PathVariable(value = "usuario") String usuario,
            @PathVariable(value = "id") String id) {
        model = registroService.activar(model, usuario, id);
        if (model.containsAttribute("usuario")) {
            return "/registro/activa";
        } else {
            return "/registro/salida";
        }
    }

    @Autowired
    FirebaseStorageService firebaseStorageService;

    @PostMapping("/activar")
    public String activar(
            Usuario usuario,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        Usuario usuarioExistente = usuarioService.getUsuarioPorUsername(usuario.getUsername());

        // Verificar si el usuario existe
        if (usuarioExistente != null) {
            usuarioExistente.setActivo(true);
            var codigo = new BCryptPasswordEncoder();//aqui encript el password
            usuarioExistente.setPassword(codigo.encode(usuario.getPassword()));
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
        return "redirect:/";
    }

    @PostMapping("/recordarUsuario")
    public String recordarUsuario(Model model, Usuario usuario)
            throws MessagingException {
        model = registroService.recordarUsuario(model, usuario);
        return "/registro/salida";
    }
}
