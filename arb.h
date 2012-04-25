/*estructura*/
typedef struct arb{
	/*implementacion ...*/
	struct arb izq;
	struct arb der;
	int value;
}ARB;

/*interfaz*/


newARB(void *tree){
	ARB *arb;

	arb = (ARB *)malloc (sizeof (ARB *));
	/* llenado inicial*/

	arb->izq = NULL;
	arb->der = NULL;
	arb->value= -1;

	return arb;
}
/*funciones a implementar*/
bool isEmpty();
insertarARB(ARB *arb,int element);
buscarARB(int element);
borrarARB(ARB *arb,int element);