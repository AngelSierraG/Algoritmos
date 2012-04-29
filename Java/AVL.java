public class AVL implements Tree{
private AVL left;
private AVL rigth;
private int heigth;

/*funciones a implementar*/
bool estaVacio(){
	if(p->value == -1)
		return true;
	else
		false;
}


static int Height( AVL *P )
	{
	    if( P == NULL )
	        return -1;
	    else
	        return P->Height;
	}
	 

static int Max( int Lhs, int Rhs )
	{
	    return Lhs > Rhs ? Lhs : Rhs;
	}
	 
	 
	/* This function can be called only if K2 has a izq child */
	/* Perform a rotate between a node (K2) and its izq child */
	/* Update heights, then return new root */
	 
static AVL *SingleRotateWithizq( AVL *K2 )
	{
	    AVL *K1;
	 
	    K1 = K2->izq;
	    K2->izq = K1->der;
	    K1->der = K2;
	 
	    K2->Height = Max( Height( K2->izq ), Height( K2->der ) ) + 1;
	    K1->Height = Max( Height( K1->izq ), K2->Height ) + 1;
	 
	    return K1;  /* New root */
	}
	 
	 
	/* This function can be called only if K1 has a der child */
	/* Perform a rotate between a node (K1) and its der child */
	/* Update heights, then return new root */
static AVL *SingleRotateWithder( AVL *K1 )
	{
	    AVL *K2;
	 
	    K2 = K1->der;
	    K1->der = K2->izq;
	    K2->izq = K1;
	 
	    K1->Height = Max( Height( K1->izq ), Height( K1->der ) ) + 1;
	    K2->Height = Max( Height( K2->der ), K1->Height ) + 1;
	 
	    return K2;  /* New root */
	}
	 
	 
	/* This function can be called only if K3 has a izq */
	/* child and K3's izq child has a der child */
	/* Do the izq-der double rotation */
	/* Update heights, then return new root */
	 
static AVL *DoubleRotateWithizq( AVL *K3 )
	{
	    /* Rotate between K1 and K2 */
	    K3->izq = SingleRotateWithder( K3->izq );
	 
	    /* Rotate between K3 and K2 */
	    return SingleRotateWithizq( K3 );
	}
	 
	 
	/* This function can be called only if K1 has a der */
	/* child and K1's der child has a izq child */
	/* Do the der-izq double rotation */
	/* Update heights, then return new root */
	 
static AVL *DoubleRotateWithder( AVL *K1 )
	{
	    /* Rotate between K3 and K2 */
	    K1->der = SingleRotateWithizq( K1->der );
	 
	    /* Rotate between K1 and K2 */
	    return SingleRotateWithder( K1 );
	}


void insertarAVL(AVL *avl,int element){
	if( avl == NULL )
    {
	        /* Create and return a one-node tree */
	        avl = newAVL();
	        if( avl == NULL ){
	            /*FatalError( "Out of space!!!" );*/
	            printf("Out of space!!!");
	            exit(1);
	        }

	        else
	        {
	            avl->value = element; avl->Height = 0;
	            avl->izq = avl->der = NULL;
	        }
	    }
	    else
	        if( element < avl->value )
	        {
	            insertarAVL(avl->izq,element );
	            if( Height( avl->izq ) - Height( avl->der ) == 2 )
	                if( element < avl->izq->value )
	                    avl = SingleRotateWithizq( avl );
	                else
	                    avl = DoubleRotateWithizq( avl );
	        }
	        else
	            if( element > avl->value )
	            {
	                insertarAVL(avl->der, element );
	                if( Height( avl->der ) - Height( avl->izq ) == 2 )
	                    if( element > avl->der->value )
	                        avl = SingleRotateWithder( avl );
	                    else
	                        avl = DoubleRotateWithder( avl );
	            }
	            /* Else X is in the tree already; we'll do nothing */
	 
	            avl->Height = Max( Height( avl->izq ), Height( avl->der ) ) + 1;
	            //return avl;

}

int buscarAVL(AVL *avl,int element){
	if( avl == NULL )
	    return -1;
	    if( element < avl->value)
	    return buscarAVL(avl->izq,element);
	    else
	        if( element > avl->value )
	            return buscarAVL(avl->der,element );
	        else
	            return avl->value;
}

void borrarAVL(AVL *avl,int element){

	/*avl2=buscarAVL(avl,element);*/

}}