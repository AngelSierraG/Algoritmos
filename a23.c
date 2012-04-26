/*estructura*/
#include <iostream.h>
#include <cstdlib>
#include <vector.h>


template<class T>
struct nodoArbol
{

   T info;
   nodoArbol();
   nodoArbol<T> *der;  //apuntadores
   nodoArbol<T> *izq;
   nodoArbol<T> *med;
   nodoArbol<T> *tmp;
   nodoArbol<T> *pad;



   int factor;
   int hm,hd,hi,ht;   //etiquetas
   int hc,c;            //numero  de hijos
   char quehijo;
   bool abuelo,padre;      //let us know if the pointers go to leaves or more nodes


};

template<class T>
nodoArbol<T>::nodoArbol(){  //inicializa variables/apuntadores

        c=0;
        med=NULL;
        der=NULL;
        tmp=NULL;
        izq=NULL;

        hi=hd=hm=hc=0;
        abuelo = false;
        padre=false;
}





template<class T>
class Arbol{

        int l;
        int cc;
        public:
        Arbol();
        ~Arbol();
        nodoArbol<T> *raiz;
        bool buscar(T dato);   //metodo publico para buscar
        bool borrar(T dato);
        bool isertar(T dato);  //metodo publico que manda a llamar a insertar
        bool vacia();            //metodo publico para ver si un arbol esta vacio
        int minimo(bool);

        private:
        void inodo(nodoArbol<T>*);
        nodoArbol<T>* buscar(T dato, nodoArbol<T>*);
        nodoArbol<T>* insertar(nodoArbol<T> *fuente, T dato, char k="i"); //inserta un dato en un nodo
        void borrar(nodoArbol<T> *);
        bool n;
        bool vacia(nodoArbol<T> *fuente);     //si un nodo esta vacio regresa true
        bool balancear(nodoArbol<T> *nodo);   //si un nodo tiene 4 hijos lo divide
        void actetiq(nodoArbol<T> * nodo, bool n);   //sincroniza las etiquetas con los valores de los nodos
        void checar(nodoArbol<T> *,bool);   //funcion que revisa los valores de las etiquetas
        void quehijos(nodoArbol<T> *);
};

template<class T>
Arbol<T>::Arbol()
{
    raiz=new nodoArbol<T>;
    l=0;
     cc=0;
    n=false;


}
template<class T>
Arbol<T>::~Arbol()
{

  borrar(raiz);

}
template<class T>
void Arbol<T>::borrar(nodoArbol<T> *R){

if(R->izq!=NULL) borrar(R->izq);
if(R->med!=NULL) borrar(R->med);
if(R->der!=NULL) borrar(R->der);

delete R;

}
template<class T>
int Arbol<T>::minimo(bool n){
  nodoArbol<T> *temp=new nodoArbol<T>;
  temp=raiz;
  while(1){

   if(!n){
         if(temp->izq!=NULL)
                 temp=temp->izq;
         else
                 return temp->c;
   }

  }
}


template<class T>
void Arbol<T>::quehijos(nodoArbol<T> *nodo){

  if(nodo->izq!=NULL)
  nodo->izq->quehijo='i';
  if(nodo->med!=NULL)
  nodo->med->quehijo='m';
  if(nodo->der!=NULL)
  nodo->der->quehijo='d';

}

//------------------------------------------------------------------------------

template<class T>     //si raiz es nula
bool Arbol<T>::vacia()
{
   if(!n)
   return vacia(raiz);

}
//------------------------------------------------------------------------------
template<class T>
bool Arbol<T>::vacia(nodoArbol<T> *fuente)   //si un nodo es nulo
{
   if ( fuente->hi==0)
       return true;
   else
       return false;
}
//------------------------------------------------------------------------------

template<class T>
bool Arbol<T>::borrar(T dato){

  nodoArbol<T> *bd = new nodoArbol<T>;
  bd = buscar(dato, raiz);

  if(bd ==NULL) return false;
  else{
  if(bd->hi==dato){
     delete bd->izq;
     bd->izq=bd->med;
     bd->med=bd->der;
     delete bd->der;
     bd->der=NULL;
     actetiq(bd,0);
  }
  else if(bd->hm==dato){

     delete bd->med;
     bd->med=bd->der;


     delete bd->der;

     bd->der=NULL;
     actetiq(bd,0);


  }
  else{
     delete bd->der;
     bd->der=NULL;
     actetiq(bd,0);
  }
  if(bd->izq!=NULL && bd->med!=NULL)
     bd->hc=2;

   else if(bd->izq==NULL && bd->med==NULL ){

   bd->hc=0;
   }



   else if(bd->izq!=NULL && bd->med==NULL){
   bd->hc=1;

   if(bd!=raiz)
   inodo(bd);
   }



   checar(raiz,0);
   checar(raiz,1);






  return true;
  }

}

