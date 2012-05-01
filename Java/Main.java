
import arbol.avl.*; 
import arbol.arbolRojoNegro.*; 
import arbol.*;


import iterador.*;
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

private static void treeTest(IArbolOrdenado<Integer> tree,String type[],int n[],int range[],int k[],int[] insertcase) throws ElementoExisteException, ElementoNoExisteException //throws ElementoExisteException, ElementoNoExisteException{
, InstantiationException, IllegalAccessException
{
	int i,j,r,l,m;
	Test t;

	t=new Test();
	for(i=0; i<2 ; i++){
	for(l=0 ; l<4 ; l++){
	for(j=0; j<2 ; j++){
	for(r=0; r<3 ; r++){
	for(m=0; m<4 ; m++){ 
		
		System.out.printf("%s con: k=%d  n=%s\trango=%s\tcaso de insercion=%d\t",type[m],k[r],n[j],range[i],insertcase[l]);
		t.setTest(n[j],k[r],range[i],insertcase[l]);
		
		if(m==0){
			tree = new ArbolAVL<Integer>();
		}
		else if(m==1)
			tree = new ArbolRojoNegro<Integer>();
		else if(m==2)
			tree = new Arbol2_3<Integer>();
		else
			tree = new ArbolB<Integer>(256);
		
		Sequence.createsequence(t,insertcase[l]);
		Sequence.dosequence(tree,t);
		t.u.restart();
	}
	//}
	}
	}
	}
	}
	return;
}

public static void main(String[] args) throws ElementoExisteException, ElementoNoExisteException //throws ElementoExisteException, ElementoNoExisteException {
, InstantiationException, IllegalAccessException
{
	int n[],range[],insertcase[],k[];
	IArbolOrdenado<Integer> tree = null;
	String type[];

	n = new int[2];
	range = new int[2];
	insertcase = new int[4];
	k = new int[3];
	type = new String[4];
	
	System.out.printf("Iniciando testeo:\n");

	range[0] = MyMath.pow(2,16);
	range[1] = MyMath.pow(2,24);

	n[0] = MyMath.pow(2,11);
	//n[1] = MyMath.pow(2,19);
	n[1] = MyMath.pow(2,16);
	

	k[0] = 3;
	k[1] = 5;
	k[2] =7;
	insertcase[0]=case1;insertcase[1]=case2;insertcase[2]=case3;insertcase[3] = case4;

	type[0] = "\nTesteando AVL:\n";
	
	
	type[1] = "\nTesteando ARB:\n";

	type[2] = "\nTesteando A23:\n";
	

	type[3] = "\nTesteando AB:\n";
	
	treeTest(tree,type,n,range,k,insertcase);

	return;
}
}