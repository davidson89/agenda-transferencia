<div class=content>
	<div id="main">
		<h1>Cadastro de Conta</h1>
		<form action="inserirConta" method="POST">
			<table class="cadastro">
				<tr>
					<td width="5%" align="left"><b>Cliente:</b></td>
					<td width="20%" align="left"><input type="text" id="nomeCliente" name="nomeCliente" size="20"/></td>
					<td width="5%" align="left"><b>CPF/CNPJ:</b></td>
					<td width="20%" align="left"><input type="text" id="cpfCnpj" name="cpfCnpj" maxlength="14" onkeyup="MascaraCPF(this,event)" size="15"/></td>
					<td width="5%" align="left"><b>E-mail:</b></td>
					<td width="20%" align="left"><input type="text" id="email" name="email" size="20"/></td>
				</tr>
				<tr>
					<td align="left"><b>Número Conta:</b></td>
					<td align="left"><input type="text" id="numeroConta" name="numeroConta" size="20"/></td>
					<td align="left"><b>Saldo Inicial:</b></td>
					<td align="left"><input type="text" id="saldoInicial" name="saldoInicial" size="15"/></td>
					<td align="left"><b>Limíte:</b></td>
					<td align="left"><input type="text" id="limite" name="limite" size="20"/></td>
				</tr>
			</table>
			<div style="text-align: right;" >
				<button name="Salvar" style="width: 100px;height: 30px; margin-top: 5px;">Salvar</button>
			</div>
		</form>
	</div>
</div>