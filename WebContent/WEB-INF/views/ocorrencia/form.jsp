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
			<div class="row">
				<div class="col flex-1">
					<form:input path="titulo" placeholder="Insira o título da ocorrência"/>	
				</div>
				<div class="col flex-1">
					<form:textarea path="descricao"/>
				</div>
				<div class="col flex-1">
					<form:select path="categoria">
						<form:options items="${categorias}" itemLabel="nome" itemValue="id"/>
					</form:select>
				</div>
			</div>
		</form:form>
	</main>
	<c:import url="../templates/botoesFlutuantes.jsp" />
</body>
</html>