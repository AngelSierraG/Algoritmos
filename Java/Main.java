import arbol.avl.*; 
import arbol.arbolRojoNegro.*; 
import arbol.*;
import tree.*;
import avlTree.*;
import iterador.*;
import range.*;
import lista.*;
import colaEncadenada.*;
import interadorSinMemoria.*;
import listaEncadenada.*;

public class Main {
static final int cAVL=0;
static final int cARB=1;
static final int cA23=2;
static final int cAB =3;
static final int case1=0;
static final int case2=1;
static final int case3=2;
static final int case4=3;

private static void treeTest(IArbolOrdenado<Integer> tree,int n[],int range[],int k[],int[] insertcase) throws ElementoExisteException, ElementoNoExisteException //throws ElementoExisteException, ElementoNoExisteException{
{
	int i,j,r,l;
	for(i=0; i<2 ; i++){
	for(j=0; j<2 ; j++){
	for(r=0; r<3 ; r++){
		//System.out.printf("Creando test\n");
		Test t = new Test(n[j],k[r],range[i]);
		for(l=0 ; l<4 ; l++){
			System.out.printf("Testeando arbol con: k=%d\tn=%s\trango=%s\tcaso de insercion =%d\n",k[r],n[j],range[i],insertcase[l]);
			Sequence.createsequence(t,insertcase[l]);
			Sequence.dosequence(tree,t);
			t.u.restart();
			}
			//freeTest(t);
	}}}
	return;
}

public static void main(String[] args) throws ElementoExisteException, ElementoNoExisteException //throws ElementoExisteException, ElementoNoExisteException {
{
	int n[],range[],insertcase[],k[];
	IArbolOrdenado<Integer> tree;
	//Tree tree;

	n = new int[2];
	range = new int[2];
	insertcase = new int[4];
	k = new int[3];

	//AVL *avl;ARB *arb;A23 *a23;AB *ab;

	System.out.printf("Iniciando testeo\n");


	range[0] = MyMath.pow(2,16);
	range[1] = MyMath.pow(2,24);
	//System.out.printf("rango creado\n");

	//n[0] = MyMath.pow(2,11);
	n[0] = 5;
	//n[1] = MyMath.pow(2,19);
	n[1] = 8;
	//System.out.printf("n creado\n");

	k[0] = 3;
	k[1] = 5;
	k[2] =7;
	//System.out.printf("k creado\n");

	insertcase[0]=case1;insertcase[1]=case2;insertcase[2]=case3;insertcase[3] = case4;
	//System.out.printf("insertcase creado\n");

	//System.out.printf("Variables iniciadas\n");

	/*AVL TESTING*/
	System.out.printf("Testeando AVL:\n");

	tree = new ArbolAVL<Integer>();
	//tree = new AvlTree();

	//type = cAVL;
	//System.out.printf("Testing init:\n");
	treeTest(tree,n,range,k,insertcase);
	
	//freeAVL(avl);

	/*ARB TESTING*/
	System.out.printf("Testeando ARB:\n");

	tree = new ArbolRojoNegro<Integer>();
	//tree = new AvlTree();

	//type = cAVL;
	//System.out.printf("Testing init:\n");
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