template<class T>
void Arbol<T>::inodo(nodoArbol<T> *nodo){

              nodoArbol<T> *temp = new nodoArbol<T>;
              nodoArbol<T> *temp2 = new nodoArbol<T>;
              nodoArbol<T> *temp3 = new nodoArbol<T>;

              if(nodo!=raiz){

              temp2=nodo->pad;
              }

       if(nodo==raiz){

       raiz=raiz->izq ;
       delete nodo->med;
       delete nodo;

       raiz->pad=NULL;
       raiz->quehijo='r';

       }
       else{
       if(nodo->quehijo=='i'){
         temp=nodo->izq;
         temp2->izq=NULL;
         delete nodo;
         temp2->izq=temp2->med;

         temp2->med=temp2->der;

         temp2->der=NULL;

         temp2->hc = temp2->hc -1;

        // insertar(temp2->izq, temp, 'i');
        temp3=temp2->izq;

        temp3->tmp=temp3->der;

        temp3->der=temp3->med;

        temp3->med=temp3->izq;

        temp3->izq=temp;

        temp3->hc+=1;


        }

          else if(nodo->quehijo=='m'){

       temp=nodo->izq;

       temp2->med=NULL;
       delete nodo;

       temp2->med=temp2->der;
       temp2->hc=temp2->hc-1;
       temp2->der=NULL;

       if(temp2->izq->hc==2 || temp2->med==NULL){  //si es mas facil insertar a la izquierda o el nodo derecho esta vacio

            if(temp2->izq->hc==2){
            temp3=temp2->izq;
            temp3->der=temp;
            temp3->hc+=1;
            }




           else{     //si el nodo de la derecha esta vacio y el nodo de la izquierda ya tiene 3 hijos
           temp3=temp2->izq;
           temp3->tmp=temp;
           temp3->hc+=1;
           }
       }
       else{        //si el nodo de la izquierda ya tiene 3 hijos y el de la derecha no esta vacio
           temp3=temp2->med;
           temp3->tmp=temp3->der;
           temp3->der=temp3->med;
           temp3->med=temp3->izq;
           temp3->izq=temp;
           temp3->hc+=1;


        }
     }

       else  if(nodo->quehijo=='d'){

          temp=nodo->izq;
          temp2->der=NULL;

          delete nodo;
          temp2->hc=temp2->hc-1;


        temp3=temp2->med;

        if(temp3->der==NULL){
        temp3->der=temp;

        }
        else{

        temp3->tmp=temp;
        }

        temp3->hc+=1;

        }

        temp->pad=temp3;


     if (temp2->hc==1)
     inodo(temp2);
     }

}

template<class T>
void Arbol<T>::checar(nodoArbol<T>* nodo,bool k){


    if(nodo->abuelo && nodo!=NULL){
    if(nodo->izq!=NULL)
      checar(nodo->izq,k);
    if(nodo->med!=NULL)


      checar(nodo->med,k);

    if(nodo->der!=NULL)
      checar(nodo->der,k);
     }
      if(!k){
      if(nodo==raiz && !raiz->padre)
      actetiq(raiz,1);
      else if(nodo==raiz && raiz->padre)
      actetiq(raiz,0);




    else if(nodo->padre){

      actetiq(nodo,0);
      }
    else {
      actetiq(nodo,1);
    }
    quehijos(nodo);
    }
    else
    {
    balancear(nodo);
    quehijos(nodo);
    }



}



//------------------------------------------------------------------------------

