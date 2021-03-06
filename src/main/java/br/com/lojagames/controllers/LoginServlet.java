package br.com.lojagames.controllers;

import br.com.lojagames.dao.UsuarioDAO;
import br.com.lojagames.models.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author victor
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sessao = request.getSession();
        
        if (sessao.getAttribute("usuario") != null) {
            Usuario usuario = (Usuario) sessao.getAttribute("usuario");
            String url = usuario.montarUrl();
            
            response.sendRedirect(request.getContextPath() + url);
            return;
        }
        
        request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        Usuario usuario = UsuarioDAO.pesquisaPorEmail(email);
        
        if (usuario != null && usuario.validarSenha(senha)) {
            HttpSession sessao = request.getSession();
            sessao.setAttribute("usuario", usuario);
            String url = usuario.montarUrl();
            
            response.sendRedirect(request.getContextPath() + url);
            
        } else {
            request.setAttribute("msgErro", "Usuário ou senha incorreta");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }
}
