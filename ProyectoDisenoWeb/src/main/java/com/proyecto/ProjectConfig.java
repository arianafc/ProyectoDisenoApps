package com.proyecto;

import com.proyecto.dao.ColorDao;
import com.proyecto.dao.FacturaDao;
import com.proyecto.dao.TallasDao;
import com.proyecto.dao.VentaDao;
import com.proyecto.domain.Color;
import com.proyecto.domain.Factura;
import com.proyecto.domain.Tallas;
import com.proyecto.domain.Venta;
import com.proyecto.service.ColorService;
import com.proyecto.service.FacturaService;
import com.proyecto.service.TallasService;
import com.proyecto.service.VentaService;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class ProjectConfig implements WebMvcConfigurer {

    @Autowired
    private TallasDao tallasDao;
    @Autowired
    private ColorDao colorDao;
    @Autowired
    private FacturaDao facturaDao;
    @Autowired
    private VentaDao ventaDao;

    @Bean
    public VentaService ventaService() {
        return new VentaService() {
            @Override
            public List<Venta> getVentas() {
                return ventaDao.findAll();
            }

            @Override
            public Venta getVenta(Venta venta) {
                return ventaDao.findById(venta.getIdFactura()).orElse(null);
            }

            @Override
            public void saveVenta(Venta venta) {
                ventaDao.save(venta);
            }

            @Override
            public void delete(Long idVenta) {
                ventaDao.deleteById(idVenta);
            }

        };
    }

    @Bean
    public FacturaService facturaService() {
        return new FacturaService() {
            @Override

            public List<Factura> getRols() {
                return facturaDao.findAll();
            }

            @Override
            public Factura getFactura(Factura factura) {
                return facturaDao.findById(factura.getIdFactura()).orElse(null);
            }

            @Override
            public void save(Factura factura) {
                facturaDao.save(factura);
            }

            @Override
            public void delete(Long idFactura) {
                facturaDao.deleteById(idFactura);
            }

            @Override
            public List<Factura> findByIdUsuario(Long idUsuario) {
                return facturaDao.encontrarPorUsuario(idUsuario);
            }
        };
    }

    @Bean
    public TallasService tallasService() {
        return new TallasService() {
            @Override
            public List<Tallas> findByidProducto(Long idProducto) {
                return tallasDao.encontrarPorID(idProducto);
            }
        };
    }

    @Bean
    public ColorService colorService() {
        return new ColorService() {
            @Override
            public List<Color> findByidProducto(Long idProducto) {
                return colorDao.encontrarPorID(idProducto);
            }
        };
    }

    /* Los siguientes métodos son para hacer uso de Internacionalización */
    @Bean
    public LocaleResolver localeResolver() {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        slr.setLocaleAttributeName("session.current.locale");
        slr.setTimeZoneAttributeName("session.current.timezone");
        return slr;
    }

    @Bean

    public LocaleChangeInterceptor localeChangeInterceptor() {
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registro) {
        registro.addInterceptor(localeChangeInterceptor());
    }

    /*Bean para utilizar los textos de mensajes en una clase Java*/
    @Bean("messagesSource")
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /* Los siguiente métodos son para implementar el tema de seguridad dentro del proyecto */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registro/nuevo").setViewName("/registro/nuevo");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeHttpRequests((request) -> request
                .requestMatchers("/", "/index", "/errores/**",
                        "/producto/**",
                        "/registro/**", "/carrito/**", "/inicioSesion/**", "/js/**", "/webjars/**")
                .permitAll()
                .requestMatchers(
                        "/producto/nuevo", "/inicioSesion/**", "/producto/guardar",
                        "/producto/modificar/**", "/producto/eliminar/**",
                        "/categoria/nuevo", "/categoria/guardar",
                        "/categoria/modificar/**", "/categoria/eliminar/**",
                        "/usuario/nuevo", "/usuario/guardar", "/usuario/listado",
                        "/usuario/modificar/*", "/usuario/modificarUsuario/*", "/usuario/eliminar/**", "/usuario/adminInicio", "/usuario/eliminarUsuario/*", "/usuario/modificarUsuario/*",
                        "/reportes/**", "/usuario/actualizarPassword", "/usuario/actualizarUsuario"
                ).hasRole("ADMIN")
                .requestMatchers(
                        "/producto/listado",
                        "/categoria/listado",
                        "/usuario/listado"
                ).hasAnyRole("ADMIN", "VENDEDOR")
                .requestMatchers(
                        "/usuario/crearCuenta", "/carrito/listado","/usuario/dashboard", "/inicioSesion/**", "/usuario/miCuenta", "/usuario/actualizarPassword", "/usuario/guardarUsuario"
                ).hasAnyRole("ADMIN", "VENDEDOR", "USER")
                .requestMatchers("/facturar/carrito","/carrito/orden", "/index","/pedidos/listado", "/producto/vistaProducto/Mujer/Lifestyle", "/producto/vistaProducto/Hombre/*", "/producto/vistaProducto/Nin@",
                        "/producto/guiaTallas", "/producto/vistaProductoDetalle/*", "/contacto/contacto", "/carrito/listado", "/usuario/guardarUsuario", "/producto/guardarComentario")
                .hasRole("USER")
                )
                .formLogin((form) -> form
                .loginPage("/login").permitAll())
                .logout((logout) -> logout.permitAll());
        return http.build();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build)
            throws Exception {
        build
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
