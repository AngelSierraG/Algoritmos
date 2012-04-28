/*estructura*/
typedef struct a23{
	/*implementacion ...*/
	struct a23 *izq;
	struct a23 *der;
	struct a23 *med;
	int value;
	int Height;
}A23;

/*interfaz*/


A23 *newA23(){
	A23 *a23;

	a23 = (A23 *)malloc (sizeof (A23 *));
	/* llenado inicial*/

	a23->izq = NULL;
	a23->der = NULL;
	a23->value= -1;
	a23->Height= 0;

	return a23;
}
/*funciones a implementar*/
bool isEmptyA23(A23 *a23){return false;}
void insertarA23(A23 *a23,int element){return;}
int buscarA23(A23 *a23,int element){return -1;}
void borrarA23(A23 *a23,int element){return;}

void freeA23(A23 *a23){
	if(a23->izq == NULL && a23->der == NULL){
		free(a23);
		return;
	}
	freeA23(a23->izq);
	freeA23(a23->med);
	freeA23(a23->der);
	free(a23);
	return;
}