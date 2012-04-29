#include "universe.h"
#include "array.h"

typedef struct test{
	int type,n,k,range;
	Array *insert,*del,*search;
	Universe *u;
}Test;

Test *newTest(int type,int n,int k,int range){
	Test *aux;
	aux = (Test *) malloc(sizeof(struct test));
	printf("creando estructura de test \n");
	aux->type = type;
	aux->n = n;
	aux->k = k;
	aux->range = range;
	aux->u= (Universe *)newUniverse(range);
	aux->insert = newArray(k * n * 3);
	aux->del = newArray(k * n * 3);
	aux->search = newArray(k*n);
	return aux;
}

void freeTest(Test *t){
	free(t->insert);
	free(t->del);
	free(t->search);
	free(t->u);
	free(t);
	return ;
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

