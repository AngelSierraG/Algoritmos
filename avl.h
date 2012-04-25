/*estructura*/
typedef struct avl{
	/*implementacion ...*/
	struct avl izq;
	struct avl der;
	int value;
}AVL;

/*interfaz*/


newAVL(void *tree){
	AVL *avl;

	avl = (AVL *)malloc (sizeof (AVL *));
	/* llenado inicial*/

	avl->izq = NULL;
	avl->der = NULL;
	avl->value= -1;

	return avl;
}
/*funciones a implementar*/
bool isEmpty();
insertarAVL(AVL *avl,int element);
buscarAVL(int element);
borrarAVL(AVL *avl,int element);