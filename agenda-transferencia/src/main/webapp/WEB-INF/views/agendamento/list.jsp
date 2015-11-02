<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class=content>
	<div id="main">
		<h1>Pesquisa de Agendamentos</h1>
		<form action="buscaAgendOuVaiParaForm" method="POST">
			<table class="consulta" style="border: 1px solid #8B8682;">
				<tr>
					<td width="10%" class="consulta" align="left"><b>Conta Origem</b></td>
					<td width="15%" class="consulta" align="left">
						<input type="text" id="contaOrig" name="contaOrig" maxlength="7" onkeyup="MascaraConta(this,event)" size="15" />
					</td>
					<td width="10%" class="consulta" align="left"><b>Conta Destino</b></td>
					<td width="15%" class="consulta" align="left">
						<input type="text" id="contaDest" name="contaDest" maxlength="7" onkeyup="MascaraConta(this,event)" size="15" />
					</td>
					<td width="10%" class="consulta" align="left"><b>Tipo Agendamento</b></td>
					
					
					<td width="15%" class="consulta" align="left">
						<select name="tipoAgend" id="tipoAgend">
        					<c:forEach items="${tipos}" var="option">
                				<option value="${option}">
                					<c:out value="${option}"></c:out>
                				</option>
       						 </c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="consulta" align="left"><b>Data Operação</b></td>
					<td class="consulta" align="left">
						<input type="text" id="dtOperacao" name="dtOperacao" maxlength="10" onkeyup="MascaraData(this,event)" size="15" />
					</td>
					<td class="consulta" align="left"><b>Data Transferência</b></td>
					<td class="consulta" align="left">
						<input type="text" id="dtTransf" name="dtTransf" maxlength="10" onkeyup="MascaraData(this,event)" size="15" />
					</td>
				<tr>
			</table>
			<div style="text-align: right; max-height: 30px;">
				<button type="submit" name="submit" value="pesquisa"
					style="width: 100px; height: 30px; margin-top: 5px;">Pesquisar</button>
				<button type="submit" name="submit" value="novo"
					style="width: 100px; height: 30px; margin-top: 5px;">Novo</button>
			</div>
		</form>
		<table class="resultado">
			<tr>
				<td width="15%" align="center"><b>Cliente Origem</b></td>
				<td width="10%" align="center"><b>Conta Origem</b></td>
				<td width="15%" align="center"><b>Cliente Destino</b></td>
				<td width="10%" align="center"><b>Conta Destino</b></td>
				<td width="10%" align="center"><b>Tipo Agendamento</b></td>
				<td width="7%" align="center"><b>Valor</b></td>
				<td width="10%" align="center"><b>Taxa</b></td>
				<td width="10%" align="center"><b>Data Operação</b></td>
				<td width="10%" align="center"><b>Data Transferência</b></td>
				<td width="3%" align="center"><b>Ação</b></td>
			</tr>
			<c:forEach items="${agendamentos}" var="ag">
				<tr id="row_${ag.id}" align="center">
					<td>${ag.contaOrigem.agente.nome}</td>
					<td>${ag.contaOrigem.numeroConta}</td>
					<td>${ag.contaDestino.agente.nome}</td>
					<td>${ag.contaDestino.numeroConta}</td>
					<td>${ag.tipoAgendamento}</td>
					<td>${ag.valor}</td>
					<td>${ag.taxaTransferencia}</td>
					<td>${ag.dtOpFormatada}</td>
					<td>${ag.dtTransfFormatada}</td>
					<td>
						<a href="removeAgend?id=${ag.id}">
							<img src="<c:url value="/resources/imagens/delete.png"/>" style="height: 21px; width: 24px;" />
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>