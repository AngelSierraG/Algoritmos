/*estructura*/
typedef struct avl{
	/*implementacion ...*/
	struct avl *izq;
	struct avl *der;
	int value;
	int Height;
}AVL;

/*interfaz*/


AVL *newAVL(){
	AVL *avl;

	avl = (AVL *)malloc (sizeof (AVL *));
	/* llenado inicial*/

	avl->izq = NULL;
	avl->der = NULL;
	avl->value= -1;
	avl->Height= 0;

	return avl;
}
/*funciones a implementar*/
bool isEmptyAVL(AVL *avl);
void insertarAVL(AVL *avl,int element);
int buscarAVL(AVL *avl,int element);
void borrarAVL(AVL *avl,int element);

void freeAVL(AVL *avl){
	if(avl->izq == NULL && avl->der == NULL){
		free(avl);
		return;
	}
	freeAVL(avl->izq);
	freeAVL(avl->der);
	free(avl);
	return;
}