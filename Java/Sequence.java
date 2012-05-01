import java.util.Date;

import arbol.ElementoExisteException;
import arbol.ElementoNoExisteException; 
import arbol.IArbolOrdenado;

public class Sequence {
	public static boolean debug = false;
 
public static void createsequence(Test t,int insertcase){
	t.createSequence(insertcase);
	
	System.out.println("Secuencias Creadas!");
	
	if(debug){
		System.out.println("Insert " + t.insert.getNElems() + ":" + t.insert.print());
	
		System.out.println("Search " + t.search.getNElems() + ":"  + t.search.print());
	
		System.out.println("Delete " + t.del.getNElems() + ":" +  t.del.print());
	}

	return;
}

/* Funcion que generara la sequencia , utilizando la distintas funciones de search, eliminar, e insert elementos al arreglo*/
public static int dosequence(IArbolOrdenado<Integer> tree,Test t) throws ElementoExisteException //throws ElementoExisteException, ElementoNoExisteException{
, ElementoNoExisteException
{
	int i,j,d,s;
	long timer;
	Date currentDate;
	Array debugA = null;
	
	if(debug){
		debugA = new Array(t.k * t.n);
		}
	/*
	(i^k d^k i^k )^n f^k*n (d^k i^k d^k )^n
	*/
	
	/*INICIAR TIEMPO SUB secuencia*/
	currentDate = new Date();

    timer = currentDate.getTime();

	for(j=0,s=0,d=0; s < t.n ;){
		for(i=0;i < t.k;i++){
			if (debug){
				debugA.putElem(t.insert.getElem(j % t.n));
				}
			tree.insertar(t.insert.getElem(j++ % t.n));
			s++;
			}
		for(i=0;i < t.k;i++){
			if(debug)
				debugA.delElem(t.del.getElem(d));
			tree.eliminar(t.del.getElem(d++));
			s--;
			}
		for(i=0;i < t.k;i++){
			if(debug)
				debugA.putElem(t.insert.getElem(j % t.n*t.k));
			tree.insertar(t.insert.getElem(j++ % t.n));
			s++;
			}
	}
	currentDate = new Date();
	System.out.printf("tiempos: 1era=%d\t", currentDate.getTime()- timer);

	timer = currentDate.getTime();
	for(s=0;s< t.n*t.k;s++){
		tree.buscar(t.search.getElem(s % t.n));
	}
	currentDate = new Date();
	System.out.printf("2da=%d\t", currentDate.getTime()- timer);
	
	timer = currentDate.getTime();
	for(s = t.n - 1; s >= 0 ; ){
		for(i=0;i < t.k;i++){
			if(debug)
				debugA.delElem(t.del.getElem(d));
			tree.eliminar(t.del.getElem(d++));
			s--;
			}
		for(i=0;i < t.k;i++){
			if(debug)
				debugA.putElem(t.insert.getElem(j % t.n*t.k));
			tree.insertar(t.insert.getElem(j++ % t.n)); 
			s++;
			}
		for(i=0;i < t.k;i++){
			if(debug)
				debugA.delElem(t.del.getElem(d));
			tree.eliminar(t.del.getElem(d++));
			s--;
			}
	if(debug)
		System.out.println("final Tengo debug " + debugA.getNElems() + ":" + debugA.print());
	}
	currentDate = new Date();
	System.out.printf("3era=%d\n", currentDate.getTime()- timer);

	//freeTest(t);

	/*FINALIZA TIEMPO*/

	return 1;
}
}