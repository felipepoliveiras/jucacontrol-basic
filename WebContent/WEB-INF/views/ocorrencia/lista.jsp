<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/" var="raiz" />
<c:url value="/assets" var="assets" />
<c:url value="/app/ocorrencia" var="urlVisualizarOcorrencia" />
<c:url value="/app/ocorrencia/nova" var="urlNovaOcorrencia" />


<!DOCTYPE html>
<html>
<head>
	<c:import url="../templates/head.jsp"/>
</head>
<body>
	<c:import url="../templates/header.jsp"/>
	<main class="container">
		<a href="${urlNovaOcorrencia}" class="btn btn-red d-block ma-l-auto ma-t-s" style="max-width: 220px"> Abrir ocorrência</a>
		<h1 class="fx-slide-in">Ocorrências</h1>
		<section id="sectionOcorrencias">
			<h2>Classificar por: </h2>
			<%--Filtros de busca --%>
			<form>
				<select name="filtro">
					<option value="TODOS">Todos</option>
					<option value="EM_ATENDIMENTO">Em atendimento</option>
					<option value="AGUARDANDO">Aguardando resposta</option>
				</select>
			</form>
			
			<%-- Tabela de ocorrências --%>
			<table id="tabelaOcorrencias" class="table container read-container">
				<thead>
					<tr>
						<th></th>
						<th>Ocorrências</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ocorrencias}" var="ocorrencia">
						<tr>
							<%--- Sinalizador de status --%>
							<td>${ocorrencia.status}</td>
							<%-- Descrição da ocorrência --%>
							<td>
								<p class="ocorrencia-id">
									<a href="${urlVisualizarOcorrencia}?id=${ocorrencia.id}">
										${ocorrencia.id}
									</a>
								</p>
								<h4>${ocorrencia.titulo}</h4>
								<p class="ocorrencia-detalhe"><b class="color-pink">Data de abertura: </b>
									${ocorrencia.dataAbertura}
								</p>
								<p class="ocorrencia-detalhe"><b class="color-pink">Data de abertura: </b>
									${ocorrencia.dataEncerramento}
								</p>
							</td>
							<%--Quem atendeu ocorrencia/link de atendimento--%>
							<td>
								
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</section>
	</main>
	<c:import url="../templates/botoesFlutuantes.jsp"/>
</body>
</html>