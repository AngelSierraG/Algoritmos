//Definicion de la clase lista
class ListaSimpleEst{
	String impresor="";
	NodosListaEst PrimerNodo;
	NodosListaEst UltimoNodo;
	//Retorna True si lista Vacia
	public boolean VaciaLista(){
		return PrimerNodo ==null;
	}
	//Inserta al final de la lista (solo prestamos)
	public void InsertaFinal(int ide,int cod,String nom,NodosListaEstCursos a){
		if(VaciaLista()){
			PrimerNodo= UltimoNodo = new NodosListaEst (ide,cod,nom,a);
			System.out.println("Se inserto estudiante "+ide+nom);
	    }
		else{
			UltimoNodo=UltimoNodo.siguiente=new NodosListaEst (ide,cod,nom,a);
			System.out.println("Se inserto estudiante "+ide+" "+nom);	
	    }
	}

	//elimina el ultimo elemento
	public void EliminaFinal (){
	    if(!VaciaLista()){
			if(PrimerNodo.equals(UltimoNodo))
		    	PrimerNodo = UltimoNodo = null;
		    else{ 
		    	NodosListaEst Actual=PrimerNodo;
		      	while(Actual.siguiente != UltimoNodo)
		        	Actual = Actual.siguiente;
		      	UltimoNodo =Actual;
		      	Actual.siguiente = null;
		    }
	 	}
	}
	//Miembro
	public boolean Miembro (int ele){
		boolean enc=false;
		NodosListaEst aux=PrimerNodo;
		while((aux != null) && (enc==false)){
			if(ele==aux.ident){
				enc=true;
			}
			else{
				aux=aux.siguiente;
			}
		}
		return enc;
	}
	//Nombre
	public String CualNombre (int ele){
		String x="";
		boolean enc=false;
		NodosListaEst aux=PrimerNodo;
		while((aux != null) && (enc==false)){
			if(ele==aux.ident){
				enc=true;
				x=aux.Nombre;
			}
			else{
				aux=aux.siguiente;
			}
		}
		return x;
	}
	//imprime
	public void Imprimir(){ 
		if (VaciaLista()){
			impresor="error";
		}
	  	else{
        	NodosListaEst Actual = PrimerNodo;
	     	while (Actual != null){
	      		impresor=impresor+"Id: "+Actual.ident+"\nNombre: "+Actual.Nombre+"\nCod Carrera: "+Actual.Codcar+"\nCursos: ";
	      		NodosListaEstCursos aa=Actual.cursos;
	      		while(aa!=null){
	      			impresor=impresor+aa.curso+" ";
	      			aa=aa.siguiente;
	      		}
	      		impresor=impresor+"\n--------------------------\n";
	      		Actual=Actual.siguiente;
	     	}
	   	}
	}	
	// Largo
	public void Largo(){
		NodosListaEst aux=PrimerNodo;
		int Lar =0;
		while (aux!= null){
	  		Lar++;
	  		aux =aux.siguiente;
    	}
    	System.out.println ( "El largo de la Lista es: "+ Lar);
	}
}
//Nodo de lista simple
class NodosListaEst{
	int ident;
	int Codcar;
	String Nombre;
	NodosListaEstCursos cursos;
	NodosListaEst siguiente;
	NodosListaEst (int ide,int cod,String nom,NodosListaEstCursos a){
		ident=ide;
		Codcar=cod;
		Nombre=nom;
		cursos=a;
		siguiente=null;
	}
	NodosListaEst(){}
}
//lista de cursos
class NodosListaEstCursos{
	int curso;
	NodosListaEstCursos siguiente;
	NodosListaEstCursos (int cur){
		curso=cur;
		siguiente=null;
	}
	NodosListaEstCursos(){}
}
