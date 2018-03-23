<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/" var="raiz" />
<c:url value="/assets" var="assets" />
<c:url value="/app/ocorrencia/deletar" var="urlDeletarOcorrencia" />
<c:url value="/app/ocorrencia/encerrar" var="urlEncerrarOcorrencia" />
<c:url value="/app/ocorrencia/salvar" var="urlSalvarOcorrencia" />


<!DOCTYPE html>
<html>
<head>
	<c:import url="../templates/head.jsp"/>
</head>
<body>
	<c:import url="../templates/header.jsp"/>
	<main class="container read-container">
		<h1>Abrir Ocorrência</h1>
		<form:form action="${urlSalvarOcorrencia}" method="post" class="grid-flex" modelAttribute="ocorrencia">
			<form:hidden path="id"/>
			<div class="row">
				<div class="col flex-1">
					<form:input path="titulo" placeholder="Insira o título da ocorrência"/>	
					<form:errors path="titulo" cssClass="erro" element="div" />
				</div>
			</div>
			<div class="row">
				<div class="col flex-1">
					<form:textarea path="descricao"/>
					<form:errors path="descricao" cssClass="erro" element="div" />
				</div>
			</div>
			<div class="row">
				<div class="col flex-1">
					<form:select path="categoria.id">
						<form:options items="${categorias}" itemLabel="nome" itemValue="id"/>
						<form:errors path="categoria.id" cssClass="erro" element="div" />
					</form:select>
				</div>
			</div>
			<div class="row btn-group">
				<button type="submit" class="btn btn-blue col flex-1">SALVAR</button>
			</div>
		</form:form>
	</main>
	<c:import url="../templates/botoesFlutuantes.jsp" />
</body>
</html>