/*estructura*/
typedef struct ab{
	/*implementacion ...*/
	struct ab *izq;
	struct ab *der;
	int value;
	int Height;
}AB;

/*interfaz*/


AB *newAB(){
	AB *ab;

	ab = (AB *)malloc (sizeof (AB *));
	/* llenado inicial*/

	ab->izq = NULL;
	ab->der = NULL;
	ab->value= -1;
	ab->Height= 0;

	return ab;
}
/*funciones a implementar*/
bool isEmptyAB(AB *ab){return false;}
void insertarAB(AB *ab,int element){return;}
int buscarAB(AB *ab,int element){return -1;}
void borrarAB(AB *ab,int element){return;}

void freeAB(AB *ab){
	if(ab->izq == NULL && ab->der == NULL){
		free(ab);
		return;
	}
	freeAB(ab->izq);
	freeAB(ab->der);
	free(ab);
	return;
}