
package arbol.arbol2_3;

import java.io.Serializable;

import iterador.Iterador;
import iterador.IteradorException;
import iterador.IteradorSimple;
import arbol.ElementoExisteException;
import arbol.ElementoNoExisteException;
import arbol.IArbolOrdenado;

/**
 * Implementaci�n de un �rbol 2-3
 * 
 * @param <T> Tipo de datos que contiene cada nodo del �rbol. Los nodos deben implementar la interface comparable
 */
public class Arbol2_3<T extends Comparable<? super T>> implements Serializable, IArbolOrdenado<T>
{
	// -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

	/**
	 * Constante para la serializaci�n
	 */
	private static final long serialVersionUID = 1L;

	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Ra�z del �rbol 2-3
     */
    private Nodo2_3<T> raiz;

    /**
     * Peso del �rbol 2-3
     */
    private int peso;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo �rbol vac�o. <br>
     * <b>post: </b> Se construy� un �rbol vac�o, con ra�z null.
     */
    public Arbol2_3( )
    {
        raiz = null;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Devuelve la ra�z del �rbol para ser navegada. <br>
     * <b>post: </b> Se retorn� la ra�z del �rbol.
     * @return Ra�z del �rbol
     */
    public Nodo2_3<T> darRaiz( )
    {
        return raiz;
    }

    
    /* (non-Javadoc)
     * @see uniandes.cupi2.collections.arbol.IArbol#buscar(java.lang.Object)
     */
    public T buscar( T modelo )
    {
        return ( raiz != null ) ? raiz.buscar( modelo ) : null;
    }

    /* (non-Javadoc)
     * @see uniandes.cupi2.collections.arbol.IArbol#darAltura()
     */
    public int darAltura( )
    {
        return raiz == null ? 0 : raiz.darAltura( );
    }

    /* (non-Javadoc)
     * @see uniandes.cupi2.collections.arbol.IArbol#darPeso()
     */
    public int darPeso( )
    {
        return peso;
    }

    
    /* (non-Javadoc)
     * @see uniandes.cupi2.collections.arbol.IArbolOrdenado#insertar(java.lang.Comparable)
     */
    public void insertar( T elemento ) throws ElementoExisteException
    {
        if( raiz == null )
        {
            // Caso 1: el �rbol es vac�o
            raiz = new Nodo2_3<T>( elemento );
        }
        else
        {
            // Caso 2: el �rbol no es vac�o
            raiz = raiz.insertar( elemento );
        }
        peso++;
    }

    
    /* (non-Javadoc)
     * @see uniandes.cupi2.collections.arbol.IArbolOrdenado#eliminar(java.lang.Comparable)
     */
    public void eliminar( T elemento ) throws ElementoNoExisteException
    {
        if( raiz != null )
        {
            // Caso 1: el �rbol no es vac�o
            raiz = raiz.eliminar( elemento );
            peso--;
        }
        else
        {
            // Caso 2: el �rbol es vac�o
            throw new ElementoNoExisteException( "El elemento especificado no existe en el �rbol" );
        }
    }

    /**
     * Devuelve los elementos del �rbol en inorden. <br>
     * <b>post: </b> Se retorno el iterador para recorrer los elementos del �rbol en inorden.
     * @return Iterador para recorrer los elementos del �rbol en inorden. Diferente de null.
     */
    public Iterador<T> inorden( )
    {
        IteradorSimple<T> resultado = new IteradorSimple<T>( peso );
        if( raiz != null )
        {
            try
            {
                raiz.inorden( resultado );
            }
            catch( IteradorException e )
            {
                // Nunca deber�a lanzar esta excepci�n porque el tama�o del
                // iterador es el peso del �rbol
            }
        }
        return resultado;
    }
}
