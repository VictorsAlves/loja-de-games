/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lojagames.controllers;

import br.com.lojagames.dao.FilialDAO;
import br.com.lojagames.models.Filial;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Victor
 */
@WebServlet(name = "EditarFilial", urlPatterns = {"/diretor/editar-filial"})
public class EditarFilial extends HttpServlet {
    
    FilialDAO fiDao = new FilialDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/editar-filial.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        Filial filial = fiDao.pesquisarFilialPorID(id);

        request.setAttribute("idAttr", id);
        request.setAttribute("nomeAttr", filial.getNome());
        request.setAttribute("enderecoAttr", filial.getEndereco());
        request.setAttribute("cepAttr", filial.getCep());
        request.setAttribute("estadoAttr", filial.getEstado());

        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String cep = request.getParameter("cep");
        String estado = request.getParameter("estado");
        Filial filial = new Filial(id, nome, endereco, cep, estado);
        boolean editou = fiDao.editar(filial);
        if (editou) {
            request.setAttribute("editadoAttr", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/diretor.jsp");
            dispatcher.forward(request, response);
        } else {
 request.setAttribute("editadoAttr", false);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/diretor.jsp");
            dispatcher.forward(request, response);
        }

    }

}
