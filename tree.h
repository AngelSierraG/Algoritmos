#include "avl.c"
#include "arb.c"
#include "a23.h"
#include "ab.h"


char *treeToString(int tree){
	if(cAVL == tree)
		return "AVL";
	else if(cARB == tree)
		return "ARB";
	else if(cA23 == tree)
		return "A23";
	else if(cAB == tree)
		return "AB";
}

/* Dispatch de funciones dependiendo del tipo de arbol*/
void insertar(void *tree,int type,int element){
	if(type == cAVL){
		insertarAVL((AVL *)tree,element);
	}
	else if(type == cARB){
		insertarARB((ARB *)tree,element);
	}
	else if(type == cA23){
		insertarA23((A23 *)tree,element);
	}
	else if(type == cAB){
		insertarAB((AB *)tree,element);
	}
}

void borrar(void *tree,int type,int element){
	if(type == cAVL){
		borrarAVL((AVL *)tree,element);
	}
	else if(type == cARB){
		borrarARB((ARB *)tree,element);
	}
	else if(type == cA23){
		borrarA23((A23 *)tree,element);
	}
	else if(type == cAB){
		borrarAB((AB *)tree,element);
	}
}

int buscar(void *tree,int type,int element){
	if(type == cAVL){
		return buscarAVL((AVL *)tree,element);
	}
	else if(type == cARB){
		return buscarARB((ARB *)tree,element);
	}
	else if(type == cA23){
		return buscarA23((A23 *)tree,element);
	}
	else if(type == cAB){
		return buscarAB((AB *)tree,element);
	}
}

void freeTree(void *tree,int type){
	if(type == cAVL){
		freeAVL((AVL *)tree);
	}
	else if(type == cARB){
		freeARB((ARB *)tree);
	}
	else if(type == cA23){
		freeA23((A23 *)tree);
	}
	else if(type == cAB){
		freeAB((AB *)tree);
	}
	return;
}