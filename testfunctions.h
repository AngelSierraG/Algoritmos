#include "universe.h"
#include "array.h"
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

typedef struct test{
	int type,n,k,range;
	Universe *u;
}Test;

Test *newTest(int type,int n,int k,int range){
	Test *t;
	t = (Test *) malloc (sizeof(Test *));
	t->type = type;
	t->n = n;
	t->k = k;
	t->range = range;
	t->u= (Universe *)newUniverse(range);
	return t;
}

/* Dispatch de funciones dependiendo del tipo de arbol*/
int insertar(void *tree,int type,int element){
	if(type == AVL){
		return insertarAVL(tree,element);
	}
	else if(type == ARB){
		return insertarARB(tree,element);
	}
	else if(type == A23){
		return insertarA23(tree,element);
	}
	else if(type == AB){
		return insertarAB(tree,element);
	}
}

int borrar(void *tree,int type,int element){
	if(type == AVL){
		return borrarAVL(tree,element);
	}
	else if(type == ARB){
		return borrarARB(tree,element);
	}
	else if(type == A23){
		return borrarA23(tree,element);
	}
	else if(type == AB){
		borrarAB(tree,element);
	}
}

int buscar(void *tree,int type,int element){
	if(type == AVL){
		return buscarAVL(tree,element);
	}
	else if(type == ARB){
		return buscarARB(tree,element);
	}
	else if(type == A23){
		return buscarA23(tree,element);
	}
	else if(type == AB){
		return buscarAB(tree,element);
	}
}

void createRandomInsertSeq(Test *t,Array *insert){
	int i;
	for(i=0; i < getArrayLength(insert) ; i++)
		putArrayElem(insert,i, getRandomNumber(t->u));
	return;
}
void createRandomDeleteSeq(Test *t,Array *insert,Array *del){
	int i,j,k;
	copyArray(insert,del);
	for(i=0; i < t->n/2 ; i++){
		srand(time(NULL));
		j= rand() % getArrayLength(del);
		k= rand() % getArrayLength(del);
		suffleArrayElem(del,j,k);
	}
	return;
}

void createSemiOrderInsertSeq(Test *t,Array *insert){
	int i,j,k;
	createRandomInsertSeq(t,insert);
	sortArray(insert);
	for(i=0; i < t->n/4 ; i++){
		srand(time(NULL));
		j= rand() % getArrayLength(insert);
		k= rand() % getArrayLength(insert);
		suffleArrayElem(insert,j,k);
	}
	return;

}
void createSemiOrderDeleteSeq(Test *t,Array* insert,Array *del){
	int i,j,k;
	copyArray(insert,del);
	sortArray(del);
	for(i=0; i < t->n/4 ; i++){
		srand(time(NULL));
		j= rand() % getArrayLength(del);
		k= rand() % getArrayLength(del);
		suffleArrayElem(del,j,k);
	}
	return;
}

void createSearchSeq(Test *t,Array *insert1,Array *insert2,Array *delete1,Array *search){
	int i,j;

	for(i=0; i < getArrayLength(insert1) ;i++)
		if( searchElem(insert1,getArrayElem(delete1,i)) == 1)
			putArrayElem(search,i,-1);
	for(i=0; i < getArrayLength(insert2) ; i++)
		putElem(search,getArrayElem(insert2,i));
	return;
}