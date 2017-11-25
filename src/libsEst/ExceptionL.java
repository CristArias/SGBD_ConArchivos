
package libsEst;

/**
 *
 * @author CristianAG
 */
public class ExceptionL extends Exception {

    /**
     * Constructor con mensaje
     *
     * @param mensaje Mensaje de error
     */
    public ExceptionL(String mensaje) {
        super(mensaje);
    }

    /**
     * Metodo que retorna el mensaje de error para la excepcion generada. <br>
     *
     * @return Mensaje de error representativo de la Excepcion generada.
     */
    public String getMensaje() {
        return (super.getMessage());

    }

}