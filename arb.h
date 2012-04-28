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


ARB *newARB(){
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
bool isEmptyARB(ARB *arb);
void insertarARB(ARB *arb,int element);
int buscarARB(ARB *arb,int element);
void borrarARB(ARB *arb,int element);


void freeARB(ARB *arb){
	if(arb->izq == NULL && arb->der == NULL){
		free(arb);
		return;
	}
	freeARB(arb->izq);
	freeARB(arb->der);
	freeARB(arb->padre);
	free(arb);
	return;
}