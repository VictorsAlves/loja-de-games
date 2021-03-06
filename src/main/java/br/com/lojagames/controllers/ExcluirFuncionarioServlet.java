package br.com.lojagames.controllers;

import br.com.lojagames.dao.FuncionarioDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author victor
 */
@WebServlet(name = "ExcluirFuncionarioServlet", urlPatterns = {"/rh/excluir-funcionario"})
public class ExcluirFuncionarioServlet extends HttpServlet {
    
    FuncionarioDAO fDao = new FuncionarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        boolean excluiu = fDao.excluir(id);
        
        if (excluiu) {
                request.setAttribute("excluidoAttr", true);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/rh.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pesquisar-funcionario.jsp");
                dispatcher.forward(request, response);
            }
    }

}
