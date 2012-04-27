        

//flata convertir de c++ a c



        class Arbol

        {

                pagina **Indice;

                pagina **VSAM;

                pagina *comodin_raiz;

                pagina *comodin_VSAM;

         

                // Funciones que manejan las páginas

                void crear_pagina(pagina **,int);

                void inicializar(pagina *);

                void insertar(pagina *,int,int *);

         

                // Funciones que manejan la pila

                void init_pila(LIFO *);

                void ins_pila(LIFO *,pagina *);

                int pila_vacia(LIFO *);

                void retira_pila(LIFO *,pagina **);

         

                // Funciones que manejan el árbol internamente

                void ins_bmas(pagina **,pagina **,int,int *);

                void buscar(pagina *,int,int *,LIFO *);

                void romper(pagina *,pagina *,pagina **,int,int *,int);

                int donde(pagina *,int);

                void cderecha_apunt(pagina *,int);

                void listar_VSAM(pagina *);

        public:

                Arbol();

                ~Arbol();

         

                // Funciones que manejan el árbol externamente

                void Ingresar_Numero(int);

                void Mostrar_Arbol();

        };