template<class T>
void Arbol<T>::actetiq(nodoArbol<T> *nodo,bool n) {  //actualiza etiquetas.

   if (n==0){                                        //Si es un nodo padre(n=0) las actualiza conforme a los valores de sus hijos

  if(nodo->izq!=NULL){

  nodo->hi=nodo->izq->c;
  }

  else nodo->hi=0;

    if(nodo->med!=NULL){

  nodo->hm=nodo->med->c;
                        }
  else nodo->hm=0;

    if(nodo->der!=NULL){

  nodo->hd=nodo->der->c;
  }

  else {nodo->hd=0;}

    if(nodo->tmp!=NULL)
  nodo->ht=nodo->tmp->c;
  }

  if(n>=1){   //Si es un nodo abuelo las actualiza conforma a las etiquetas de sus hijos

  nodo->hi= max(nodo->izq->hi,nodo->izq->hm);
  nodo->hi= max(nodo->hi,nodo->izq->hd);
  nodo->hm= max(nodo->med->hi,nodo->med->hm);
  nodo->hm= max(nodo->hm,nodo->med->hd);
  if(nodo->der!=NULL){
    nodo->hd= max(nodo->der->hi,nodo->der->hm);
    nodo->hd= max(nodo->hd,nodo->der->hd);
  }
  else nodo->hd=0;
  }

}


template<class T>
nodoArbol<T>* Arbol<T>::insertar(nodoArbol<T> *fuente, T dato, char k)  //inserta a un nodo determinado un nodo terminal
{ //metodo insertar

   nodoArbol<T> *hoja=new nodoArbol<T>;

   hoja->c = dato;


   if(vacia(fuente)){

    fuente->padre =true;
    }







   if(vacia()){   //si el arbol esta vacio la raiz apuntara al primer nodo
   raiz = fuente;
   fuente->quehijo='r';
   }

   if(vacia(fuente)){       //si el nodo esta vacio el primer valor se insertara a la izquierda

    fuente-> izq = hoja;
    fuente->hc = fuente->hc+1;
    hoja->quehijo='i';
   }

   else {

   if (fuente->hc<4){    //solo si tiene 1 2 o 3 hijos


     if(hoja->c == fuente->hi || hoja->c==fuente->hm || hoja->c==fuente->hd){  //condiciones para insertar a la izquierda

     return NULL;      }

     if (hoja->c<fuente->hi){  //si entra hasta la izquierda el nuevo nodo hace un recorrimiento general

       if(fuente->izq!=NULL && !fuente->padre){   //si es un nodo abuelo verifica a que nodo hijo se le inserta el nuevo dato

        insertar(fuente->izq,dato,'i');

       }
       else{


         fuente->tmp=fuente->der;

         fuente->der=fuente->med;



         fuente->med = fuente->izq;

         hoja->quehijo='i';
         fuente->izq=hoja;

         fuente->hc = fuente->hc+1;
       }




   }

   else if(hoja->c > fuente->hi && hoja->c <fuente->hm ||  hoja->c > fuente->hi && fuente->hm==0 || !fuente->padre && hoja->c > fuente->hi && fuente->hd==0){

        if(fuente->med!=NULL && !fuente->padre){

         insertar(fuente->med,dato,'m');



            }
         else{

         fuente->tmp=fuente->der;
         fuente->der=fuente->med;
         fuente->med=hoja;
         fuente->hc = fuente->hc+1;
         hoja->quehijo='m';
         }

  }

  else if(hoja->c > fuente->hm && fuente->hd ==0 || hoja->c >fuente->hm && hoja->c < fuente->hd || fuente->abuelo==true && hoja->c>fuente->hm){
               //para insertar a la derecha
         if(!fuente->padre){

          insertar(fuente->der,dato,'d');


        }

         else{



         fuente->tmp=fuente->der;
         fuente->der=hoja;
         fuente->hc = fuente->hc+1;
         hoja->quehijo='d';
         }

   }
   else if(hoja->c>fuente->hd){   //si se inserta en temporal

        fuente->tmp=hoja;
        fuente->hc = fuente->hc+1;


        }

  else return NULL;
       }
   else return NULL;
   }


      if(fuente->padre) //verifica de que forma se van a actualizar las etiquetas
      {

      actetiq(fuente,0);
      }

      else
      actetiq(fuente,1);

      hoja->pad = fuente;     //un apuntador del hijo apunta al padre

      if(fuente->hc==4)      //si tiene 4 hijos de balancea
       {


       //cout<<fuente->pad->hi<<fuente->pad->hm<<"\n";
       //cout<<fuente->hi<<fuente->hm<<fuente->hd<<fuente->ht<<"\n";
      balancear(fuente);

      actetiq(fuente,1);     //se actualizan etiquetas de nuevo

       }

      return fuente;



}
//------------------------------------------------------------------------------
template<class T>
nodoArbol<T>* Arbol<T>::buscar(T dato, nodoArbol<T> *inicio){

           nodoArbol<T> *nodo = new nodoArbol<T>;


           nodo=inicio;
           if(nodo->abuelo){

             if(nodo->hi>=dato)
                 buscar(dato,nodo->izq);

             else if(nodo->hi<dato && nodo->hm>=dato ||nodo->hi<dato && nodo->der==NULL)
                 buscar(dato,nodo->med);
             else if(nodo->hd>=dato && nodo->der!=NULL)
                 buscar(dato,nodo->der);
           }

           else if(nodo->padre){

           if(nodo->hi==dato ||nodo->hm==dato || nodo->hd==dato){

           return nodo;
           }
           else
           return NULL;
           }




}
//------------------------------------------------------------------------------
template<class T>
bool Arbol<T>::buscar(T dato){
           nodoArbol<T> *resultado = new nodoArbol<T>;
           resultado=buscar(dato,raiz);
           if (resultado!=NULL)
               return true;
           return false;


}


