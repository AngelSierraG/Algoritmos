
package arbol.arbolRojoNegro;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import arbol.ElementoExisteException;
import arbol.ElementoNoExisteException;
import arbol.IArbolOrdenado;

/**
 * Implementaci�n de un �rbol binario balanceado por el m�todo rojo-negro.
 * 
 * @author Juan Erasmo G�mez
 * 
 * @param <T> Tipo de informaci�n almacenada por el �rbol.
 */
public class ArbolRojoNegro<T extends Comparable<? super T>> implements Serializable, IArbolOrdenado<T>
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
     * Raiz del �rbol.
     */
    private NodoRojoNegro<T> raiz;
    
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------    

    /**
     * Constructor por defecto.
     * </p>
     * Construye un �rbol vacio.
     */
    public ArbolRojoNegro( )
    {
        raiz = null;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------      
    
    /*
     * (non-Javadoc)
     * @see uniandes.cupi2.collections.arbol.IArbolOrdenado#insertar(java.lang.Comparable)
     */
    public void insertar( T elem ) throws ElementoExisteException
    {
        // Crear el nuevo nodo y agregarlo como si el arbol fuera un arbol
        // binario normal
        NodoRojoNegro<T> nodo = new NodoRojoNegro<T>( elem );

        NodoRojoNegro<T> r2 = null;

        if( raiz == null )
        {
            raiz = nodo;
            raiz.cambiarColor( NodoRojoNegro.NEGRO );
        }
        else
        {
            r2 = raiz.insertar( nodo );
        }

        raiz = r2 != null && r2.darPadre( ) == null ? r2 : raiz;
    }

    /*
     * (non-Javadoc)
     * @see uniandes.cupi2.collections.arbol.IArbolOrdenado#eliminar(java.lang.Comparable)
     */
    public void eliminar( T elem ) throws ElementoNoExisteException
    {
        if( raiz == null )
            throw new ElementoNoExisteException( "El árbol se encuentra vacio" );
        if( raiz.darInfoNodo( ).compareTo( elem ) == 0 && raiz.hijoDerechoHoja( ) && raiz.hijoIzquierdoHoja( ) )
            raiz = null;
        else
        {
            NodoRojoNegro<T> r2 = raiz.darNodo( elem ).eliminar( );
            raiz = r2 != null && r2.darPadre( ) == null ? r2 : raiz;
        }
    }

    /**
     * Retorna una lista con los elementos del árbol en preorden.
     * 
     * @return Una lista con los elementos del árbol en preorden.
     */
    public List<T> darPreorden( )
    {
        List<T> preorden = new LinkedList<T>( );
        if( raiz != null )
            raiz.darPreorden( preorden );
        return preorden;
    }

    /**
     * Verifica si un elemento existe en el arbol.
     * 
     * @param elem Elemento a buscar en el arbol.
     * @return <code>true</code> si el elemento es encontrado en el arbol o <code>false</code> en caso contrario.
     */
    public boolean existe( T elem )
    {
        return raiz != null ? raiz.existe( elem ) : false;
    }

    /*
     * (non-Javadoc)
     * @see uniandes.cupi2.collections.arbol.IArbol#buscar(java.lang.Object)
     */
    public T buscar( T modelo )
    {
        try
        {
            return raiz != null ? raiz.darNodo( modelo ).darInfoNodo( ) : null;
        }
        catch( ElementoNoExisteException e )
        {
            return null;
        }
    }

    /**
     * Retorna la raiz del árbol.
     * 
     * @return La raiz del árbol.
     */
    public NodoRojoNegro<T> darRaiz( )
    {
        return raiz;
    }

    
    /* (non-Javadoc)
     * @see uniandes.cupi2.collections.arbol.IArbol#darPeso()
     */
    public int darPeso( )
    {
        return raiz == null ? 0 : raiz.darPeso( );
    }

    /* (non-Javadoc)
     * @see uniandes.cupi2.collections.arbol.IArbol#darAltura()
     */
    public int darAltura( )
    {
        return raiz == null ? 0 : raiz.darAltura( );
    }

    /**
     * Retorna el menor elemento del árbol.
     * 
     * @return El menor elemento del árbol o <code>null</code> si el árbol esta vacio.
     */
    public T darMinimo( )
    {
        return raiz == null ? null : raiz.darMenor( ).darInfoNodo( );
    }

    /**
     * Retorna el mayor elemento del árbol.
     * 
     * @return El mayor elemento del árbol o <code>null</code> si el árbol esta vacio.
     */
    public T darMayor( )
    {
        return raiz == null ? null : raiz.darMayor( ).darInfoNodo( );
    }

}