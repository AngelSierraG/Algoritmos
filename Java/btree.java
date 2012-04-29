import javax.swing.*;
import java.io.*;
//Arboles Btree
class Btree{
	Bnodo p=new Bnodo();
	Bnodo Xder = new Bnodo();
	Bnodo Xizq = new Bnodo();
	NodoPr X;
	Bnodo Xr;
	String salida="",imps="";
	boolean EmpA = false,Esta=false;
	//***************************************
	//*****Inserta un nodo en un arbol b*****
	//***************************************
	void Inserta(NodoPr clave){
		Insertaa(clave,p);
	}
	//auxiliar de inserta nodo
	public void Insertaa(NodoPr clave, Bnodo raiz){
		Empujar(clave,raiz);
		if(EmpA){
			p=new Bnodo();
			p.Cuentas= 1;
			p.Claves[0]=X;
			p.Ramas[0]=raiz;
			p.Ramas[1]=Xr;
		}
		JOptionPane.showMessageDialog(null,"Insercion Completa");
	}
	//Empuja los elementos del arbol
	public void Empujar(NodoPr clave, Bnodo raiz){
		int k=0;
		Esta=false;
		if(Vacio(raiz)){
			EmpA=true;
			X=clave;
			Xr=null;
		}
		else{
			k=BuscarNodo(clave, raiz);
			if(Esta){
				JOptionPane.showMessageDialog(null,"No hay claves repetidas");
				EmpA=false;
			}
			else{
				Empujar(clave, raiz.Ramas[k]);
				if(EmpA){
					if(raiz.Cuentas < 4){
						EmpA = false;
						MeterHoja(X, raiz, k);
					}
					else{
						EmpA = true;
						DividirN(X, raiz, k);
					}
				}
		    }
		}	
	}
	//Meter hoja
	public void MeterHoja(NodoPr clave, Bnodo raiz, int k){	
		int i = raiz.Cuentas;
		while (i != k){
			raiz.Claves[i] = raiz.Claves[i-1];
			raiz.Ramas[i+1] = raiz.Ramas[i];
			--i;
		}
		raiz.Claves[k] = clave;
		raiz.Ramas[k+1] = Xr;
		raiz.Cuentas = ++raiz.Cuentas;
	}
	//Dividir nodo
	public void DividirN(NodoPr Clave, Bnodo Raiz, int k){ 
		int pos=0;
		int Posmda=0;	
		if (k <= 2)
			Posmda = 2;
		else{
			Posmda = 3;
		}
		Bnodo Mder = new Bnodo();
		pos = Posmda+1;
		while (pos != 5){
			 Mder.Claves [(pos - Posmda)-1] = Raiz.Claves[pos-1];
			 Mder.Ramas [pos - Posmda] = Raiz.Ramas[pos];
			 ++pos;
		}
		Mder.Cuentas = 4 - Posmda;
		Raiz.Cuentas = Posmda;
		if (k <= 2)
			MeterHoja(Clave, Raiz, k);
		else{
			MeterHoja(Clave, Mder, (k - Posmda));
		}
		X = Raiz.Claves[Raiz.Cuentas-1];
		Mder.Ramas[0] = Raiz.Ramas[Raiz.Cuentas];
		Raiz.Cuentas = --Raiz.Cuentas;
		Xr=Mder;
	}
	//****************************************
	//*****Borrar un elemento del arbol b*****
	//****************************************
	void Eliminar(NodoPr clave){
		if(Vacio(p)){
			JOptionPane.showMessageDialog(null,"No hay elementos");
		}
		else
			Eliminara(p,clave);
	}
	public void Eliminara(Bnodo Raiz, NodoPr clave){
		try{
			EliminarRegistro(Raiz, clave);
		}
		catch(Exception e){
			Esta=false;
		}
		if (!Esta)
			JOptionPane.showMessageDialog(null,"No se encontro el elemento");
		else{
			if (Raiz.Cuentas == 0)
				Raiz = Raiz.Ramas[0];
			p=Raiz;
			JOptionPane.showMessageDialog(null,"Eliminacion completa");
		}
	}
	//Elimina el registro
	public void EliminarRegistro(Bnodo raiz, NodoPr c){
		int pos = 0;
		NodoPr sucesor;
		if (Vacio(raiz))
			Esta = false;
		else{
			pos = BuscarNodo(c, raiz);
			if (Esta){
				if (Vacio(raiz.Ramas[pos-1]))
					Quitar(raiz, pos);
				else{
					Sucesor(raiz, pos);
					EliminarRegistro (raiz.Ramas[pos], raiz.Claves[pos-1]);
				}
			}
			else{
				EliminarRegistro(raiz.Ramas[pos], c);
				if ((raiz.Ramas[pos] != null) && (raiz.Ramas[pos].Cuentas < 2))
					Restablecer(raiz, pos);
			}
		}
	}
	//Busca el sucesor
	public void Sucesor (Bnodo raiz, int k){
		Bnodo q=raiz.Ramas[k];
		while(!Vacio(q.Ramas[0]))
			q=q.Ramas[0];
		raiz.Claves[k-1]=q.Claves[0];
	}
	//Combina para formar un nodo
	public void Combina(Bnodo raiz, int pos){
		int j;
		Xder = raiz.Ramas[pos];
		Xizq = raiz.Ramas[pos-1];
		Xizq.Cuentas++;
		Xizq.Claves[Xizq.Cuentas-1] = raiz.Claves[pos-1];
		Xizq.Ramas[Xizq.Cuentas] = Xder.Ramas[0];
		j = 1;
		while (j != Xder.Cuentas+1){
			Xizq.Cuentas++;
			Xizq.Claves[Xizq.Cuentas-1] = Xder.Claves[j-1];
			Xizq.Ramas[Xizq.Cuentas] = Xder.Ramas[j];
			j++;
		}
		Quitar(raiz, pos);
	}
	//Mueve a la derecha
	public void MoverDer(Bnodo raiz, int pos){
		int i = raiz.Ramas[pos].Cuentas;
		while (i != 0){
			raiz.Ramas[pos].Claves[i] = raiz.Ramas[pos].Claves[i-1];
			raiz.Ramas[pos].Ramas[i+1] = raiz.Ramas[pos].Ramas[i];
			--i;
		}
		raiz.Ramas[pos].Cuentas++;
		raiz.Ramas[pos].Ramas[1] = raiz.Ramas[pos].Ramas[0];
		raiz.Ramas[pos].Claves[0] = raiz.Claves[pos-1];
		raiz.Claves[pos-1] = raiz.Ramas[pos-1].Claves[raiz.Ramas[pos-1].Cuentas-1];
		raiz.Ramas[pos].Ramas[0] = raiz.Ramas[pos-1].Ramas[raiz.Ramas[pos-1].Cuentas];
		raiz.Ramas[pos-1].Cuentas--;
	}
	//Mover a la izquierda
	public void MoverIzq(Bnodo raiz, int pos){
		int i;
		raiz.Ramas[pos-1].Cuentas++;
		raiz.Ramas[pos-1].Claves[raiz.Ramas[pos-1].Cuentas-1] = raiz.Claves[pos-1];
		raiz.Ramas[pos-1].Ramas[raiz.Ramas[pos-1].Cuentas] = raiz.Ramas[pos].Ramas[0];
		raiz.Claves[pos-1] = raiz.Ramas[pos].Claves[0];
		raiz.Ramas[pos].Ramas[0] = raiz.Ramas[pos].Ramas[1];
		raiz.Ramas[pos].Cuentas--;	
		i = 1;
		while (i != raiz.Ramas[pos].Cuentas+1){
			raiz.Ramas[pos].Claves[i-1] = raiz.Ramas[pos].Claves[i];
			raiz.Ramas[pos].Ramas[i] = raiz.Ramas[pos].Ramas[i+1];
			i++;
		}
	}
	//Quita el elemento
	public void Quitar (Bnodo raiz, int pos){
		int j = pos + 1;
		while (j != raiz.Cuentas+1){
			raiz.Claves [j-2] = raiz.Claves[j-1];
			raiz.Ramas [j-1] = raiz.Ramas[j];
			j++;
		}
		raiz.Cuentas--;
	}
	//Restablece el nodo
	public void Restablecer(Bnodo raiz, int pos){
		if (pos>0){
			if (raiz.Ramas[pos-1].Cuentas > 2)
				MoverDer(raiz, pos);
			else
				Combina(raiz, pos);
		}
		else{
			if (raiz.Ramas[1].Cuentas > 2)
				MoverIzq(raiz, 1);
			else
				Combina(raiz, 1);
		}
	}
	//***************************
	//*****Metodos Generales*****
	//***************************
	//muestra los temas por estudiante
	public void temasestudiante(ListaSimpleEst idents,Bnodo Raiz){
		NodosListaEst m=idents.PrimerNodo;
		imps="";
		while(m!=null){	
			imps=imps+"IDENTIFICACION: "+m.ident;
			temasestudianteaux(m.ident,Raiz);
			imps=imps+"\n---------------------------------------\n";
			m=m.siguiente;
		}
	}
	public void temasestudianteaux(int id,Bnodo Raiz){
		int i;
		if(!Vacio(Raiz)){
			temasestudianteaux(id,Raiz.Ramas[0]);
			i=1;
			while (i != Raiz.Cuentas+1){
				if(id==Raiz.Claves[i-1].ident){
					imps=imps+"\n***** Tema: "+Raiz.Claves[i-1].codtem+" *****"+"\n         -Num Prestamo: "+Raiz.Claves[i-1].nump+"\n         -Libro: "+Raiz.Claves[i-1].codlib+"\n         -Cod Autor: "+Raiz.Claves[i-1].codaut+"\n         -Fecha/Hora: "+Raiz.Claves[i-1].fecha;
				}
				temasestudianteaux(id,Raiz.Ramas[i]);
				++i;
			}
		}	
	}
	//Retorna true si el arbol esta vacio
	public boolean Vacio(Bnodo raiz){
		return (raiz==null || raiz.Cuentas==0);
	}
	//Buscar retorna true si lo encuentra y la posicion
	public int BuscarNodo(NodoPr clave, Bnodo raiz){
		int j=0;
		if(clave.nump < raiz.Claves[0].nump){
			Esta = false;
			j=0;
		}
		else{
			j = raiz.Cuentas;
			while (clave.nump < raiz.Claves[j-1].nump && j>1)
				--j;
			Esta = (clave.nump == raiz.Claves[j-1].nump);
		}
		return j;
	}
	//miembro
	public boolean Miembro(NodoPr clave, Bnodo raiz){
		boolean si=false;
		int j=0;
		if(!Vacio(p)){
			if(clave.nump < raiz.Claves[0].nump){
				si = false;
				j=0;
			}
			else{
				j = raiz.Cuentas;
				while (clave.nump < raiz.Claves[j-1].nump && j>1)
					--j;
				si = (clave.nump == raiz.Claves[j-1].nump);
			}
		}
		return si;
	}
	//Recorrido InOrden Iterativo
	void InordenIT(){
		int i=0;
		Bnodo nodo=p;
		ListaSimpleB Pila = new ListaSimpleB();
		do{
			i=0;
			while (!Vacio(nodo)){
				Pila.InsertaFinal(nodo, i);
				nodo=nodo.Ramas[i];
		    }
			if (!Pila.VaciaLista()){
				i=Pila.UltimoNodo.indice;
				nodo=Pila.UltimoNodo.datos;
				Pila.EliminaFinal();
				i++;
				if (i <= nodo.Cuentas){
					salida=salida+"Num Prestamo: "+nodo.Claves[i-1].nump+"\n             -Id: "+nodo.Claves[i-1].ident;
					salida=salida+"\n             -Cod Tema: "+nodo.Claves[i-1].codtem+"\n             -Cod Libro: "+nodo.Claves[i-1].codlib+"\n             -Cod Autor: "+nodo.Claves[i-1].codaut+"\n             -Fecha/hora: "+nodo.Claves[i-1].fecha+"\n";
					salida=salida+"-------------------------------\n";
					if (i < nodo.Cuentas)
						Pila.InsertaFinal(nodo, i);
					nodo=nodo.Ramas[i];	
				}
			}
		}
		while (!Pila.VaciaLista() || !Vacio(nodo)); 	
	}
}
//Nodo de arbol b
class Bnodo{
	Bnodo[]Ramas = new Bnodo[5];
	NodoPr[]Claves = new NodoPr[4];
	int Cuentas = 0;
	Bnodo(NodoPr clave){
		Claves[0] = clave;	
	}
	Bnodo(){
	}
}

