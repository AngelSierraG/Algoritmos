
package arbol.arbol2_3;

import java.io.Serializable;

import iterador.Iterador;
import iterador.IteradorException;
import iterador.IteradorSimple;
import arbol.ElementoExisteException;
import arbol.ElementoNoExisteException;
import arbol.IArbolOrdenado;

/**
 * Implementación de un árbol 2-3
 * 
 * @param <T> Tipo de datos que contiene cada nodo del árbol. Los nodos deben implementar la interface comparable
 */
public class Arbol2_3<T extends Comparable<? super T>> implements Serializable, IArbolOrdenado<T>
{
	// -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

	/**
	 * Constante para la serialización
	 */
	private static final long serialVersionUID = 1L;

	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Raíz del árbol 2-3
     */
    private Nodo2_3<T> raiz;

    /**
     * Peso del árbol 2-3
     */
    private int peso;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo árbol vacío. <br>
     * <b>post: </b> Se construyó un árbol vacío, con raíz null.
     */
    public Arbol2_3( )
    {
        raiz = null;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve la raíz del árbol para ser navegada. <br>
     * <b>post: </b> Se retornó la raíz del árbol.
     * @return Raíz del árbol
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
            // Caso 1: el árbol es vacío
            raiz = new Nodo2_3<T>( elemento );
        }
        else
        {
            // Caso 2: el árbol no es vacío
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
            // Caso 1: el árbol no es vacío
            raiz = raiz.eliminar( elemento );
            peso--;
        }
        else
        {
            // Caso 2: el árbol es vacío
            throw new ElementoNoExisteException( "El elemento especificado no existe en el árbol" );
        }
    }

    /**
     * Devuelve los elementos del árbol en inorden. <br>
     * <b>post: </b> Se retorno el iterador para recorrer los elementos del árbol en inorden.
     * @return Iterador para recorrer los elementos del árbol en inorden. Diferente de null.
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
                // Nunca debería lanzar esta excepción porque el tamaño del
                // iterador es el peso del árbol
            }
        }
        return resultado;
    }
}
