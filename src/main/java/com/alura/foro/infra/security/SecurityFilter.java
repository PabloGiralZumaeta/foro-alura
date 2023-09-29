package com.alura.foro.infra.security;

import com.alura.foro.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService  tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Obtener el token del header
        var authHeader = request.getHeader("Authorization");
        if(authHeader != null){
            var token =authHeader.replace("Bearer ", "");
            // Obtenemos el usuario-Login
            var nombreUsuario = tokenService.getSubject(token); // extrae el username
            if(nombreUsuario!=null){
                // token valido
                var usuario = usuarioRepository.findByLogin(nombreUsuario);

                var authentication = new UsernamePasswordAuthenticationToken(usuario,
                        null,
                        usuario.getAuthorities()); //forzamos un inicio de sesion
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        filterChain.doFilter(request,response);

    }
}
