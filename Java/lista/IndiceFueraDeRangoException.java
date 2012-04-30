

package lista; 

/**
 * Excepci�n que indica que el �ndice pasado como par�metro est� fuera de rango
 */
public class IndiceFueraDeRangoException extends RuntimeException
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
	 * Constante para serializaci�n
	 */
	private static final long serialVersionUID = 1L;
	
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

	/**
     * Constructor de la excepci�n
     * @param valor Valor fuera de rango
     */
    public IndiceFueraDeRangoException( int valor )
    {
        super( "�ndice: " + valor );
    }
}
