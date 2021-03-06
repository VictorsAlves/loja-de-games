package br.com.lojagames.controllers;

import br.com.lojagames.dao.VendaDAO;
import br.com.lojagames.models.Venda;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "DetalhesVendaServlet", urlPatterns = {"/gerente-vendas/detalhes-venda"})
public class DetalhesVendaServlet extends HttpServlet {
    
    VendaDAO vDao = new VendaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/detalhes-venda-gerente.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        Venda venda = vDao.getVendaPorId(id);
        request.setAttribute("vendaAttr", venda);
        
        dispatcher.forward(request, response); 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
