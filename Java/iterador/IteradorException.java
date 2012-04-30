

package iterador;

/**
 * Excepci�n que indica un error sobre las operaciones del iterador simple
 */
public class IteradorException extends Exception 
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
    /**
	 * Constante para la serializaci�n
	 */
	private static final long serialVersionUID = 1L;	
	
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
	
    /**
     * Constructor de la excepci�n
     * @param mensaje Mensaje de excepci�n
     */
    public IteradorException( String mensaje )
    {
        super( mensaje );
    }
}
