
package interadorSinMemoria;

/**
 * M�todos b�sicos que debe ofrecer todo nodo de cupi2 Collections, para una estructura<br>
 * que use un iterador sin memoria
 * @param <T> Tipo de datos que maneja el nodo
 */
public interface INodo<T> 
{
    /**
     * Retorna el nodo anterior
     * @return El nodo anterior
     */
    public INodo<T> darAnterior( );

    /**
     * Retorna el nodo siguiente
     * @return El nodo siguiente
     */
    public INodo<T> darSiguiente( );

    /**
     * Retorna el elemento del nodo
     * @return El elemento del nodo
     */
    public T darElemento( );
}
