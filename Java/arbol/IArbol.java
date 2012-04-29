
package arbol;

/**
 * Representa un arbol. 
 * @param <T> Tipo de información almacenada por el árbol.
 */
public interface IArbol<T>
{
    
    /**
     * Retorna la altura del árbol.
     * @return La altura del árbol.
     */
    public int darAltura();
    
    /**
     * Retorna el número de elementos del árbol.
     * @return El número de elementos del árbol.
     */
    public int darPeso();
    
    /**
     * Busca un elemento en el árbol dado su modelo.
     * @param modelo Descripción del elemento que se va a buscar en el árbol. Debe contener por lo menos la información mínima necesaria para que el método de comparación del
     *        nodo pueda establecer una relación de orden.
     * @return T elemento del árbol que corresponde al modelo o <code>null</code> si este no existe.
     */
    public T buscar(T modelo);
    
}
