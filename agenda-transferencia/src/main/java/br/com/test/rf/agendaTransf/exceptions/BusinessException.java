/**
 * 
 */
package br.com.test.rf.agendaTransf.exceptions;

/**
 * Exception lançada ao ocorrer algum erro de business.
 * 
 * @author "davidson.rodrigues"
 *
 * @created 23 de out de 2015
 */
public class BusinessException extends Exception {

	private String message;
	
	
	/**
	 * @param string
	 */
	public BusinessException(String message) {
		this.message = message;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
