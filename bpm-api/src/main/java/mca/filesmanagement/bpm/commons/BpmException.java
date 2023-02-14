package mca.filesmanagement.bpm.commons;

/**
 * Excepción padre del microservicio de BPM.
 *
 * @author agat
 */
public class BpmException extends Exception {

	private static final long serialVersionUID = -5637038326905709197L;

	/** Constructor por defecto. */
	public BpmException() {
		super();
	}

	/**
	 * Constructor con el mensaje de salida incluído.
	 * @param msg Mensaje.
	 */
	public BpmException(String msg) {
		super(msg);
	}
}
