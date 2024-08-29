package br.com.nicolas.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.nicolas.todolist.user.iUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private iUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
               
                // Pegar a autenticação (usuario + senha)
                var authorization = request.getHeader("Authorization");

                // Separa o "Basic" do restante da senha length para calcular os caracteres e remover com o trim
                var authEncoded = authorization.substring("Basic".length()).trim();

                // Decode da senha em base64 JavaUtil
                byte[] authDecode = Base64.getDecoder().decode(authEncoded);

                // Converter array de byte para converter para string
                var authString = new String(authDecode);

                // Separar usuario da senha em array. Ex: [zalasik] [123456]
                String[] credentials = authString.split(":");
                String username = credentials[0];
                String password = credentials[1];

                // Validar usuário
                    var user = this.userRepository.findByUsername(username);
                    if (user == null) {
                        response.sendError(401, "Usuário sem autorização");                        
                    }else {                        
                        // Validar senha
                        

                        // Segue caminho
                        filterChain.doFilter(request, response);
                    }
            }
}