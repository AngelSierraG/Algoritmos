
import arbol.avl.*; 
import arbol.arbolRojoNegro.*; 
import arbol.*;


import iterador.*;
import range.*;
import lista.*;
import colaEncadenada.*;
import interadorSinMemoria.*;
import listaEncadenada.*;

import arbol.*;
import arbol.avl.*;
import arbol.arbol2_3.*;
import arbol.arbolB.*;
import arbol.arbolRojoNegro.*;


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
			System.out.printf("Testeando arbol con: k=%d  n=%s\trango=%s\tcaso de insercion=%d\t",k[r],n[j],range[i],insertcase[l]);
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

	n = new int[2];
	range = new int[2];
	insertcase = new int[4];
	k = new int[3];

	System.out.printf("Iniciando testeo:\n");

	range[0] = MyMath.pow(2,16);
	range[1] = MyMath.pow(2,24);

	n[0] = MyMath.pow(2,11);
	//n[0] = 5;
	n[1] = MyMath.pow(2,19);
	//n[1] = 8;


	k[0] = 3;
	k[1] = 5;
	k[2] =7;
	insertcase[0]=case1;insertcase[1]=case2;insertcase[2]=case3;insertcase[3] = case4;


	/*AVL TESTING*/
	System.out.printf("Testeando AVL:\n");

	tree = new ArbolAVL<Integer>();
	treeTest(tree,n,range,k,insertcase);
	
	/*ARB TESTING*/
	System.out.printf("\nTesteando ARB:\n"); 
	tree = new ArbolRojoNegro<Integer>();
	treeTest(tree,n,range,k,insertcase);

	/*A23 TESTING*/
	System.out.printf("\nTesteando A23:\n");
	tree= new Arbol2_3<Integer>();
	treeTest(tree,n,range,k,insertcase);

	/*AB TESTING*/
	System.out.printf("\nTesteando AB:\n");
	tree= new ArbolB<Integer>(256);
	treeTest(tree,n,range,k,insertcase);

	return;
}
}