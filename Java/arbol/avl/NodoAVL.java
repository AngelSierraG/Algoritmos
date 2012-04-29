

package arbol.avl;

import java.io.Serializable;

import colaEncadenada.ColaEncadenada;
import colaEncadenada.ColaVaciaException;
import iterador.IteradorException;
import iterador.IteradorSimple;
import arbol.ElementoExisteException;
import arbol.ElementoNoExisteException;

/**
 * Nodo de un �rbol AVL
 * @param <T> Tipo de elemento que va a contener cada nodo del �rbol
 */
public class NodoAVL<T extends Comparable<? super T>> implements Serializable
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
	 * Constantes para la serializaci�n
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constante que representa el hecho de que el nodo est� balanceado hacia la izquierda.
     */
    private static final int BIZQ = 1;

    /**
     * Constante que representa el hecho de que el nodo est� balanceado.
     */
    private static final int BAL = 0;

    /**
     * Constante que representa el hecho de que el nodo est� balanceado hacia la derecha.
     */
    private static final int BDER = -1;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Elemento almacenado en el nodo
     */
    private T elem;

    /**
     * Nodo a la derecha
     */
    private NodoAVL<T> derNodo;

    /**
     * Nodo a la izquierda
     */
    private NodoAVL<T> izqNodo;

    /**
     * Indica el estado de balanceo del nodo
     */
    private int balance;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del nodo. <br>
     * <b>post: </b> Se construy� el nodo con el elemento especificado, derNodo= null y izqNodo= null.
     * @param pElemento Elemento que va a ser almacenado en el nodo
     */
    public NodoAVL( T pElemento )
    {
        elem = pElemento;
        derNodo = null;
        izqNodo = null;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Devuelve la ra�z del �rbol (elemento del nodo). <br>
     * <b>post: </b> Se retorn� la ra�z del �rbol (elemento del nodo).
     * @return Ra�z del �rbol (elemento del nodo)
     */
    public T darRaiz( )
    {
        return elem;
    }

    /**
     * Devuelve el hijo derecho del nodo. <br>
     * <b>post: </b> Se retorn� el hijo derecho del nodo.
     * @return Hijo derecho del nodo
     */
    public NodoAVL<T> darHijoDerecho( )
    {
        return derNodo;
    }

    /**
     * Devuelve el hijo izquierdo del nodo. <br>
     * <b>post: </b> Se retorn� el hijo izquierdo del nodo.
     * @return Hijo izquierdo del nodo
     */
    public NodoAVL<T> darHijoIzquierdo( )
    {
        return izqNodo;
    }

    /**
     * Agrega un nuevo elemento en el �rbol cuya ra�z es el nodo actual. <br>
     * <b>pre: </b> pElemento!=null. <br>
     * <b>post: </b> Se insert� el elemento especificado en el �rbol.
     * @param pElemento elemento que se va a agregar
     * @return Ra�z del �rbol producto de insertar en el �rbol que comienza en el nodo actual el elemento que llega como par�metro
     * @throws ElementoExisteException El elemento ya existe en el �rbol
     */
    public NodoAVL<T> insertar( T pElemento ) throws ElementoExisteException
    {
        // Agrega el elemento al �rbol utilizando una operaci�n auxiliar
        Retorno retorno = new Retorno( null, false );
        auxInsertar( pElemento, retorno );
        return retorno.respuesta;
    }

    /**
     * Elimina el elemento dado como par�metro, del �rbol cuya ra�z es el nodo actual. <br>
     * <b>pre: </b> pElemento!=null. <br>
     * <b>post: </b> Se elimin� el elemento especificado si exist�a en el �rbol.
     * @param pElemento Elemento que se va a eliminar
     * @return Ra�z del �rbol producto de eliminar del �rbol que comienza en el nodo actual el elemento que llega como par�metro
     * @throws ElementoNoExisteException El elemento no se encontr� en el �rbol
     */
    public NodoAVL<T> eliminar( T pElemento ) throws ElementoNoExisteException
    {
        // Elimina el elemento utilizando una operaci�n auxiliar
        Retorno retorno = new Retorno( null, false );
        auxEliminar( pElemento, retorno );
        return retorno.respuesta;
    }

    /**
     * Busca el elemento cuyo modelo viene dado como par�metro, en el �rbol cuya ra�z es el nodo actual. <br>
     * <b>pre: </b> modelo!=null. <br>
     * <b>post: </b> Se retorn� el elemento que cumple con el modelo o null si no encuentra ninguno.
     * @param modelo Modelo del elemento que se va a buscar
     * @return Elemento que cumple con el modelo o null si no encuentra ninguno
     */
    public T buscar( T modelo )
    {
        // Compara el valor con el valor del nodo
        int resultado = elem.compareTo( modelo );
        if( resultado == 0 )
        {
            // Caso 1: El elemento est� en el nodo ra�z
            return elem;
        }
        else if( resultado > 0 )
        {
            // Caso 2: El elemento puede estar en el sub�rbol izquierdo
            return ( izqNodo != null ) ? izqNodo.buscar( modelo ) : null;
        }
        else
        {
            // Caso 3: El elemento puede estar en el sub�rbol derecho
            return ( derNodo != null ) ? derNodo.buscar( modelo ) : null;
        }
    }

    /**
     * Agrega los elementos al iterador que llega como par�metro, utilizando para esto un recorrido en inorden. <br>
     * <b>pre: </b> resultado!=null. <br>
     * <b>post: </b> Se retorn� el resultado del recorrido.
     * @param resultado Resultado del recorrido
     */
    public void inorden( IteradorSimple<T> resultado )
    {
        // Agrega los elementos del sub�rbol izquierdo
        if( izqNodo != null )
        {
            izqNodo.inorden( resultado );
        }

        try
        {
            // Agrega el elemento que se encuentra en el nodo
            resultado.agregar( elem );
        }
        catch( IteradorException e )
        {
            // Nunca deber�a aparecer esta excepci�n
        }

        // Agrega los elementos del sub�rbol derecho
        if( derNodo != null )
        {
            derNodo.inorden( resultado );
        }
    }

    /**
     * Devuelve la altura del �rbol cuya ra�z es el nodo actual. <br>
     * <b>post: </b> Se retorn� la altura del �rbol cuya ra�z es el nodo actual.
     * @return Altura del �rbol cuya ra�z es el nodo actual
     */
    public int darAltura( )
    {
        int a1 = ( izqNodo == null ) ? 0 : izqNodo.darAltura( );
        int a2 = ( derNodo == null ) ? 0 : derNodo.darAltura( );
        return ( a1 >= a2 ) ? a1 + 1 : a2 + 1;
    }

    /**
     * Devuelve el elemento mayor del �rbol cuya ra�z es el nodo actual. <br>
     * <b>post: </b> Se retorn� el elemento mayor del �rbol cuya ra�z es el nodo actual.
     * @return Elemento mayor del �rbol cuya ra�z es el nodo actual
     */
    public T darMayor( )
    {
        NodoAVL<T> nodo = mayorElemento( );
        return ( nodo == null ) ? null : nodo.darRaiz( );
    }

    /**
     * Devuelve el elemento menor del �rbol cuya ra�z es el nodo actual. <br>
     * <b>post: </b> Se retorn� el elemento menor del �rbol cuya ra�z es el nodo actual.
     * @return Elemento menor del �rbol cuya ra�z es el nodo actual
     */
    public T darMenor( )
    {
        NodoAVL<T> nodo = menorElemento( );
        return ( nodo == null ) ? null : nodo.darRaiz( );
    }

    // -----------------------------------------------------------------
    // Operaciones Auxiliares
    // -----------------------------------------------------------------

    /**
     * Agrega el elemento al �rbol cuya ra�z es el nodo actual. <br>
     * <b>pre: </b> pElemento!=null, retorno!=null. <br>
     * <b>post: </b> Se insert� el elemento en el �rbol.
     * @param pElemento que se va a insertar
     * @param retorno Objeto con la informaci�n de la nueva ra�z del �rbol y un indicador de cambio de altura
     * @throws ElementoExisteException El elemento ya existe en el �rbol
     */
    private void auxInsertar( T pElemento, Retorno retorno ) 
    {
        // Compara el elemento con el valor almacenado en el nodo
        int resultado = elem.compareTo( pElemento );
        if( resultado == 0 )
        {
            // Caso 1: El elemento est� en el nodo actual
        	return;
            //throw new ElementoExisteException( "El elemento ya existe en el �rbol" );
        }
        else if( resultado > 0 )
        {
            // Caso 2: Debe agregar por la izquierda
            if( izqNodo == null )
            {
                // Se debe crear el nodo nuevo
                izqNodo = new NodoAVL<T>( pElemento );
                retorno.respuesta = this;
                if( derNodo == null )
                {
                    balance = BIZQ;
                    retorno.diferenciaAltura = true;
                }
                else
                {
                    balance = BAL;
                    retorno.diferenciaAltura = false;
                }
            }
            else
            {
                // Caso general de inserci�n por la izquierda
                izqNodo.auxInsertar( pElemento, retorno );
                izqNodo = retorno.respuesta;

                // Balancea si es necesario
                if( retorno.diferenciaAltura )
                {
                    // El sub�rbol izquierdo aument� de altura, debe corregirse
                    // el balance
                    switch( balance )
                    {
                        case BIZQ:
                            retorno.diferenciaAltura = false;
                            retorno.respuesta = balanceaIzq( );
                            break;
                        case BAL:
                            balance = BIZQ;
                            retorno.respuesta = this;
                            break;
                        case BDER:
                            balance = BAL;
                            retorno.diferenciaAltura = false;
                            retorno.respuesta = this;
                            break;
                    }
                }
                else
                {
                    retorno.respuesta = this;
                }
            }
        }
        else
        {
            // Caso 3: Debe agregar por la derecha
            if( derNodo == null )
            {
                // Se debe crear el nodo nuevo
                derNodo = new NodoAVL<T>( pElemento );
                retorno.respuesta = this;
                if( izqNodo == null )
                {
                    balance = BDER;
                    retorno.diferenciaAltura = true;
                }
                else
                {
                    balance = BAL;
                    retorno.diferenciaAltura = false;
                }
            }
            else
            {
                // Caso general de inserci�n por la derecha
                derNodo.auxInsertar( pElemento, retorno );
                derNodo = retorno.respuesta;

                // Balancea si es necesario
                if( retorno.diferenciaAltura )
                {
                    // El sub�rbol derecho aument� de altura, debe corregirse el
                    // balance
                    switch( balance )
                    {
                        case BIZQ:
                            balance = BAL;
                            retorno.diferenciaAltura = false;
                            retorno.respuesta = this;
                            break;
                        case BAL:
                            balance = BDER;
                            retorno.respuesta = this;
                            break;
                        case BDER:
                            retorno.diferenciaAltura = false;
                            retorno.respuesta = balanceaDer( );
                            break;
                    }
                }
                else
                {
                    retorno.respuesta = this;
                }
            }
        }
    }

    /**
     * Elimina el elemento del �rbol cuya ra�z es el nodo actual. <br>
     * <b>pre: </b> pElemento!=null, retorno!=null. <br>
     * <b>post: </b> Se elimin� el elemento del �rbol si exist�a.
     * @param pElemento Elemento que se va a eliminar
     * @param retorno Objeto con la informaci�n de la nueva ra�z del �rbol y un indicador de cambio de altura
     * @throws ElementoNoExisteException Elemento no encontrado en el �rbol
     */
    private void auxEliminar( T pElemento, Retorno retorno ) throws ElementoNoExisteException
    {
        // Compara el elemento con el valor almacenado en el nodo
        int resultado = elem.compareTo( pElemento );
        if( resultado == 0 )
        {
            // Caso 1: El elemento est� en el nodo actual
            if( izqNodo == null & derNodo == null )
            {
                // No tiene hijos, simplemente debe eliminarlo
                retorno.diferenciaAltura = true;
                retorno.respuesta = null;
            }
            else if( izqNodo == null )
            {
                retorno.respuesta = derNodo;
                retorno.diferenciaAltura = true;
            }
            else
            {
                // Reemplaza el nodo con la mayor elemento del nodo izquierdo
                NodoAVL<T> reemplazo = izqNodo.mayorElemento( );
                elem = reemplazo.elem;
                izqNodo.auxEliminar( reemplazo.elem, retorno );
                izqNodo = retorno.respuesta;

                // Balancea si es necesario
                if( retorno.diferenciaAltura )
                {
                    balanElimDer( retorno );
                }
                else
                {
                    retorno.respuesta = this;
                }
            }
        }
        else if( resultado > 0 )
        {
            // Caso 2: El elemento debe estar por la izquierda
            if( izqNodo == null )
            {
                throw new ElementoNoExisteException( "El elemento no se encuentra en el �rbol" );
            }
            izqNodo.auxEliminar( pElemento, retorno );
            izqNodo = retorno.respuesta;

            // Balancea si es necesario
            if( retorno.diferenciaAltura )
            {
                balanElimDer( retorno );
            }
            else
            {
                retorno.respuesta = this;
            }
        }
        else
        {
            // Caso 3: El elemento debe estar por la derecha
            if( derNodo == null )
            {
                throw new ElementoNoExisteException( "El elemento no se encuentra en el �rbol" );
            }
            derNodo.auxEliminar( pElemento, retorno );
            derNodo = retorno.respuesta;

            // Balancea si es necesario
            if( retorno.diferenciaAltura )
            {
                balanElimIzq( retorno );
            }
            else
            {
                retorno.respuesta = this;
            }
        }
    }

    /**
     * Balancea el sub�rbol izquierdo de un �rbol AVL que se ha desbalanceado por una inserci�n. Actualiza los factores de balanceo. <br>
     * <b>post: </b> Se balance� el sub�rbol izquierdo y se actualizar�n los factores de balanceo. <br>
     * @return Nodo balanceado
     */
    private NodoAVL<T> balanceaIzq( )
    {
        if( izqNodo.balance == BIZQ )
        {
            // Caso 3
            balance = BAL;
            izqNodo.balance = BAL;
            return roteDer( );
        }
        else
        {
            // Caso 5
            switch( izqNodo.derNodo.balance )
            {
                case BIZQ:
                    balance = BDER;
                    izqNodo.balance = BAL;
                    break;
                case BAL:
                    balance = BAL;
                    izqNodo.balance = BAL;
                    break;
                case BDER:
                    balance = BAL;
                    izqNodo.balance = BIZQ;
                    break;
            }
            izqNodo.derNodo.balance = BAL;
            return roteIzqDer( );
        }
    }

    /**
     * Balancea el sub�rbol derecho de un �rbol AVL que se ha desbalanceado por una inserci�n. Actualiza los factores de balanceo. <br>
     * <b>post: </b> Se balance� el sub�rbol derecho y se actualizar�n los factores de balanceo.
     * @return Nodo balanceado
     */
    private NodoAVL<T> balanceaDer( )
    {
        if( derNodo.balance == BDER )
        {
            // Caso 2
            balance = BAL;
            derNodo.balance = BAL;
            return roteIzq( );
        }
        else
        {
            // Caso 4
            switch( derNodo.izqNodo.balance )
            {
                case BIZQ:
                    balance = BAL;
                    derNodo.balance = BDER;
                    break;
                case BAL:
                    balance = BAL;
                    derNodo.balance = BAL;
                    break;
                case BDER:
                    balance = BIZQ;
                    derNodo.balance = BAL;
                    break;
            }
            derNodo.izqNodo.balance = BAL;
            return roteDerIzq( );
        }
    }

    /**
     * Rota a la izquierda un nodo y sus hijos. <br>
     * <b>post: </b> Se rot� a la izquierda un nodo y sus hijos.
     * @return El nodo rotado a la izquierda
     */
    private NodoAVL<T> roteIzq( )
    {
        NodoAVL<T> temp = derNodo;
        derNodo = temp.izqNodo;
        temp.izqNodo = this;
        return temp;
    }

    /**
     * Rota a la derecha un nodo y sus hijos. <br>
     * <b>post: </b> Se rot� a la derecha un nodo y sus hijos.
     * @return Nodo El nodo rotado a la derecha
     */
    private NodoAVL<T> roteDer( )
    {
        NodoAVL<T> temp = izqNodo;
        izqNodo = temp.derNodo;
        temp.derNodo = this;
        return temp;
    }

    /**
     * Rota a la derecha y despu�s a la izquierda un nodo y sus hijos. <br>
     * <b>post: </b> Se rot� a la derecha y despues a la izquierda un nodo y sus hijos.
     * @return Nodo El nodo rotado a la derecha y luego a la izquierda
     */
    private NodoAVL<T> roteDerIzq( )
    {
        derNodo = derNodo.roteDer( );
        return roteIzq( );
    }

    /**
     * Rota a la izquierda y despu�s a la derecha un nodo y sus hijos. <br>
     * <b>post: </b> Se rot� a la izquierda y despues a la derecha un nodo y sus hijos.
     * @return Nodo El nodo rotado a la izquierda y luego a la derecha
     */
    private NodoAVL<T> roteIzqDer( )
    {
        izqNodo = izqNodo.roteIzq( );
        return roteDer( );
    }

    /**
     * Se ha eliminado un elemento del sub�rbol izquierdo y por esta raz�n el sub�rbol derecho se encuentra desbalanceado. Esta rutina reestablece el balance perdido. Al
     * entrar, retorno.diferenciaAltura == TRUE. <br>
     * <b>pre: </b> retorno!=null. <br>
     * <b>post: </b> Se restableci� el balance� perdido a causa de una eliminac�n en el sub�rbol izquierdo.
     * @param retorno Estructura que contiene el �rbol resultado y una variable que me permite determinar si el nodo est� desbalanceado
     */
    private void balanElimDer( Retorno retorno )
    {
        switch( balance )
        {
            case BIZQ:
                balance = BAL;
                retorno.respuesta = this;
                break;
            case BAL:
                balance = BDER;
                retorno.diferenciaAltura = false;
                retorno.respuesta = this;
                break;
            case BDER:
                if( derNodo.balance != BIZQ )
                {
                    retorno.respuesta = roteIzq( );
                    if( retorno.respuesta.balance == BAL )
                    {
                        retorno.respuesta.balance = BIZQ;
                        retorno.respuesta.izqNodo.balance = BDER;
                        retorno.diferenciaAltura = false;
                    }
                    else
                    {
                        retorno.respuesta.balance = BAL;
                        retorno.respuesta.izqNodo.balance = BAL;
                    }
                }
                else
                {
                    retorno.respuesta = roteDerIzq( );
                    if( retorno.respuesta.balance == BDER )
                    {
                        retorno.respuesta.izqNodo.balance = BIZQ;
                    }
                    else
                    {
                        retorno.respuesta.izqNodo.balance = BAL;
                    }
                    if( retorno.respuesta.balance == BIZQ )
                    {
                        retorno.respuesta.derNodo.balance = BDER;
                    }
                    else
                    {
                        retorno.respuesta.derNodo.balance = BAL;
                    }
                    retorno.respuesta.balance = BAL;
                }
                break;
        }
    }

    /**
     * Se ha eliminado un elemento del sub�rbol derecho y por esta raz�n el sub�rbol izquierdo se encuentra desbalanceado. Esta rutina reestablece el balance perdido. Al
     * entrar, ret.difAltura == TRUE. <br>
     * <b>pre: </b> retorno!=null. <br>
     * <b>post: </b> Se restableci� el balance� perdido a causa de una eliminac�n en el sub�rbol derecho.
     * @param retorno Estructura que contiene el �rbol resultado y una variable que me permite determinar si el nodo est� desbalanceado
     */
    private void balanElimIzq( Retorno retorno )
    {
        switch( balance )
        {
            case BIZQ:
                if( izqNodo.balance != BDER )
                {
                    retorno.respuesta = roteDer( );
                    if( retorno.respuesta.balance == BAL )
                    {
                        retorno.respuesta.balance = BDER;
                        retorno.respuesta.derNodo.balance = BIZQ;
                        retorno.diferenciaAltura = false;
                    }
                    else
                    {
                        retorno.respuesta.balance = BAL;
                        retorno.respuesta.derNodo.balance = BAL;
                    }
                }
                else
                {
                    retorno.respuesta = roteIzqDer( );
                    if( retorno.respuesta.balance == BIZQ )
                    {
                        retorno.respuesta.derNodo.balance = BDER;
                    }
                    else
                    {
                        retorno.respuesta.derNodo.balance = BAL;
                    }
                    if( retorno.respuesta.balance == BDER )
                    {
                        retorno.respuesta.izqNodo.balance = BIZQ;
                    }
                    else
                    {
                        retorno.respuesta.izqNodo.balance = BAL;
                    }
                    retorno.respuesta.balance = BAL;
                }
                break;
            case BAL:
                balance = BIZQ;
                retorno.diferenciaAltura = false;
                retorno.respuesta = this;
                break;
            case BDER:
                balance = BAL;
                retorno.respuesta = this;
                break;
        }
    }

    /**
     * Retorna el nodo con el mayor elemento de un �rbol AVL. <br>
     * <b>post: </b> Se retorn� el nodo con el mayor elemento de un �rbol AVL.
     * @return Nodo Nodo con el mayor elemento de un �rbol AVL
     */
    private NodoAVL<T> mayorElemento( )
    {
        return ( derNodo == null ) ? this : derNodo.mayorElemento( );
    }

    /**
     * Retorna el nodo con el menor elemento de un �rbol AVL.<br>
     * <b>post: </b> Se retorn� el nodo con el menor elemento de un �rbol AVL.
     * @return Nodo Nodo con el menor elemento de un �rbol AVL
     */
    private NodoAVL<T> menorElemento( )
    {
        return ( izqNodo == null ) ? this : izqNodo.menorElemento( );
    }

    /**
     * Agrega los elementos al arreglo utilizando un recorrido por niveles, partiendo del nodo actual. <br>
     * <b>post: </b> Se retorno el iterador para recorrer los elementos del �rbol por niveles.
     * @param resultado Resultado del recorrido
     */
    public void darRecorridoNiveles( IteradorSimple<T> resultado )
    {
        ColaEncadenada<NodoAVL<T>> cola = new ColaEncadenada<NodoAVL<T>>( );
        cola.insertar( this );
        while( cola.darLongitud( ) != 0 )
        {
            NodoAVL<T> nodo = null;
            try
            {
                // Toma el primer �rbol de la cola
                nodo = cola.tomarElemento( );
            }
            catch( ColaVaciaException e )
            {
                // Nunca deber�a aparecer esta excepci�n
            }
            try
            {
                resultado.agregar( nodo.elem );
            }
            catch( IteradorException e )
            {
                // Nunca deber�a aparecer esta excepci�n
            }

            // Agrega los dos sub�rboles (si no son vac�os) a la cola
            if( nodo.izqNodo != null )
            {
                cola.insertar( nodo.izqNodo );
            }
            if( nodo.derNodo != null )
            {
                cola.insertar( nodo.derNodo );
            }
        }
    }

    // -----------------------------------------------------------------
    // Clases Privadas
    // -----------------------------------------------------------------

    /**
     * Estructura para retornar la ra�z del �rbol AVL resultado de un proceso de modificaci�n, con un indicador de si su altura ha sido modificada.
     */
    private class Retorno
    {
        // -----------------------------------------------------------------
        // Atributos
        // -----------------------------------------------------------------

        /**
         * Ra�z del �rbol de respuesta
         */
        private NodoAVL<T> respuesta;

        /**
         * Indicador de cambio de altura del �rbol.
         */
        private boolean diferenciaAltura;

        // -----------------------------------------------------------------
        // Constructores
        // -----------------------------------------------------------------

        /**
         * M�todo constructor de la clase Retorno<br>
         * <b>post: </b> Se construy� en objeto retorno con los valores especificados<br>
         * @param pRespuesta Ra�z del �rbol de respuesta
         * @param pDiferenciaAltura Indicador de cambio de altura del �rbol
         */
        private Retorno( NodoAVL<T> pRespuesta, boolean pDiferenciaAltura )
        {
            respuesta = pRespuesta;
            diferenciaAltura = pDiferenciaAltura;
        }
    }
}
