/*estructura*/
typedef struct arb{
	/*implementacion ...*/
	struct arb *izq;
	struct arb *der;
	int value;
}ARB;

/*interfaz*/


ARB *newARB(void *tree){
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
void insertarARB(ARB *arb,int element);
int buscarARB(ARB *arb,int element);
void borrarARB(ARB *arb,int element);