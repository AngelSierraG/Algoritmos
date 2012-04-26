#include <stdlib.h>
#include <stdbool.h>
/*#include <math.h>*/
#include "testfunctions.h"

#define CASE1 0
#define CASE2 1
#define CASE3 2
#define CASE4 3

void createIDSequence(Array *insert,Array *del,int seqcase){
	if(CASE1 == seqcase){
		createRandomInsertSeq(insert);
		createRandomDeleteSeq(insert,del);
	}
	else if(CASE2 == seqcase){
		createSemiOrderInsertSeq(insert);
		createSemiOrderDeleteSeq(insert,del);
	}
	else if(CASE3 == seqcase){
		createSemiOrderInsertSeq(insert);
		createRandomDeleteSeq(insert,del);
	}
	else if(CASE4 == seqcase){
		createRandomInsertSeq(insert);
		createSemiOrderDeleteSeq(insert,del);
	}
	return;
}

void createSSequence(Array *insert1,Array *insert2,Array *delete1,Array *search){
	createSearchSeq(insert1,insert2,delete1,search);
	return;
}
/* Funcion que generara la sequencia , utilizando la distintas funciones de buscar, eliminar, e insertar elementos al arreglo*/
void dosequence(Test *t,int seqcase) {
	/*
	(i^k d^k i^k )^n f^k*n (d^k i^k d^k )^n
	*/
	Array *insert1,*insert2,*insert3,*del1,*del2,*del3,*search1;
	int index,i,j;

	index = 0;
	insert1 = newArray(t->k);insert2 = newArray(t->k);insert3 = newArray(t->k);
	del1 = newArray(t->k);del2 = newArray(t->k);del3 = newArray(t->k);
	search1 = newArray(t->k);

	createIDSequence(insert1,del1,seqcase);
	createIDSequence(insert2,del2,seqcase);
	createIDSequence(insert3,del3,seqcase);

	createSSequence(insert1,insert2,del1,search1);

	semioredenados = 

	for(j=0;j<=t->n;j++){
		for(i=0;i<=t->k;i++)
			insertar(t->tree,t->type,getElem(insert1,i) );

		for(i=0;i<=t->k;i++)
			borrar(t->tree,t->type,getElem(del1,i));

		for (i=0;i<=t->k;i++)
			insertar(t->tree,t->type,getElem(insert2,i));

	}
	for(j=0;j<=t->n*t->k;j++){
		buscar(t->tree,t->type, getElem(search1,j));
	}
	for(j=0;j<=t->n;j++){
		for (i=0;i<=t->k;i++)
			borrar(t->tree,t->type, getElem(del2,i) );

		for (i=0;i<=t->k;i++)
			insertar(t->tree,t->type,getElem(insert3,i) );

		for (i=0;i<=t->k;i++)
			borrar(t->tree,t->type, getElem(del3,i) );

	}

	free(insert1);free(insert1);free(insert1);
	free(del1);free(del2);free(del3);
	free(search1);
	return;
}

/*
Los elementos a insertar son escogidos al azar uniformemente del universo,
 y los a borrar,al azar uniformemente del conjunto ya insertado en la estructura
*/
void newSequenceCase1(Test *t){

	/*busqueda del tipo 1, kn/2 búsquedas de elementos ya insertados en la estructura escogidos al azar*/
	dosequence(t,getElemUniverse,searchElemDomain,delElemRandomDomain);

	/*busqueda del tipo 2*, kn/2 búsquedas de elementos escogidos al azar del dominio */
	dosequence(t,getElemUniverse,searchElemUniverse,delElemRandomDomain);
}

/*
Los elementos a insertar y a borrar se encuentran semiordenados.
*/
newSequenceCase2(int k){
	

}

/*
Sólo los elementos a insertar están semiordenados (por lo que los elementos a borrar son
elegidos al azar uniformemente del conjunto insertado)
*/
newSequenceCase3(int k){
	

}

/*
Sólo los elementos a borrar están semiordenados (por lo que los elementos a insertar son
elegidos al azar uniformemente del universo)
*/
newSequenceCase4(int k){
	

}