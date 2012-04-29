

package arbol;

/**
 * Excepción es lanzanda cuando un elemento del árbol no es encontrado
 */
public class ElementoNoExisteException extends Exception
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
     * Constructor con mensaje
     * 
     * @param mensaje Mensaje de error
     */
    public ElementoNoExisteException( String mensaje )
    {
        super( mensaje );
    }
}
