import java.util.Date;
import arbol.*;

/*CASE1
Los elementos a insert son escogidos al azar uniformemente del universo,
 y los a delete,al azar uniformemente del conjunto ya insertado en la estructura
*/
/*CASE2
Los elementos a insert y a delete se encuentran semiordenados.
*/
/*CASE3
Sólo los elementos a insert están semiordenados (por lo que los elementos a delete son
elegidos al azar uniformemente del conjunto insertado)
*/
/*CASE4
Sólo los elementos a delete están semiordenados (por lo que los elementos a insert son
elegidos al azar uniformemente del universo)
*/
public class Sequence {
static final int case1=0;
static final int case2=1;
static final int case3=2;
static final int case4=3;

private static void createISequence(Test t,long insertcase){
	if(case1 == insertcase || case4 == insertcase){
		t.createRandomInsertSeq();
	}
	else if(case2 == insertcase || case3 == insertcase){
		t.createSemiOrderInsertSeq();
	}
	return;
}

private static void createDSSequence(Test t,long seqcase){
	if(case1 == seqcase || case3 == seqcase){
		t.createRandomDeleteSearchSeq();
	}
	else if(case2 == seqcase || case4 == seqcase){
		t.createSemiOrderDeleteSearchSeq();
	}
	return;
}

public static void createsequence(Test t,long insertcase){
	createISequence(t,insertcase);
	createDSSequence(t,insertcase);
	return;
}

/* Funcion que generara la sequencia , utilizando la distintas funciones de search, eliminar, e insert elementos al arreglo*/
public static int dosequence(IArbolOrdenado<Integer> tree,Test t) throws ElementoExisteException, ElementoNoExisteException{
	int i,j,d,s;
	long timer;
	Date currentDate;

	/*
	(i^k d^k i^k )^n f^k*n (d^k i^k d^k )^n
	*/
	
	/*INICIAR TIEMPO SUB secuencia*/
	currentDate = new Date();

    timer = currentDate.getTime();

	for(j=0,d=0; j< 2*t.n*t.k ;){
		for(i=0;i < t.k;i++)
			tree.insertar(t.insert.getElem(j++));

		for(i=0;i < t.k;i++)
			tree.eliminar(t.del.getElem(d++));

		for(i=0;i < t.k;i++)
			tree.insertar(t.insert.getElem(j++));
	}
	System.out.printf("\tTiempo 1era sub secuencia = %d\n", currentDate.getTime()- timer);

	timer = currentDate.getTime();
	for(s=0;s< t.n*t.k;s++){
		tree.buscar(t.search.getElem(s));
	}
	System.out.printf("\tTiempo 2da sub secuencia = %d\n", currentDate.getTime()- timer);
	
	timer = currentDate.getTime();
	for(;j< 3*t.n*t.k ; ){
		for(i=0;i < t.k;i++)
			tree.eliminar(t.del.getElem(d++));

		for(i=0;i < t.k;i++)
			tree.insertar(t.insert.getElem(j++));

		for(i=0;i < t.k;i++)
			tree.eliminar(t.del.getElem(d++));
	}
	System.out.printf("\tTiempo 3era sub secuencia = %d\n", currentDate.getTime()- timer);

	//freeTest(t);

	/*FINALIZA TIEMPO*/

	return 1;
}
}