//Definicion de la clase lista
class ListaSimpleB{
	NodosLista PrimerNodo;
	NodosLista UltimoNodo;
	//Retorna True si lista Vacia
	public boolean VaciaLista(){
		return PrimerNodo ==null;
	}
	public ListaSimpleB(String s){
		PrimerNodo=UltimoNodo=null;
	}
	public ListaSimpleB(){
		this ("Lista");
	}
	//Inserta un elemento al frente de la lista (solo prestamos)
	void InsertaInicio(Bnodo ElemInser,int ind){
		if(VaciaLista())
			PrimerNodo=UltimoNodo=new NodosLista(ElemInser,ind);
		else
			PrimerNodo=new NodosLista(ElemInser, PrimerNodo,ind);
	}
	//Inserta al final de la lista (solo prestamos)
	public void InsertaFinal(Bnodo ElemInser,int ind){
		if(VaciaLista()){
			PrimerNodo= UltimoNodo = new NodosLista (ElemInser,ind);
	    }
		else{
			UltimoNodo=UltimoNodo.siguiente=new NodosLista(ElemInser,ind);
	    }
	}
	//Elimina al final (todos)
	public void EliminaFinal(){
    	if (VaciaLista())
      		System.out.println ("No hay elementos");
		if (PrimerNodo.equals (UltimoNodo))
      		PrimerNodo = UltimoNodo = null;
    	else{
    		NodosLista Actual =PrimerNodo;
      		while (Actual.siguiente != UltimoNodo)
          		Actual = Actual.siguiente;
			UltimoNodo =Actual;
      		Actual.siguiente = null;
    	}
	}	
	//Inserta al final de la lista (normal)
	public void InsertaFinal(String ElemInser){
		if(VaciaLista()){
			PrimerNodo= UltimoNodo = new NodosLista (ElemInser);
	    }
		else{
			UltimoNodo=UltimoNodo.siguiente=new NodosLista(ElemInser);
	    }
	}
	//Elimina al Inicio (todos)
	public void EliminaInicio(){
		if(VaciaLista())
			System.out.println("No hay elementos");
		else
			if(PrimerNodo.equals(UltimoNodo))
				PrimerNodo=UltimoNodo=null;
			else
				PrimerNodo=PrimerNodo.siguiente;
	}
	// Largo
	public void Largo(){
		NodosLista aux=PrimerNodo;
		int Lar =0;
		while (aux!= null){
	  		Lar++;
	  		aux =aux.siguiente;
    	}
    	System.out.println ( "El largo de la Lista es: "+ Lar);
	}
	//Imprimir
	void Imprimir(){
		NodosLista m=PrimerNodo;
		String k;
		while(m!=null){
			k=String.valueOf(m.letra);
			if(k.equals("#"))
				System.out.print(" ");
			else{
				System.out.print(m.letra);
			}
			m=m.siguiente;
		}
		System.out.println("");
	}
}
//Nodo de lista simple
class NodosLista{
	Bnodo datos;
	String letra;
	int indice;
	NodosLista siguiente;
	NodosLista(Bnodo valor,int ind){
		datos=valor;
		indice=ind;
		siguiente=null;
	}
	NodosLista (Bnodo valor,NodosLista signodo,int ind){
		datos=valor;
		indice=ind;
		siguiente=signodo; 
	}
	NodosLista(String valor){
		letra=valor;
		siguiente=null;
	}
	NodosLista (String valor,NodosLista signodo){
		letra=valor;
		siguiente=signodo; 
	}
}
//Nodo de prestamos
class NodoPr{
	int nump;
	int ident;
	int codlib;
	int codtem;
	int codaut;
	String fecha;
	NodoPr(int val){
		nump=val;
	}
	NodoPr(){
	}
}
