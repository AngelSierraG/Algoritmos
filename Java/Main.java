import java.io.*;
import java.Math;

public class Main {
static final int cAVL 0;
static final int cARB 1;
static final int cA23 2;
static final int cAB  3;

private String elementToString(int i){
	if(i == 0)
		return "2^11";
	else if(i == 1)
		return "2^19";
}
private String rangeToString(int i){
	if(i == 0)
		return "2^16";
	else if(i == 1)
		return "2^24";
}

private String treeToString(int tree){
	if(cAVL == tree)
		return "AVL";
	else if(cARB == tree)
		return "ARB";
	else if(cA23 == tree)
		return "A23";
	else if(cAB == tree)
		return "AB";
}

private void treeTest(Tree tree,int n[],int range[],int k[],int insertcase[]){
	int i,j,r,l;
	for(i=0; i<2 ; i++)
	for(j=0; j<2 ; j++)
	for(r=0; r<3 ; r++){
		System.out.printf("creando test\n");
		Test t = newTest(type,n[j],k[r],range[i]);

		for(l=0 ; l<4 ; l++)
			System.out.printf("Testeando arbol %s: k=%d\tn=%s\trango=%s\tcaso de insercion =%d\n", treeToString(type),k[r],elementToString(j),rangeToString(i),insertcase[l]);
			Sequence.createsequence(tree,t,insertcase[l]);
			Sequence.dosequence(tree,t);
			//freeTest(t);
	}
	return;
}

public static void main(String[] args) {
	int n[],range[],insertcase[],k[];
	Tree t;

	n = new int[2];
	range = new int[2];
	insertcase = new int[4];
	k = new int[3];

	//AVL *avl;ARB *arb;A23 *a23;AB *ab;

	System.out.printf("Iniciando testeo\n");


	range[0] = Math.pow(2,16);range[1] = Math.pow(2,24);
	System.out.printf("rango creado\n");

	n[0] = Math.pow(2,11);n[1] = Math.pow(2,19);
	System.out.printf("n creado\n");

	k[0] = 3;k[1] = 5;k[2] =7;
	System.out.printf("k creado\n");

	insertcase[0]=case1;insertcase[1]=case2;insertcase[2]=case3;insertcase[3] = case4;
	System.out.printf("insertcase creado\n");

	System.out.printf("Variables iniciadas\n");

	/*AVL TESTING*/
	System.out.printf("testeando AVL:\n");

	tree = (AVL)newAVL();

	//type = cAVL;
	System.out.printf("testing init:\n");
	treeTest(tree,n,range,k,insertcase);
	
	//freeAVL(avl);

	/*ARB TESTING*/
	/*
	System.out.printf("testeando ARB:\n");
	tree=(ARB *)newARB();
	type = cARB;
	treeTest(n,range,k,insertcase);
	freeARB(tree);

	/*A23 TESTING*/
	/*
	System.out.printf("testeando A23:\n");
	tree=(A23 *)newA23();
	type = cA23;
	treeTest(n,range,k,insertcase);
	freeA23(tree);

	/*AB TESTING*/
	/*
	System.out.printf("testeando AB:\n");
	tree=(AB *)newAB();
	type = cAB;
	treeTest(n,range,k,insertcase);
	freeAB(tree);
	*/

	return;
}
}