/*estructura*/
typedef struct arb{
	/*implementacion ...*/
	struct arb *izq;
	struct arb *der;
	struct arb *padre;
	int value;
	char color;
}ARB;

/*interfaz*/


ARB *newARB(void *tree){
	ARB *arb;

	arb = (ARB *)malloc (sizeof (ARB *));
	/* llenado inicial*/
	arb->padre = NULL;
	arb->izq = NULL;
	arb->der = NULL;
	arb->value= -1;

	return arb;
}
/*funciones a implementar*/
bool isEmpty();
int insertarARB(ARB *arb,int element);
int buscarARB(ARB *arb,int element);
int borrarARB(ARB *arb,int element);
