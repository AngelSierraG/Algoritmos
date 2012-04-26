#include "arb.h" //este es el rojo negro	

bool isEmpty();





void solucionarRojoRojo(ARB *inicio,ARB *node, int h){
  int ladohijo;
  ARB *abuelo; // en node traemos al padre, en h 1 si el hijo rojo es el izquierdo 2 si no
  ARB *tio;
  ARB *ayudante;
  abuelo=node->padre;
  if(abuelo->izq && abuelo->der){
    if(node==abuelo->izq) tio=abuelo->der;
    else tio=abuelo->izq;

    if(tio->color=='r'){ // caso uno y dos
      tio->color='n'; node->color='n';
      if(abuelo!=inicio) abuelo->color='r';
      if(abuelo->padre){
        ayudante=abuelo->padre;
        if(ayudante->izq==abuelo) ladohijo=1;
        else ladohijo=2;
        if(ayudante->color=='r') solucionarRojoRojo(inicio, ayudante, ladohijo);
      }
      return;
    }
  }
  if(h==1 && abuelo->izq==node){ // caso tres
    node->color='n', abuelo->color='r';
    ayudante=node->der, node->der=abuelo, node->padre=abuelo->padre;
    abuelo->padre=node, abuelo->izq=ayudante;
    if(ayudante) ayudante->padre=abuelo;
    if(node->padre){
      ayudante=node->padre;
      if(ayudante->izq==node->der) ayudante->izq=node;
      else ayudante->der=node;
    }
    else inicio=node;
  }
  else if(h==2 && abuelo->der==node){ // caso cuatro
    node->color='n', abuelo->color='r';
    ayudante=node->izq, node->izq=abuelo, node->padre=abuelo->padre;
    abuelo->padre=node, abuelo->der=ayudante;
    if(ayudante) ayudante->padre=abuelo;
    if(node->padre){
      ayudante=node->padre;
      if(ayudante->izq==node->izq) ayudante->izq=node;
      else ayudante->der=node;
    }
    else inicio=node;
  }
  else if(h==2 && abuelo->izq==node){ // caso cinco
    tio=node->der, ayudante=tio->izq, abuelo->izq=tio;
    tio->padre=abuelo, tio->izq=node, node->padre=tio;
    node->der=ayudante;
    if(ayudante) ayudante->padre=node;
    solucionarRojoRojo(inicio,tio, 1);
  }
  else if(h==1 && abuelo->der==node){ // caso seis
    tio=node->izq, ayudante=tio->der, abuelo->der=tio;
    tio->padre=abuelo, tio->der=node, node->padre=tio;
    node->izq=ayudante;
    if(ayudante) ayudante->padre=node;
    solucionarRojoRojo(inicio,tio, 2);
  }
}


void insertarARB(ARB *arb,int element){


//void insercion(int key, char cad[32]){
  int ladohijo;
  ARB *hijo;
  ARB *ayudante;
  int bandera;
  if(arb == NULL){ // el arbol esta vacio cargando como raiz
    arb= newARB();
    arb->value=element; 
    arb->color='n';
    //strcpy(inicio->cadena,cad); inicio->padre=NULL;
    //inicio->izquierdo=NULL; inicio->derecho=NULL; inicio->color='n';
  }
  else{ // el arbol no esta vacio buscando su lugar
    hijo = newARB();
    hijo->value=element; 
    //strcpy(hijo->cadena,cad);
    hijo->padre=NULL; 
    hijo->izq=NULL; 
    hijo->der=NULL;
    hijo->color='r';
    ayudante=arb;
    do{
      hijo->padre=ayudante,
      bandera=1;
      if(element<ayudante->value){
        if(ayudante->izq)
        	 ayudante=ayudante->izq;
        else 
        	ayudante->izq=hijo, bandera=0, ladohijo=1;
      }
      else{
        if(ayudante->der) ayudante=ayudante->der;
        else ayudante->der=hijo, bandera=0, ladohijo=2;
      }
    }while(bandera==1);
    if(ayudante->color=='r') solucionarRojoRojo(arb,ayudante, ladohijo);
  }
}









int buscarARB(ARB *arb,int element){


ARB *ayudante;
ayudante=arb;
if(!ayudante) return NULL;
  do{
    if(element==ayudante->value) return ayudante->value;
    else if(element<ayudante->value) ayudante=ayudante->izq;
    else if(element>ayudante->value) ayudante=ayudante->der;
  }while(ayudante);
  return NULL


}

void borrarARB(ARB *arb,int element);