//------------------------------------------------------------------------------

template<class T>
bool Arbol<T>::isertar(T dato){       //metodo publico para llamar a insertar


        if(insertar(raiz, dato,'i')==NULL)
        return 0;
        else {return 1;}

}

//------------------------------------------------------------------------------

template<class T>
bool Arbol<T>::balancear(nodoArbol<T> *nodo){  //metodo para balancear

       if (nodo->hc !=4) return false;
       nodoArbol<T> *temp= new nodoArbol<T>;
       nodoArbol<T> *temp2= new nodoArbol<T>;
       nodoArbol<T> *temp3= new nodoArbol<T>;


       temp->izq = nodo->izq;        //parte el nodo e inserta los datos en otros nodos
       temp->med = nodo->med;


       temp2->izq = nodo->der;
       nodo->der->quehijo='i';
       temp2->med = nodo->tmp;
       nodo->tmp->quehijo='m';

       temp3=temp->izq;   //para que apunten los hijos a su nuevo padre
       temp3->pad=temp;

       temp3=temp->med;
       temp3->pad=temp;

       temp3=temp2->izq;
       temp3->pad=temp2;

       temp3=temp2->med;
       temp3->pad=temp2;

       actetiq(temp,0);
       actetiq(temp2,0);



       if(nodo->izq->abuelo || nodo->izq->padre){

       temp->padre=false;
       temp2->padre=false;
       temp->abuelo=true;
       temp2->abuelo=true;

       actetiq(temp,1);
       actetiq(temp2,1);

       }

       else{

       temp->padre=true;
       temp2->padre=true;
       actetiq(temp,0);
       actetiq(temp2,0);

       }

       temp->hc=2;
       temp2->hc=2;


       if(nodo==raiz){    //si el nodo es raiz el particionamiento es facil
       nodo->quehijo='r';
       nodo->izq=temp;
       temp->quehijo='i';
       nodo->med=temp2;
       temp2->quehijo='m';
       temp->pad=nodo;
       temp2->pad=nodo;

       nodo->hc=2;

       nodo->abuelo=true;
       nodo->padre=false;

       nodo->der=NULL;
       nodo->hd=0;



       return true;
       }
       else{

       temp->izq->pad=temp; //redundante pero asegura
       temp->med->pad=temp;


       temp2->izq->pad=temp2;
       temp2->med->pad=temp2;

       temp->pad=nodo->pad;
       temp2->pad=nodo->pad;

       if (nodo->quehijo=='i'){   //si no se checa que hijo es (izq,med,der) para saber donde se insertan los nodos

          nodo->pad->tmp=nodo->pad->der;
          nodo->pad->der=nodo->pad->med;
          nodo->pad->med=temp2;
          nodo->pad->izq=temp;
          nodo->pad->hc++;
          nodo->pad->der->quehijo='d';
          nodo->pad->med->quehijo='m';
          nodo->pad->izq->quehijo='i';

          }
      else if(nodo->quehijo=='m'){


          nodo->pad->tmp=nodo->pad->der;
          nodo->pad->der=temp2;
          nodo->pad->med=temp;
          nodo->pad->hc++;
          nodo->pad->der->quehijo='d';
          nodo->pad->med->quehijo='m';


       }
       else if(nodo->quehijo=='d'){



       nodo->pad->tmp=temp2;
       nodo->pad->der=temp;
       nodo->pad->hc++;
       nodo->pad->der->quehijo='d';


       }

        delete nodo;
       }




       temp->der=NULL;
       temp2->der=NULL;
       return true;

}







