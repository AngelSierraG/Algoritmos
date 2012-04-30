

package colaEncadenada;

import java.io.Serializable; 

/**
 * Nodo de la cola encadenada
 * @param <T> Tipo de elemento que va a contener la cola
 */
public class NodoCola<T> implements Serializable
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
     * Elemento del nodo
     */
    private T elemento;

    /**
     * Siguiente elemento encadenado
     */
    private NodoCola<T> sigNodo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por par�metros del nodo. <br>
     * <b>post: </b> Se construy� el nodo con el elemento especificado, sigNodo=null y elemento = pElemento.<br>
     * @param pElemento Elemento que va a ser almacenado en el nodo. Diferente de null<br>
     */
    public NodoCola( T pElemento )
    {
        elemento = pElemento;
        sigNodo = null;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el elemento del nodo. <br>
     * <b>post: </b> Se retorn� el elemento contenido en el nodo.<br>
     * @return El elemento contenido en el nodo. Diferente de null<br>
     */
    public T darElemento( )
    {
        return elemento;
    }

    /**
     * Desconecta el nodo de la cola suponiendo que es el primero. <br>
     * <b>pre: </b> El nodo actual es el primer nodo de la cola. <br>
     * <b>post: </b> Se retorn� el nodo con el cual comienza la cola ahora, sigNodo=null.<br>
     * @return Nodo con el cual comienza la cola ahora.<br>
     */
    public NodoCola<T> desconectarPrimero( )
    {
        NodoCola<T> p = sigNodo;
        sigNodo = null;
        return p;
    }
    
    

    /**
     * Inserta el nodo especificado despu�s del nodo actual. <br>
     * <b>post: </b> Se insert� el nodo despu�s del nodo actual lo que implica que sigNodo=nodo.<br>
     * @param nodo El nodo a ser insertado<br>
     * @return Nodo que se insert� despu�s del nodo actual<br>
     */
    public NodoCola<T> insertarDespues( NodoCola<T> nodo )
    {
        sigNodo = nodo;
        return nodo;
    }

    /**
     * Retorna el siguiente nodo. <br>
     * <b>post: </b> Se retorn� el siguiente nodo.<br>
     * @return El nodo siguiente<br>
     */
    public NodoCola<T> darSiguiente( )
    {
        return sigNodo;
    }

    /**
     * Convierte el nodo a un String. <br>
     * <b>post: </b> Se retorn� la representaci�n en String del nodo. Dicha representaci�n corresponde a la representaci�n en String del elemento que contiene.
     * @return La representaci�n en String del nodo
     */
    @Override
    public String toString( )
    {
        return elemento.toString( );
    }
}
