#include <stdlib.h>
#include <math.h>
#include <universe.h>
#include "array.h"
/* La idea es tener 1 archivo por implementacion de arbol y sus funciones:
#include "avl.h"
#include "arb.h"
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

typedef struct test{
	void *tree;
	int type,n,k,range;
	Universe u;
}Test;

Test *newTest(void *tree,int type,int n,int k,int range){
	Test t;
	t = (Test *) malloc (sizeof(Test *));
	t->tree = tree;
	t->type = type;
	t->n = n;
	t->k = k;
	t->range = range;
	t->u= (Universe *)newUniverse(range);
	return t;
}

/* Funcion que generara la sequencia , utilizando la distintas funciones de buscar, eliminar, e insertar elementos al arreglo*/
void sequence(Test *t,void (* getElem)(),void (* searchElem)(),void (* delElem)()) {
	/*
	(i^k d^k i^k )^n f^k*n (d^k i^k d^k )^n
	*/
	Array *semiordenados;
	int semiordenados[t->k];
	int index;

	index = 0;
	semiordenados = newArray(t->k);

	for(int j=0,j<=t->n;j++){
		for (int i=0,i<=t->k,i++)
			insertar(type,getElem(semiordenados,index++,t));
		index = 0;
		semiordenados = NULL;
		for (int i=0,i<=t->k,i++)
			borrar(type,delElem(semiordenados,index++,t));
		index = 0;
		semiordenados = NULL;
		for (int i=0,i<=t->k,i++)
			insertar(type,getElem(semiordenados,index++,t));
		index = 0;
		semiordenados = NULL;
	}
	for(int j=0,j<=t->n*t->k;j++){
		buscar(type,searchElem(array,t));
	}
	for(int j=0,j<=t->n;j++){
		for (int i=0,i<=t->k,i++)
			borrar(type,delElem(semiordenados,index++,t));
		index = 0;
		semiordenados = NULL;
		for (int i=0,i<=t->k,i++)
			insertar(type,getElem(semiordenados,index++,t));
		index = 0;
		semiordenados = NULL;
		for (int i=0,i<=t->k,i++)
			borrar(type,delElem(semiordenados,index++,t));
		index = 0;
		semiordenados = NULL;
	}

	free(semiordenados);
	return;
}

int getElemRandom(Array *array,int index,void *tree){
	return getRandomNumber(tree->u);
}
int getElemSemiOrden(Array *array,int index,void *tree){
	if (array == NULL){
		array = (Array *) malloc (sizeof (Array *));
		array->length = t-> k;
		array->values = int [t->k];
		for(int i=0; i < t-> k, i++)
			array->values[i] = getRandomNumber(t->u);
		sort(array);
	}
	return array->values[index];

}
int searchElemUniverse(Array *array,void *tree){
	return getRandomNumber(tree->u);
}
int searchElemDomain(Array *array,void *tree){
	int random_index,length;

	random_index = (int)(srand (time(NULL)) %(array->length -1) );

	return array->values[random_index];
}

int delElemRandomDomain(Array *array,int index,void *tree){
	int random_index,length;

	random_index = (int)(srand (time(NULL)) %(array->length -1) );
	array->length--;

	return array->values[random_index];
}

/*
Los elementos a insertar son escogidos al azar uniformemente del universo,
 y los a borrar,al azar uniformemente del conjunto ya insertado en la estructura
*/
void newSequenceCase1(Test *t,Array *array){

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



/* Dispatch de funciones dependiendo del tipo de arbol*/
void insertar(int type,Array *array,int element){
	if(type == AVL){
		insertarAVL(array,element);
	}
	else if(type == ARB){
		insertarARB(array,element);
	}
	else if(type == A23){
		insertarA23(array,element);
	}
	else if(type == AB){
		insertarAB(array,element);
	}
}

void borrar(int type,Array *array,int element){
	if(type == AVL){
		borrarAVL(array,element);
	}
	else if(type == ARB){
		borrarARB(array,element);
	}
	else if(type == A23){
		borrarA23(array,element);
	}
	else if(type == AB){
		borrarAB(array,element);
	}
}

void buscar(int type,Array *array,int element){
	if(type == AVL){
		buscarAVL(array,element);
	}
	else if(type == ARB){
		buscarARB(array,element);
	}
	else if(type == A23){
		buscarA23(array,element);
	}
	else if(type == AB){
		buscarAB(array,element);
	}
}