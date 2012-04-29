import java.util.Date;

static final int case1 0;
static final int case2 1;
static final int case3 2;
static final int case4 3;

/*CASE1
Los elementos a insertar son escogidos al azar uniformemente del universo,
 y los a borrar,al azar uniformemente del conjunto ya insertado en la estructura
*/
/*CASE2
Los elementos a insertar y a borrar se encuentran semiordenados.
*/
/*CASE3
Sólo los elementos a insertar están semiordenados (por lo que los elementos a borrar son
elegidos al azar uniformemente del conjunto insertado)
*/
/*CASE4
Sólo los elementos a borrar están semiordenados (por lo que los elementos a insertar son
elegidos al azar uniformemente del universo)
*/


public class Sequence {

private static void createISequence(Test t,Array insert,int seqcase){
	if(case1 == seqcase || case4 == seqcase){
		createRandomInsertSeq(t,insert);
	}
	else if(case2 == seqcase || case3 == seqcase){
		createSemiOrderInsertSeq(t,insert);
	}
	return;
}

private static void createDSSequence(Test t,Array insert,Array del,Array search,int seqcase){
	if(case1 == seqcase || case3 == seqcase){
		createRandomDeleteSearchSeq(t,insert,del,search);
	}
	else if(case2 == seqcase || case4 == seqcase){
		createSemiOrderDeleteSearchSeq(t,insert,del,search);
	}
	return;
}

public static void createsequence(void tree,Test t,int seqcase){
	createISequence(t,t.insert,seqcase);
	createDSSequence(t,t.insert,t.del,t.search,seqcase);
	return;
}

/* Funcion que generara la sequencia , utilizando la distintas funciones de buscar, eliminar, e insertar elementos al arreglo*/
public static int dosequence(void tree,Test t) {
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
			tree.insertar(tree,t.type,getElem(t.insert,j++));

		for(i=0;i < t.k;i++)
			borrar(tree,t.type,getElem(t.del,d++));

		for(i=0;i < t.k;i++)
			insertar(tree,t.type,getElem(t.insert,j++));
	}
	System.out.printf("\tTiempo 1era sub secuencia = %d\n", currentDate.getTime()- timer);

	timer = currentDate.getTime();
	for(s=0;s< t.n*t.k;s++){
		buscar(tree,t.type,getElem(t.search,s));
	}
	System.out.printf("\tTiempo 2da sub secuencia = %d\n", currentDate.getTime()- timer);
	
	timer = currentDate.getTime();
	for(;j< 3*t.n*t.k ; ){
		for(i=0;i < t.k;i++)
			borrar(tree,t.type,getElem(t.del,d++));

		for(i=0;i < t.k;i++)
			insertar(tree,t.type,getElem(t.insert,j++));

		for(i=0;i < t.k;i++)
			borrar(tree,t.type,getElem(t.del,d++));
	}
	System.out.printf("\tTiempo 3era sub secuencia = %d\n", currentDate.getTime()- timer);

	//freeTest(t);

	/*FINALIZA TIEMPO*/

	return 1;
}
}