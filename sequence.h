#include "testfunctions.h"
#include "tree.h"

#define CASE1 0
#define CASE2 1
#define CASE3 2
#define CASE4 3

/*CASE1
Los elementos a insertar son escogidos al azar uniformemente del universo,
 y los a borrar,al azar uniformemente del conjunto ya insertado en la estructura
*/
/*CASE2
Los elementos a insertar y a borrar se encuentran semiordenados.
*/
/*CASE3
Sólo los elementos a insertar están semiordenados (por lo que los elementos a borrar son
elegidos al azar uniformemente del conjunto insertado)
*/
/*CASE4
Sólo los elementos a borrar están semiordenados (por lo que los elementos a insertar son
elegidos al azar uniformemente del universo)
*/

void createISequence(Test *t,Array *insert,int seqcase){
	if(CASE1 == seqcase || CASE4 == seqcase){
		createRandomInsertSeq(t,insert);
	}
	else if(CASE2 == seqcase || CASE3 == seqcase){
		createSemiOrderInsertSeq(t,insert);
	}
	return;
}

void createDSSequence(Test *t,Array *insert,Array *del,Array *search,int seqcase){
	if(CASE1 == seqcase || CASE3 == seqcase){
		createRandomDeleteSearchSeq(t,insert,del,search);
	}
	else if(CASE2 == seqcase || CASE4 == seqcase){
		createSemiOrderDeleteSearchSeq(t,insert,del,search);
	}
	return;
}

void createsequence(void *tree,Test *t,int seqcase){
	createISequence(t,t->insert,seqcase);
	createDSSequence(t,t->insert,t->del,t->search,seqcase);
	return;
}

/* Funcion que generara la sequencia , utilizando la distintas funciones de buscar, eliminar, e insertar elementos al arreglo*/
int dosequence(void *tree,Test *t) {
	int i,j,d,s,timer;
	/*
	(i^k d^k i^k )^n f^k*n (d^k i^k d^k )^n
	*/
	
	/*INICIAR TIEMPO SUB secuencia*/
	timer = time(NULL);

	for(j=0,d=0; j< 2*t->n*t->k ;){
		for(i=0;i < t->k;i++)
			insertar(tree,t->type,getArrayElem(t->insert,j++));

		for(i=0;i < t->k;i++)
			borrar(tree,t->type,getArrayElem(t->del,d++));

		for(i=0;i < t->k;i++)
			insertar(tree,t->type,getArrayElem(t->insert,j++));
	}
	printf("\tTiempo 1era sub secuencia = %d\n", time(NULL)- timer);

	timer = time(NULL);
	for(s=0;s< t->n*t->k;s++){
		buscar(tree,t->type,getArrayElem(t->search,s));
	}
	printf("\tTiempo 2da sub secuencia = %d\n", time(NULL)- timer);
	
	timer = time(NULL);
	for(;j< 3*t->n*t->k ; ){
		for(i=0;i < t->k;i++)
			borrar(tree,t->type,getArrayElem(t->del,d++));

		for(i=0;i < t->k;i++)
			insertar(tree,t->type,getArrayElem(t->insert,j++));

		for(i=0;i < t->k;i++)
			borrar(tree,t->type,getArrayElem(t->del,d++));
	}
	printf("\tTiempo 3era sub secuencia = %d\n", time(NULL)- timer);

	freeTest(t);

	/*FINALIZA TIEMPO*/

	return 1;
}