#include <stdlib.h>
#include <stdbool.h>
/*#include <math.h>*/
#include "testfunctions.h"
/* La idea es tener 1 archivo por implementacion de arbol y sus funciones:*/
#include "avl.h"
#include "arb.h"

/*
#include "a23.h"
#include "ab.h"
*/

#define AVL 0
#define ARB 1
#define A23 2
#define AB  3

/*
#define CASEI1 4
#define CASEI2 5
#define CASEI3 6
#define CASEI4 7
#define CASEB1 8
#define CASEB2 9
*/

Test *newTest(void *tree,int type,int n,int k,int range){
	Test *t;
	t = (Test *) malloc (sizeof(Test *));
	t->tree = tree;
	t->type = type;
	t->n = n;
	t->k = k;
	t->range = range;
	t->u= (Universe *)newUniverse(range);
	return t;
}

/* Dispatch de funciones dependiendo del tipo de arbol*/
void insertar(void *tree,int type,int element){
	if(type == AVL){
		insertarAVL(tree,element);
	}
	else if(type == ARB){
		insertarARB(tree,element);
	}
	else if(type == A23){
		insertarA23(tree,element);
	}
	else if(type == AB){
		insertarAB(tree,element);
	}
}

void borrar(void *tree,int type,int element){
	if(type == AVL){
		borrarAVL(tree,element);
	}
	else if(type == ARB){
		borrarARB(tree,element);
	}
	else if(type == A23){
		borrarA23(tree,element);
	}
	else if(type == AB){
		borrarAB(tree,element);
	}
}

void buscar(void *tree,int type,int element){
	if(type == AVL){
		buscarAVL(tree,element);
	}
	else if(type == ARB){
		buscarARB(tree,element);
	}
	else if(type == A23){
		buscarA23(tree,element);
	}
	else if(type == AB){
		buscarAB(tree,element);
	}
}

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