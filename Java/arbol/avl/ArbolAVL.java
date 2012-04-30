

package arbol.avl;

import java.io.Serializable;

import iterador.Iterador;
import iterador.IteradorSimple;
import arbol.ElementoExisteException;
import arbol.ElementoNoExisteException;
import arbol.IArbolOrdenado;

/**
 * Implementaci�n de un �rbol binario ordenado balanceado por altura: �rbol AVL
 * @param <T> Tipo de datos que contiene cada nodo del �rbol
 */
public class ArbolAVL<T extends Comparable<? super T>> implements Serializable, IArbolOrdenado<T>
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
	 * Constantes para la serializaci�n
	 */
	public static final long serialVersionUID = 1L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ra�z del �rbol AVL
     */
    public NodoAVL<T> raiz;

    /**
     * Peso del �rbol AVL
     */
    public int peso;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del �rbol AVL vac�o. <br>
     * <b>post: </b> Se construy� un �rbol AVL vac�o.
     */
    public ArbolAVL( )
    {
        raiz = null;
        peso = 0;
    }

    /**
     * Constructor del �rbol AVL con ra�z. <br>
     * <b>pre: </b> r!=null y p>=0. <b>post: </b> Se construy� un �rbol AVL con la raiz r.
     * @param r Raiz del �rbol
     * @param p Peso del �rbol
     */
    public ArbolAVL( NodoAVL<T> r, int p )
    {
        raiz = r;
        peso = p;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Devuelve la ra�z del �rbol para navegarlo. <br>
     * <b>post: </b> Se retorn� la ra�z del �rbol para navegarlo.
     * @return Ra�z del �rbol para navegarlo
     */
    public NodoAVL<T> darRaiz( )
    {
        return raiz;
    }


    /* (non-Javadoc)
     * @see uniandes.cupi2.collections.arbol.IArbolOrdenado#insertar(java.lang.Comparable)
     */
    public void insertar( T elemento ) throws ElementoExisteException
    {
        if( raiz == null )
        {
            // Caso 1: el �rbol es vac�o
            raiz = new NodoAVL<T>( elemento );
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
        //else
        //{
            // Caso 2: el �rbol es vac�o
            //throw new ElementoNoExisteException( "El elemento especificado no existe en el �rbol" );
        //}
    }

    /* (non-Javadoc)
     * @see uniandes.cupi2.collections.arbol.IArbol#buscar(java.lang.Object)
     */
    public T buscar( T modelo )
    {
        return ( raiz != null ) ? raiz.buscar( modelo ) : null;
    }

    /**
     * Devuelve los elementos del �rbol en inorden. <br>
     * <b>post: </b> Se retorn� el iterador con los elementos del �rbol en inorden.
     * @return Iterador con los elementos del �rbol en inorden
     */
    public Iterador<T> inorden( )
    {
        IteradorSimple<T> resultado = new IteradorSimple<T>( peso );
        if( raiz != null )
        {
            raiz.inorden( resultado );
        }
        return resultado;
    }

    /* (non-Javadoc)
     * @see uniandes.cupi2.collections.arbol.IArbol#darAltura()
     */
    public int darAltura( )
    {
        return ( raiz != null ) ? raiz.darAltura( ) : 0;
    }

    /* (non-Javadoc)
     * @see uniandes.cupi2.collections.arbol.IArbol#darPeso()
     */
    public int darPeso( )
    {
        return peso;
    }

    /**
     * Devuelve el elemento mayor del �rbol AVL. <br>
     * <b>post: </b> Se retorn� el elemento mayor del �rbol AVL o null si el �rbol est� vacio.
     * @return Elemento mayor del �rbol AVL o null si el �rbol est� vacio
     */
    public T darMayor( )
    {
        return ( raiz != null ) ? raiz.darMayor( ) : null;
    }

    /**
     * Devuelve el elemento menor del �rbol AVL. <br>
     * <b>post: </b> Se retorn� el elemento menor del �rbol AVL o null si el �rbol est� vacio.
     * @return Elemento menor del �rbol AVL o null si el �rbol est� vacio
     */
    public T darMenor( )
    {
        return ( raiz != null ) ? raiz.darMenor( ) : null;
    }

    /**
     * Devuelve los elementos del �rbol utilizando un recorrido por niveles. <br>
     * <b>post: </b> Se retorn� el iterador para recorrer los elementos del �rbol por niveles.
     * @return Iterador con los elementos del �rbol en un recorrido por niveles
     */
    public Iterador<T> darRecorridoNiveles( )
    {
        IteradorSimple<T> resultado = new IteradorSimple<T>( darPeso( ) );
        if( raiz != null )
        {
            raiz.darRecorridoNiveles( resultado );
        }
        return resultado;
    }
}
