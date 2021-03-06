package br.com.lojagames.controllers;

import br.com.lojagames.dao.FuncionarioDAO;
import br.com.lojagames.dao.ProdutoDAO;
import br.com.lojagames.models.Filial;
import br.com.lojagames.models.Produto;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pablo.osantana
 */
@WebServlet(name = "EditarProdutoServlet", urlPatterns = {"/backoffice/editar-produto"})
public class EditarProdutoServlet extends HttpServlet {
    
    FuncionarioDAO fDao = new FuncionarioDAO();
    ProdutoDAO pDao = new ProdutoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<String> filiais = fDao.getFiliais();

        request.setAttribute("filiaisAttr", filiais);

        int id = Integer.parseInt(request.getParameter("id"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/editar-produto.jsp");
        Produto produto = pDao.pesquisarPorId(id);

        try {
            if (produto != null) {
                request.setAttribute("idAttr", id);
                request.setAttribute("nomeAttr", produto.getNome());
                request.setAttribute("precoAttr", produto.getVlrUnitario());
                request.setAttribute("qntAttr", produto.getQtdProduto());
                request.setAttribute("tipoAttr", produto.getTipo());
                request.setAttribute("filialAttr", produto.getFilial().getNome());
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));

        String nome = request.getParameter("nome");
        String tipo = request.getParameter("tipo");
        double preco = Double.parseDouble(request.getParameter("preco"));
        double quantidade = Double.parseDouble(request.getParameter("quantidade"));
        String filialStr = request.getParameter("filial");

        Filial filial = fDao.getFilial(filialStr);

        Produto produto = new Produto(id, nome, tipo, quantidade, preco, filial);

        boolean editou = pDao.editar(produto);

        if (editou) {
            request.setAttribute("editadodoAttr", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/backoffice.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/editar-produto.jsp");
            dispatcher.forward(request, response);
        }

    }

}
