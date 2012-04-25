#include <stdlib.h>
#include <stdbool.h>
/*#include <math.h>*/
#include "testfunctions.h"

/* Funcion que generara la sequencia , utilizando la distintas funciones de buscar, eliminar, e insertar elementos al arreglo*/
void sequence(Test *t,	int (* getElem)(Array *,int,Test *), int (* searchElem)(Array *,Test *),	int (* delElem)(Array *,int,Test *)) {
	/*
	(i^k d^k i^k )^n f^k*n (d^k i^k d^k )^n
	*/
	Array *semiordenados;
	int index,i,j;

	index = 0;
	semiordenados = newArray(t->k);

	for(j=0;j<=t->n;j++){
		for(i=0;i<=t->k;i++)
			insertar(t->tree,t->type,getElem(semiordenados,index++,t));
		index = 0;
		deleteArrayElements(semiordenados);
		for(i=0;i<=t->k;i++)
			borrar(t->tree,t->type,delElem(semiordenados,index++,t));
		index = 0;
		deleteArrayElements(semiordenados);
		for (i=0;i<=t->k;i++)
			insertar(t->tree,t->type,getElem(semiordenados,index++,t));
		index = 0;
		deleteArrayElements(semiordenados);
	}
	for(j=0;j<=t->n*t->k;j++){
		buscar(t->tree,t->type,searchElem(semiordenados,t));
	}
	for(j=0;j<=t->n;j++){
		for (i=0;i<=t->k;i++)
			borrar(t->tree,t->type,delElem(semiordenados,index++,t));
		index = 0;
		deleteArrayElements(semiordenados);
		for (i=0;i<=t->k;i++)
			insertar(t->tree,t->type,getElem(semiordenados,index++,t));
		index = 0;
		deleteArrayElements(semiordenados);
		for (i=0;i<=t->k;i++)
			borrar(t->tree,t->type,delElem(semiordenados,index++,t));
		index = 0;
		deleteArrayElements(semiordenados);
	}

	free(semiordenados);
	return;
}

/*
Los elementos a insertar son escogidos al azar uniformemente del universo,
 y los a borrar,al azar uniformemente del conjunto ya insertado en la estructura
*/
void newSequenceCase1(Test *t){

	/*busqueda del tipo 1, kn/2 búsquedas de elementos ya insertados en la estructura escogidos al azar*/
	sequence(t,getElemRandom,searchElemDomain,delElemRandomDomain);

	/*busqueda del tipo 2*, kn/2 búsquedas de elementos escogidos al azar del dominio */
	sequence(t,getElemRandom,searchElemUniverse,delElemRandomDomain);
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