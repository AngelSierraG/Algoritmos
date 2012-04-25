/*estructura*/
typedef struct avl{
	/*implementacion ...*/
	struct avl *izq;
	struct avl *der;
	int value;
	int Height;
}AVL;

/*interfaz*/


AVL *newAVL(void *tree){
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
<<<<<<< HEAD
bool isEmpty(AVL *p);
AVL buscarAVL(AVL *avl,int element);
AVL insertarAVL(AVL *avl,int element);
AVL borrarAVL(AVL *avl,int element);
=======
bool isEmpty();
void insertarAVL(AVL *avl,int element);
int buscarAVL(AVL *avl,int element);
void borrarAVL(AVL *avl,int element);
>>>>>>> db71d30cbfedddd2510c33645fafe4643637d672
