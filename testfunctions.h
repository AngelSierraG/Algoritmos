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

int getElemUniverse(Array *array,int index,Test *test){
	return getRandomNumber((Universe *)test->u);
}
int getElemSemiOrden(Array *array,int index,Test *test){
	int i;
	if (isEmptyArray(array)){
		array = newArray(test->k);

		for(i=0; i < test->k; i++)
			putArrayElem(array,i,getRandomNumber(test->u));
		sortArray(array);
	}
	return getArrayElem(array,index);

}
int searchElemUniverse(Array *array,Test *test){
	return getRandomNumber((Universe *)test->u);
}
int searchElemDomain(Array *array,Test *test){
	int random_index;
	srand(time(NULL));
	random_index = rand() % getArrayLength(array);

	return getArrayElem(array,random_index);
}

int delElemRandomDomain(Array *array,int index,Test *test){
	int random_index;
	srand (time(NULL));
	random_index = rand() % getArrayLength(array);

	delArrayElem(array,random_index);

	return getArrayElem(array,index);
}