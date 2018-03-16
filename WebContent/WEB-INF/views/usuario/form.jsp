<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/" var="raiz" />
<c:url value="/assets" var="assets" />
<c:url value="/app/adm/usuario/salvar" var="urlSalvarUsuario" />

<!DOCTYPE html>
<html>
<head>
	<c:import url="../templates/head.jsp"/>
</head>
<body>
	<c:import url="../templates/header.jsp"/>
	<main class="container read-container">
		<h1>Cadastro de Usuário</h1>
		<form:form action="${urlSalvarUsuario}" method="post" modelAttribute="usuario" class="labels-d-block">
			<form:hidden path="id"/>
			<div class="flex-grid">
				<div class="row">
					<div class="col flex-1">
						<label for="inputNome">NOME</label>
						<form:input path="nome" type="text" id="inputNome"/>
						<s:hasBindErrors name="nome">
							<form:errors path="nome"/>
						</s:hasBindErrors>
					</div>
					<div class="col flex-2">
						<label for="inputSobrenome">SOBRENOME</label>
						<form:input path="sobrenome" type="text" id="inputSobrenome"/>
					</div>
				</div>
				<div class="row">
					<div class="col flex-1">
						<label for="inputEmail">EMAIL</label>
						<form:input path="email" type="email" id="inputEmail"/>
					</div>
				</div>
				<div class="row">
					<div class="col flex-1">
						<label for="inputSenha">SENHA</label>
						<form:input path="senha" type="password" id="inputSenha"/>
					</div>
					<div class="col flex-1">
						<label for="inputConfirmaSenha">CONFIRMAR SENHA</label>
						<input type="password" id="inputConfirmaSenha"/>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<label>
							Administrador
							<input type="checkbox" name="isAdministrador" id="inputAdministrador" class="d-inline">
						</label>
					</div>
				</div>
			</div>
			<button type="submit" class="btn btn-blue">SALVAR</button>
		</form:form>
	</main>
	<c:import url="../templates/botoesFlutuantes.jsp" />
</body>
</html>