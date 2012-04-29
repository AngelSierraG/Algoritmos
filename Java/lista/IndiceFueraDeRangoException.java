

package lista;

/**
 * Excepción que indica que el índice pasado como parámetro está fuera de rango
 */
public class IndiceFueraDeRangoException extends RuntimeException
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
	 * Constante para serialización
	 */
	private static final long serialVersionUID = 1L;
	
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

	/**
     * Constructor de la excepción
     * @param valor Valor fuera de rango
     */
    public IndiceFueraDeRangoException( int valor )
    {
        super( "Índice: " + valor );
    }
}
