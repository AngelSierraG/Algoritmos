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
	void *tree;
	int type,n,k,range;
	Universe *u;
}Test;

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

void createRandomInsertSeq(Array *insert){
	
	return;
}
createRandomDeleteSeq(insert,del);

createSemiOrderInsertSeq(insert);
createSemiOrderDeleteSeq(insert,del);

createSearchSeq(insert1,insert2,delete1,search);