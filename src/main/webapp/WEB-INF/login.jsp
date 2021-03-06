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
  </header>
  <body>
      <div class="page-title" style="background-color: green; padding: 9px; margin-top: 0px;">
          <h1><i class="fas fa-gamepad-alt"></i> Loja de Games</h1>
      </div>
      <br>
      <c:if test="${msgErro != null}">
          <div class="alert alert-danger">
              <c:out value="${msgErro}" />
          </div>
        </c:if>
      <c:if test="${sessionScope.msg != null}">
          <div class="alert alert-success">
              <c:out value="${sessionScope.msg}" />
          </div>
          <c:remove scope="session" var="msg" />
        </c:if>
      <form method="post" action="${pageContext.request.contextPath}/login" class="needs-validation" novalidate>
          <h2 class="page-title">Login</h2>
            <br>
            <div class="form-group row">
                <label for="inputEmail" class="col-md-1 offset-md-4">Email: </label>
                <div class="col-sm-3">
                    <input type="email" class="form-control" name="email" id="email" placeholder="fulano@email.com" required>
                    <div class="invalid-feedback">
                        Digite um endereço de email
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <label for="inputSenha" class="col-md-1 offset-md-4">Senha: </label>
                <div class="col-sm-3">
                    <input type="password" class="form-control" name="senha" id="senha" placeholder="*****" required>
                    <div class="invalid-feedback">
                        Digite uma data específica
                    </div>
                </div>
            </div>
            <br>
            <button class="btn btn-lg btn-primary col-md-2 offset-md-5" type="submit">Login</button>
            <br>
        </form>
  </body>
  <script src="https://kit.fontawesome.com/1803175e4f.js" crossorigin="anonymous"></script>
</html>
