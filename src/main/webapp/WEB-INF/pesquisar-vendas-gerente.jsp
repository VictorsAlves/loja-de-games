<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.scss" media="screen" />
     <title>Loja de Games</title>
  </head>
  <header>
      <nav class="navbar navbar-light nav-color">
      <a class="navbar-brand" href="${pageContext.request.contextPath}/gerente-vendas"><i class="fas fa-gamepad-alt"></i>Loja de Games</a>
      <div class="nav-item text-nowrap">
          <a class="nav-link" href="${pageContext.request.contextPath}/logout">Sair</a>
      </div>
    </nav>
  </header>
  <body>
      <div class="page-title">
          <h1>Pesquisar Vendas</h1>
      </div>
      
      <ul class="nav flex-column" style="float: left;">
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/gerente-vendas/pesquisar-vendas">Pesquisar Vendas</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/gerente-vendas/emitir-relatorio">Emitir Relatórios</a>
        </li>
        <li class="nav-item nav-item-last">
          <a class="nav-link" href="${pageContext.request.contextPath}/gerente-vendas/nivel-combustivel">Nível de Combustível</a>
        </li>
      </ul>
        
      <form method="get" action="${pageContext.request.contextPath}/gerente-vendas/pesquisa-de-vendas" class="needs-validation" novalidate>
            <br>
            <div class="form-group row">
                <label for="inputDataInicio" class="col-md-1 offset-md-1">Data Inicio: </label>
                <div class="col-sm-2">
                    <input type="text" class="form-control" name="dataInicio" id="dataInicio" placeholder="xx/xx/xxxx" onkeypress="$(this).mask('00/00/0000');" required>
                    <div class="invalid-feedback">
                        Digite uma data inicial
                    </div>
                </div>
         
                <label for="inputDataFinal" class="col-md-1 offset-md-1">Data Final:</label>
                <div class="col-sm-2">
                    <input type="text" class="form-control" name="dataFinal" id="dataFinal" placeholder="xx/xx/xxxx" onkeypress="$(this).mask('00/00/0000');" required>
                    <div class="invalid-feedback">
                        Digite uma data final
                    </div>
                </div>

                <div class="col-sm-4">
                    <button type="submit" class="btn btn-primary mb-2">Pesquisar</button>
                </div>
            </div>
        </form>
        <br>
        <c:if test="${naoEncontradoAttr}">
            <div class="alert alert-danger">
                Não há vendas para esta data
            </div>
        </c:if>
            
        <c:if test="${not empty vendasAttr}">
            <table class="table table-sm offset-md-2" style="width: 75%;">
                <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Cliente</th>
                        <th scope="col">Valor Total</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                     <c:forEach items="${vendasAttr}" var="venda">
                    <tr>
                        <th><c:out value="${venda.getId()}"/></th>
                        <c:choose>
                            <c:when test="${venda.getCliente() != null}">
                                <td><c:out value="${venda.getCliente().getNome()}"/></td>
                            </c:when>
                            <c:otherwise>
                                <td>Não informado</td>
                            </c:otherwise>
                        </c:choose>
                        <td>R$ <c:out value="${venda.getValorTotal()}"/></td>
                        <td><a data-method="get" href="${pageContext.request.contextPath}/gerente-vendas/detalhes-venda?id=${venda.getId()}" class="btn btn-primary mb-1" >Detalhes</a></td>
                        <td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#v${venda.getId()}">Cancelar</button></td> 
                     </tr>  
                </tbody>
                <div class="modal fade" id="v${venda.getId()}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                  <div class="modal-dialog" role="document">
                    <div class="modal-content">
                      <div class="modal-body">
                          Tem certeza que deseja cancelar a Venda <c:out value="${venda.getId()}"/>
                      </div>
                      <div class="modal-footer">
                        <form action="${pageContext.request.contextPath}/gerente-vendas/excluir-venda" method="post">
                            <button class="btn btn-success" type="submit" name="id" id="confirmDeleteButton" value="${venda.getId()}">Confirmar</button>
                        </form>
                        
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
                      </div>
                    </div>
                  </div>
                </div> 
               </c:forEach>
            </table>
        </c:if>
  </body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
<script src="https://kit.fontawesome.com/1803175e4f.js" crossorigin="anonymous"></script>
</html>
