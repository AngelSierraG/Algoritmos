import java.util.Date;
import arbol.*;

public class Sequence {

public static void createsequence(Test t,int insertcase){
	t.createSequence(t,insertcase);
	
	System.out.println("Secuencias Creadas!");
	
	System.out.println("Insert " + t.insert.getNElems() + ":" + t.insert.print());
	
	System.out.println("Search " + t.search.getNElems() + ":"  + t.search.print());
	
	System.out.println("Delete " + t.del.getNElems() + ":" +  t.del.print());

	return;
}

/* Funcion que generara la sequencia , utilizando la distintas funciones de search, eliminar, e insert elementos al arreglo*/
public static int dosequence(IArbolOrdenado<Integer> tree,Test t) throws ElementoExisteException, ElementoNoExisteException{
	int i,j,d,s;
	long timer;
	Date currentDate;
	
	Array debug;
	debug = new Array(t.k * t.n);
	/*
	(i^k d^k i^k )^n f^k*n (d^k i^k d^k )^n
	*/
	
	/*INICIAR TIEMPO SUB secuencia*/
	currentDate = new Date();

    timer = currentDate.getTime();
    
    

	for(j=0,s=0,d=0; s < t.k*t.n ;){
		for(i=0;i < t.k;i++){
			debug.putElem(t.insert.getElem(j));
			tree.insertar(t.insert.getElem(j++));
			s++;
			}
		
		System.out.println("ins Tengo debug " + debug.getNElems() + ":" + debug.print());

		for(i=0;i < t.k;i++){
			debug.delElem(t.del.getElem(d));
			System.out.println("borrando " +t.del.getElem(d));
			tree.eliminar(t.del.getElem(d++));
			
			s--;
			}
		System.out.println("del Tengo debug " + debug.getNElems() + ":" + debug.print());

		for(i=0;i < t.k;i++){
			debug.putElem(t.insert.getElem(j));
			tree.insertar(t.insert.getElem(j++));
			s++;
			}
		System.out.println("ins Tengo debug " + debug.getNElems() + ":" + debug.print());
	}
	System.out.printf("\tTiempo 1era sub secuencia = %d\n", currentDate.getTime()- timer);

	timer = currentDate.getTime();
	for(s=0;s< t.n*t.k;s++){
		tree.buscar(t.search.getElem(s));
	}
	System.out.printf("\tTiempo 2da sub secuencia = %d\n", currentDate.getTime()- timer);
	
	timer = currentDate.getTime();
	for(s = t.k*t.n - 1; s >= 0 ; ){
		for(i=0;i < t.k;i++){
			System.out.println("borrando " +t.del.getElem(d));
			debug.delElem(t.del.getElem(d));
			//System.out.println("Eliminando :" + t.del.getElem(d));
			tree.eliminar(t.del.getElem(d++));
			s--;
			}
		System.out.println("del Tengo debug " + debug.getNElems() + ":" + debug.print());
		for(i=0;i < t.k;i++){
			debug.putElem(t.insert.getElem(j));
			tree.insertar(t.insert.getElem(j++));
			s++;
			}
		System.out.println("ins Tengo debug " + debug.getNElems() + ":" + debug.print());
		for(i=0;i < t.k;i++){
			System.out.println("borrando " +t.del.getElem(d));
			debug.delElem(t.del.getElem(d));
			//System.out.println("Eliminando :" + t.del.getElem(d));
			tree.eliminar(t.del.getElem(d++));
			s--;
			}
		System.out.println("final Tengo debug " + debug.getNElems() + ":" + debug.print());
	}
	System.out.printf("\tTiempo 3era sub secuencia = %d\n\n", currentDate.getTime()- timer);

	//freeTest(t);

	/*FINALIZA TIEMPO*/

	return 1;
}
}