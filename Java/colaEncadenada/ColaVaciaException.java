

package colaEncadenada;

/**
 * Excepci�n generada cuando se trata de tomar un elemento de una cola vac�a
 */
public class ColaVaciaException extends Exception
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
     * Constructor con mensaje
     * @param mensaje Mensaje de error
     */
    public ColaVaciaException( String mensaje )
    {
        super( mensaje );
    }
}
