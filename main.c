#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <math.h>
#include "sequence.h"

void treeTest(void *tree,Test *t,int type,int *n,int *range,int *k,int *insertcase){
	int i,j,r,l;
	for(i=0; i<2 ; i++)
		for(j=0; j<2 ; j++)
			for(r=0; r<3 ; r++){
				t = newTest(type,n[j],k[r],range[i]);
				for(l=0 ; l<4 ; l++)
					dosequence(tree,t,insertcase[l]);
				freeTest(t);
			}
	return;
}

int main(){
	int type,*n,*range,*k,*insertcase;
	Test *t;
	void *tree;

	range = (int *) range[2];
	n = (int *) n[2];
	k = (int *) k[3];
	insertcase = (int *) insertcase[4];

	range[0] = mypow(2,16);range[1] = mypow(2,24);
	n[0] = mypow(2,11);n[1] = mypow(2,19);
	k[0] = 3;k[1] = 5;k[2] =7;
	insertcase[0]=CASE1;insertcase[1]=CASE2;insertcase[2]=CASE3;insertcase[3] = CASE4;

	/*newTest(void *tree,int type,int n,int k,int range)*/

	/*AVL TESTING*/
	newAVL(tree);
	type = AVL;
	treeTest(tree,t,type,n,range,k,insertcase);
	freeAVL(tree);

	/*ARB TESTING*/
	newARB(tree);
	type = ARB;
	treeTest(tree,t,type,n,range,k,insertcase);
	freeARB(tree);

	/*A23 TESTING*/
	newA23(tree);
	type = A23;
	treeTest(tree,t,type,n,range,k,insertcase);
	freeA23(tree);

	/*AB TESTING*/
	newAB(tree);
	type = AB;
	treeTest(tree,t,type,n,range,k,insertcase);
	freeAB(tree);

	return 0;
}