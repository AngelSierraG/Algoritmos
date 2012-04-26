#include <stdlib.h>
#include <stdbool.h>
/*#include <math.h>*/
#include "testfunctions.h"

#define CASE1 0
#define CASE2 1
#define CASE3 2
#define CASE4 3

void createIDSequence(Test *t,Array *insert,Array *del,int seqcase){
	if(CASE1 == seqcase){
		createRandomInsertSeq(t,insert);
		createRandomDeleteSeq(t,insert,del);
	}
	else if(CASE2 == seqcase){
		createSemiOrderInsertSeq(t,insert);
		createSemiOrderDeleteSeq(t,insert,del);
	}
	else if(CASE3 == seqcase){
		createSemiOrderInsertSeq(t,insert);
		createRandomDeleteSeq(t,insert,del);
	}
	else if(CASE4 == seqcase){
		createRandomInsertSeq(t,insert);
		createSemiOrderDeleteSeq(t,insert,del);
	}
	return;
}

void createSSequence(Test *t,Array *insert1,Array *insert2,Array *delete1,Array *search){
	createSearchSeq(t,insert1,insert2,delete1,search);
	return;
}
/* Funcion que generara la sequencia , utilizando la distintas funciones de buscar, eliminar, e insertar elementos al arreglo*/
int dosequence(Test *t,int seqcase) {
	/*
	(i^k d^k i^k )^n f^k*n (d^k i^k d^k )^n
	*/
	Array *insert1,*insert2,*insert3,*del1,*del2,*del3,*search1;
	int index,i,j,contador,timer;

	index = 0;
	timer = time(NULL);

	insert1 = newArray(t->k);insert2 = newArray(t->k);insert3 = newArray(t->k);
	del1 = newArray(t->k);del2 = newArray(t->k);del3 = newArray(t->k);
	search1 = newArray(t->k);
	
	createIDSequence(t,insert1,del1,seqcase);
	createIDSequence(t,insert2,del2,seqcase);
	createIDSequence(t,insert3,del3,seqcase);

	createSSequence(t,insert1,insert2,del1,search1);

	/*INICIAR TIEMPO*/


	for(j=0;j<=t->n;j++){
		for(i=0;i<=t->k;i++)
			if (contador < t->n)
				contador= contador + insertar(t->tree,t->type,getArrayElem(insert1,i) );

		for(i=0;i<=t->k;i++)
			contador= contador - borrar(t->tree,t->type,getArrayElem(del1,i));

		for (i=0;i<=t->k;i++)
			if (contador < t->n)
				contador= contador + insertar(t->tree,t->type,getArrayElem(insert2,i));

	}
	for(j=0;j<=t->n*t->k;j++){
		buscar(t->tree,t->type, getArrayElem(search1,j));
	}
	for(j=0;j<=t->n;j++){
		for (i=0;i<=t->k;i++)
			contador= contador - borrar(t->tree,t->type, getArrayElem(del2,i) );

		for (i=0;i<=t->k;i++)
			if (contador < t->n)
				contador= contador + insertar(t->tree,t->type,getArrayElem(insert3,i) );

		for (i=0;i<=t->k;i++)
			contador= contador - borrar(t->tree,t->type, getArrayElem(del3,i) );

	}

	free(insert1);free(insert1);free(insert1);
	free(del1);free(del2);free(del3);
	free(search1);

	/*FINALIZA TIEMPO*/

	return time(NULL) - timer;
}

/*
Los elementos a insertar son escogidos al azar uniformemente del universo,
 y los a borrar,al azar uniformemente del conjunto ya insertado en la estructura
*/


/*
Los elementos a insertar y a borrar se encuentran semiordenados.
*/


/*
Sólo los elementos a insertar están semiordenados (por lo que los elementos a borrar son
elegidos al azar uniformemente del conjunto insertado)
*/


/*
Sólo los elementos a borrar están semiordenados (por lo que los elementos a insertar son
elegidos al azar uniformemente del universo)
*/
