

package iterador;

/**
 * Excepción que indica un error sobre las operaciones del iterador simple
 */
public class IteradorException extends Exception
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
    /**
	 * Constante para la serialización
	 */
	private static final long serialVersionUID = 1L;	
	
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
	
    /**
     * Constructor de la excepción
     * @param mensaje Mensaje de excepción
     */
    public IteradorException( String mensaje )
    {
        super( mensaje );
    }
}
