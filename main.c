#define cAVL 0
#define cARB 1
#define cA23 2
#define cAB  3
/* La idea es tener 1 archivo por implementacion de arbol y sus funciones:*/
#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <math.h>
#include <string.h>
#include <signal.h>
#include "sequence.h"


Test *t;
void *tree;
int type;

char *elementToString(int i){
	if(i == 0)
		return "2^11";
	else if(i == 1)
		return "2^19";
}
char *rangeToString(int i){
	if(i == 0)
		return "2^16";
	else if(i == 1)
		return "2^24";
}

void treeTest(int *n,int *range,int *k,int *insertcase){
	int i,j,r,l;
	for(i=0; i<2 ; i++)
	for(j=0; j<2 ; j++)
	for(r=0; r<3 ; r++){
		printf("creando test\n");
		t = (Test *)newTest(type,n[j],k[r],range[i]);

		for(l=0 ; l<4 ; l++)
			printf("Testeando arbol %s: k=%d\tn=%s\trango=%s\tcaso de insercion =%d\n", treeToString(type),k[r],elementToString(j),rangeToString(i),insertcase[l]);
			createsequence(tree,t,insertcase[l]);
			dosequence(tree,t);
			freeTest(t);
	}
	return;
}

void forcing_close(){
	freeTest(t);
	freeTree(tree,type);
	return;
}

int main(){
	int n[2];
	int range[2];
	int insertcase[4];
	int k[3];
	AVL *avl;ARB *arb;A23 *a23;AB *ab;

	printf("Iniciando testeo\n");

	signal(SIGTERM,forcing_close);
	printf("Modificando SIGTERM por defecto\n");

	range[0] = mypow(2,16);range[1] = mypow(2,24);
	printf("rango creado\n");

	n[0] = mypow(2,11);n[1] = mypow(2,19);
	printf("n creado\n");

	k[0] = 3;k[1] = 5;k[2] =7;
	printf("k creado\n");

	insertcase[0]=CASE1;insertcase[1]=CASE2;insertcase[2]=CASE3;insertcase[3] = CASE4;
	printf("insertcase creado\n");

	printf("Variables iniciadas\n");

	/*newTest(void *tree,int type,int n,int k,int range)*/

	/*AVL TESTING*/
	printf("testeando AVL:\n");
	avl= (AVL *)newAVL();
	tree = (AVL *)avl;
	type = cAVL;
	printf("testing init:\n");
	treeTest(n,range,k,insertcase);
	freeAVL(avl);

	/*ARB TESTING*/
	/*
	printf("testeando ARB:\n");
	tree=(ARB *)newARB();
	type = cARB;
	treeTest(n,range,k,insertcase);
	freeARB(tree);

	/*A23 TESTING*/
	/*
	printf("testeando A23:\n");
	tree=(A23 *)newA23();
	type = cA23;
	treeTest(n,range,k,insertcase);
	freeA23(tree);

	/*AB TESTING*/
	/*
	printf("testeando AB:\n");
	tree=(AB *)newAB();
	type = cAB;
	treeTest(n,range,k,insertcase);
	freeAB(tree);
	*/

	return 0;
}