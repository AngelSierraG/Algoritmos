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


char *treeToString(int tree){
	if(AVL == tree)
		return "AVL";
	else if(ARB == tree)
		return "ARB";
	else if(A23 == tree)
		return "A23";
	else if(AB == tree)
		return "AB";
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
		putElem(insert, getRandomNumber(t->u));
	return;
}

void createRandomDeleteSearchSeq(Test *t,Array *insert,Array *del,Array *search){
	int i,j,s,k,auxelem;
	Array *tengo;

	tengo = newArray(t->k * t->n);

	for(j=0; j< t->n*t->k ;){
		for(i=0;i < t->k;i++){
			putElem(tengo,getArrayElem(insert,j++));
		}
		shuffleArray(tengo,t->k/2);
		for(i=0;i < t->k;i++){
			auxelem = getArrayElem(tengo,i);
			putElem(del,auxelem);
			delElem(tengo,auxelem);
		}
		for(i=0;i < t->k;i++)
			putElem(tengo,getArrayElem(insert,j++));
	}
	
	shuffleArray(tengo,t->k/2);
	for(s=0;s< t->n*t->k;s++){
		putElem(search,getArrayElem(tengo,s));
	}
	
	for(;j< t->n*t->k ;){
		shuffleArray(tengo,t->k/2);
		for(i=0;i < t->k;i++){
			auxelem = getArrayElem(tengo,i);
			putElem(del,auxelem);
			delElem(tengo,auxelem);
		}
		for(i=0;i < t->k;i++){
			putElem(tengo,getArrayElem(insert,j++));
		}
		shuffleArray(tengo,t->k/2);
		for(i=0;i < t->k;i++){
			auxelem = getArrayElem(tengo,i);
			putElem(del,auxelem);
			delElem(tengo,auxelem);
		}
	}

	return;
}

void createSemiOrderInsertSeq(Test *t,Array *insert){
	createRandomInsertSeq(t,insert);
	sortArray(insert);
	shuffleArray(insert,t->n/4);
	return;
}
void createSemiOrderDeleteSearchSeq(Test *t,Array* insert,Array *del,Array *search){
	createRandomDeleteSearchSeq(t,insert,del,search);
	sortArray(del);
	sortArray(search);

	shuffleArray(del,t->n/4);
	shuffleArray(search,t->n/4);
	return;
}