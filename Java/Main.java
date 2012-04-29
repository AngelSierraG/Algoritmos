import arbol.avl.*; 
import arbol.*;

public class Main {
static final int cAVL=0;
static final int cARB=1;
static final int cA23=2;
static final int cAB =3;
static final int case1=0;
static final int case2=1;
static final int case3=2;
static final int case4=3;

private static String elementToString(int i){
	if(i == 0)
		return "2^11";
	else if(i == 1)
		return "2^19";
	return "";
}
private static String rangeToString(int i){
	if(i == 0)
		return "2^16";
	else if(i == 1)
		return "2^24";
	return "";
}

private static void treeTest(IArbolOrdenado<Integer> tree,long n[],long range[],long k[],long[] insertcase) throws ElementoExisteException, ElementoNoExisteException{
	int i,j,r,l;
	for(i=0; i<2 ; i++)
	for(j=0; j<2 ; j++)
	for(r=0; r<3 ; r++){
		System.out.printf("creando test\n");
		Test t = new Test(n[j],k[r],range[i]);

		for(l=0 ; l<4 ; l++){
			System.out.printf("Testeando arbol con: k=%d\tn=%s\trango=%s\tcaso de insercion =%d\n",k[r],elementToString(j),rangeToString(i),insertcase[l]);
			Sequence.createsequence(t,insertcase[l]);
			Sequence.dosequence(tree,t);}
			//freeTest(t);
	}
	return;
}

public static void main(String[] args) throws ElementoExisteException, ElementoNoExisteException {
	long n[],range[],insertcase[],k[];
	IArbolOrdenado<Integer> tree;

	n = new long[2];
	range = new long[2];
	insertcase = new long[4];
	k = new long[3];

	//AVL *avl;ARB *arb;A23 *a23;AB *ab;

	System.out.printf("Iniciando testeo\n");


	range[0] = MyMath.pow(2,16);range[1] = MyMath.pow(2,24);
	System.out.printf("rango creado\n");

	n[0] = MyMath.pow(2,11);n[1] = MyMath.pow(2,19);
	System.out.printf("n creado\n");

	k[0] = 3;k[1] = 5;k[2] =7;
	System.out.printf("k creado\n");

	insertcase[0]=case1;insertcase[1]=case2;insertcase[2]=case3;insertcase[3] = case4;
	System.out.printf("insertcase creado\n");

	System.out.printf("Variables iniciadas\n");

	/*AVL TESTING*/
	System.out.printf("testeando AVL:\n");

	tree = new ArbolAVL<Integer>();

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