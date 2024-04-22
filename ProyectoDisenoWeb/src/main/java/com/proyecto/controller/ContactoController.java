package com.proyecto.controller;

/**
 *
 * @author Jorge
 */
import com.proyecto.dao.CategoriaDao;
import com.proyecto.domain.Categoria;
import com.proyecto.domain.Producto;
import com.proyecto.service.CategoriaService;
import com.proyecto.service.EstiloService;
import com.proyecto.service.FirebaseStorageService;
import com.proyecto.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/contacto")
public class ContactoController {
    @GetMapping("/contacto")
    private String Contacto() {
        return "/contacto/contacto";
    }
    
     @GetMapping("/politica")
    private String politica() {
        return "/contacto/politica";
    }
}
