<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/" var="raiz" />
<c:url value="/usuario/autenticar" var="urlAutenticarUsuario" />

<!DOCTYPE html>
<html>
<head>
	<c:import url="../templates/head.jsp"/>
</head>
<body class="d-flex aln-items-center">
	<div class="aln-items-center container d-flex" style="height: 500px;">
		<div>
			<img alt="juca control logo" src="${raiz}assets/images/jucacontrol_logo.png">
		</div>
		<div>
			<form:form modelAttribute="usuario" action="${urlAutenticarUsuario}" method="post" style="color: white">
				<label>
					E-mail
					<form:input path="email" type="email" required="required" maxlength="120" id="inputEmail" />
					<form:errors path="email" />
				</label>
				<label>
					Senha
					<input name="senha" type="password" required="required" maxlength="20" />
				</label>
				<button class="btn" type="submit">ENTRAR</button>
			</form:form>
		</div>	
	</div>
</body>
</html>