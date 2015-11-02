<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class=content>
	<div id="main">
		<h1>Cadastro de Agendamento</h1>
		<form action="inserirAgend" method="POST">
			<table class="cadastro">
				<tr>
					<td width="10%"><b>Conta Origem</b></td>
					<td width="20%"><input type="text" id="contaOrig" name="contaOrig" maxlength="7" onkeyup="MascaraConta(this,event)" size="15"/></td>
					<td width="10%"><b>Conta Destino</b></td>
					<td width="20%"><input type="text" id="contaDest" name="contaDest" maxlength="7" onkeyup="MascaraConta(this,event)" size="15"/></td>
					<td width="10%"><b>Tipo Agendamento</b></td>
					<td width="20%">						
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
					<td width="5%"><b>Valor</b></td>
					<td width="20%"><input type="text" id="valor" name="valor" size="15"/>
					<td width="5%"><b>Data Transferência</b></td>
					<td width="20%"><input type="text" id="dtTransf" name="dtTransf" maxlength="10" onkeyup="MascaraData(this,event)" size="15"/>
				</tr>
			</table>
			<div style="text-align: right;" >
				<button name="Salvar" style="width: 100px;height: 30px; margin-top: 5px;">Salvar</button>
			</div>
		</form>
	</div>
</div>