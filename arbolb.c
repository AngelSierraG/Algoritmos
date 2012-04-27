       
       //falta convertir de c++ a c



        Arbol::Arbol()

        {

                comodin_raiz=NULL;

                comodin_VSAM=NULL;

                Indice=&comodin_raiz;

                VSAM=&comodin_VSAM;

        }

         

        Arbol::~Arbol()

        {

        }

         

        // Funciones que manejan las páginas

        void Arbol::crear_pagina(pagina **p,int x)

        {

                *p=new pagina;

                inicializar(*p);

                (*p)->cont=1;

                (*p)->info[0]=x;

        }

         

        void Arbol::inicializar(pagina *p)

        {

                int i=0;

                p->cont=0;

                while(i < M+1)

                        p->apunt[i++]=NULL;

        }

         

        void Arbol::insertar(pagina *p,int x,int *i)

        {

                int j;

                if(p->cont)

                        if(x > p->info[*i])

                                (*i)++;

                        else

                        {

                                j=p->cont-1;

                                while(j >= *i)

                                {

                                        p->info[j+1]=p->info[j];

                                        j--;

                                }

                        }

         

                p->cont++;

                p->info[*i]=x;

        }

         

        // Funciones que manejan la pila

        void Arbol::init_pila(LIFO *p)

        {

                p->t=0;

        }

         

        void Arbol::ins_pila(LIFO *p,pagina *s)

        {

                if(p->t == MAXIMO)

                        cout<<"LA PILA NO SOPORTA MAS ELEMENTOS"<<endl;

                else

                {

                        p->t++;

                        p->a[p->t-1]=s;

                }

        }

         

        int Arbol::pila_vacia(LIFO *p)

        {

                return (!p->t);

        }

         

        void Arbol::retira_pila(LIFO *p,pagina **s)

        {

                if(pila_vacia(p))

                {

                        cout<<"LA PILA ESTA VACIA"<<endl;

                        *s=NULL;

                }

                else

                {

                        *s=p->a[p->t-1];

                        p->t--;

                }

        }

         

        // Funciones que manejan el árbol externamente

        void Arbol::Ingresar_Numero(int num)

        {

                int respuesta;

                ins_bmas(Indice,VSAM,num,&respuesta);

        }

         

        void Arbol::Mostrar_Arbol()

        {

                listar_VSAM(*VSAM);

        }

         

        // Funciones que manejan el árbol internamente

        void Arbol::ins_bmas(pagina **raiz,pagina **VSAM,int x,int *s)

        {

                int posicion,i,subir,subir1,terminar,separar;

                pagina *p,*nuevo,*nuevo1;

                LIFO pila;

         

                init_pila(&pila);

                *s=0;

         

                if(*raiz == NULL)

                {

                        crear_pagina(raiz,x);

                        *VSAM=*raiz;

                }

                else

                {

                        buscar(*raiz,x,&posicion,&pila);

                        if(posicion == -1)

                                *s=1; // La llave está en el árbol

                        else

                        {

                                terminar=separar=0;

                                while(!pila_vacia(&pila) && terminar==0)

                                {

                                        retira_pila(&pila,&p);

                                        if(p->cont == M)

                                        {

                                                if(separar == 0)

                                                {

                                                        romper(p,NULL,&nuevo,x,&subir,separar);

                                                        separar=1;

                                                }

                                                else

                                                {

                                                        romper(p,nuevo,&nuevo1,subir,&subir1,separar);

                                                        subir=subir1;

                                                        nuevo=nuevo1;

                                                }

                                        }

                                        else

                                        {

                                                if(separar == 1)

                                                {

                                                        separar=0;

                                                        i=donde(p,subir);

                                                        insertar(p,subir,&i);

                                                        cderecha_apunt(p,i+1);

                                                        p->apunt[i+1]=nuevo;

                                                }

                                                else

                                                        insertar(p,x,&posicion);

                                                terminar=1;

                                        }

                                }

                                if(separar == 1 && terminar == 0)

                                {

                                        crear_pagina(raiz,subir);

                                        (*raiz)->apunt[0]=p;

                                        (*raiz)->apunt[1]=nuevo;

                                }                      

                        }

                }

        }

         

        void Arbol::buscar(pagina *p,int x,int *posicion,LIFO *pila)

        {

                int i,encontro=0;

         

                *posicion=-1;

                while(p && !encontro)

                {

                        ins_pila(pila,p);

                        i=0;

                        while(x > p->info[i] && i < p->cont-1)

                                i++;

                        if(x < p->info[i])

                                p=p->apunt[i];

                        else

                        {

                                if(x > p->info[i])

                                {

                                        if(p->apunt[0] != NULL)

                                                p=p->apunt[i+1];

                                        else

                                                p=NULL;

                                }

                                else

                                {

                                        if(p->apunt[0] != NULL)

                                                p=p->apunt[i+1];

                                        else

                                                encontro=1;

                                }

                        }

                }

         

                if(!encontro)

                        *posicion=i;

        }

         

        void Arbol::romper(pagina *p,pagina *t,pagina **q,int x,int *subir,int separar)

        {

                int a[M+1],i=0,s=0;

                pagina *b[M+2],*temp;

         

                if(separar == 0)

                {

                        temp=p->apunt[M];

                        p->apunt[M]=NULL;

                }

         

                while(i < M && !s)

                        if(p->info[i] < x)

                        {

                                a[i]=p->info[i];

                                b[i]=p->apunt[i];

                                p->apunt[i++]=NULL;

                        }

                        else

                                s=1;

         

                a[i]=x;

                b[i]=p->apunt[i];

                p->apunt[i]=NULL;

                b[++i]=t;

         

                while(i <= M)

                {

                        a[i]=p->info[i-1];

                        b[i+1]=p->apunt[i];

                        p->apunt[i++]=NULL;

                }

         

                *q=new pagina;

                inicializar(*q);

                i=0;

                if(separar == 0)

                {

                        p->cont=N;

                        (*q)->cont=N+1;

                        (*q)->info[0]=a[N];

                        while(i < N)

                        {

                                p->info[i]=a[i];

                                p->apunt[i]=b[i];

                                (*q)->info[i+1]=a[i+N+1];

                                (*q)->apunt[i]=b[i+N+1];

                                i++;

                        }

                        (*q)->apunt[M]=temp;

                        p->apunt[M]=*q;

                }

                else

                {

                        p->cont=(*q)->cont=N;

                        while(i < N)

                        {

                                p->info[i]=a[i];

                                p->apunt[i]=b[i];

                                (*q)->info[i]=a[i+N+1];

                                (*q)->apunt[i]=b[i+N+1];

                                i++;

                        }

                }

         

                p->apunt[N]=b[N];

                (*q)->apunt[N]=b[M+1];

                *subir=a[N];

        }

         

        int Arbol::donde(pagina *p,int x)

        {

                int i=0;

                while(x > p->info[i] && i < p->cont-1)

                        i++;

                return i;

        }

         

        void Arbol::cderecha_apunt(pagina *p,int i)

        {

                int j;

                j=p->cont;

                while(j > i)

                {

                        p->apunt[j]=p->apunt[j-1];

                        j--;

                }

        }

         

        void Arbol::listar_VSAM(pagina *VSAM)

        {

                int i;

                while(VSAM != NULL)

                {

                        i=0;

                        while(i < VSAM->cont)

                                cout<<VSAM->info[i++]<<" - ";

                        cout<<endl;

                        VSAM=VSAM->apunt[M];

                }

        }

