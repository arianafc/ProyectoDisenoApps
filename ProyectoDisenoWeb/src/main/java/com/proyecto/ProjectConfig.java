package com.proyecto;

import com.proyecto.dao.ColorDao;
import com.proyecto.dao.TallasDao;
import com.proyecto.domain.Color;
import com.proyecto.domain.Tallas;
import com.proyecto.service.ColorService;
import com.proyecto.service.TallasService;
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
                        "/registro/**","/carrito/**", "/inicioSesion/**","/js/**", "/webjars/**")
                .permitAll()
                .requestMatchers(
                        "/producto/nuevo", "/inicioSesion/**","/producto/guardar",
                        "/producto/modificar/**", "/producto/eliminar/**",
                        "/categoria/nuevo", "/categoria/guardar",
                        "/categoria/modificar/**", "/categoria/eliminar/**",
                        "/usuario/nuevo", "/usuario/guardar","/usuario/asignarRol",
                        "/usuario/modificar/*", "/usuario/eliminar/**", "/usuario/adminInicio",
                        "/reportes/**"
                ).hasRole("ADMIN")
                .requestMatchers(
                        "/producto/listado",
                        "/categoria/listado",
                        "/usuario/listado"
                ).hasAnyRole("ADMIN", "VENDEDOR")
                .requestMatchers(
                        "/usuario/crearCuenta", "/carrito/listado", "/inicioSesion/**"
                ).hasAnyRole("ADMIN", "VENDEDOR", "USER")        
                .requestMatchers("/facturar/carrito", "/index", "/producto/vistaProducto/Mujer/Lifestyle", "/producto/vistaProducto/Hombre/*", "/producto/vistaProducto/Nin@"
                , "/producto/guiaTallas", "/producto/vistaProductoDetalle/*", "/contacto/contacto", "/carrito/listado")
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
