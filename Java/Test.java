public class Test {

public int type,n,k,range;
public Array insert,del,search;
public Universe u;
static final int case1=0;
static final int case2=1;
static final int case3=2;
static final int case4=3;
boolean needToCreate,needToGrow,needToRepeat,needToMinimize,needToExit;
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
public Test(){
	n=-1;
	k=-1;
	range=-1;
	insert=null;
	del=null;
	search=null;
	needToCreate= false;
	needToGrow = false;
	needToRepeat = false;
	needToMinimize = false;
}

public void setTest(int n2,int k2,int range2){ 
	if (range2 > range){
		u= new Universe(range2);
		/*
		this.insert = new Array(k2 * n2 * 3);
		this.del = new Array(k2 * n2 * 3);
		this.search = new Array(k2*n2);
		*/
		this.insert = new Array(n2);
		this.del = new Array(3*k2*n2);
		this.search = new Array(n2);

		needToCreate = true;
	}
	if(n < n2)
		this.needToGrow = true;
	if(k2 < k)
		this.needToMinimize = true;
	
	this.n = n2;
	this.k = k2;
	this.range = range2; 
	
	//this.randomInsert = false;
	//this.semiOrdered = false;
	
	//System.out.println("insert:" + insert.print());
}

public Test(int n2,int k2,int range2){
	////System.out.printf("creando estructura de test \n");
	n = n2;
	k = k2;
	range = range2; 
	u= new Universe(range2);
	insert = new Array(k2 * n2 * 3);
	del = new Array(k2 * n2 * 3);
	search = new Array(k2*n2);
	//randomInsert = false;
	//semiOrdered = false;
	needToGrow = true;
	needToCreate = false;
	needToRepeat = false;
	needToMinimize = false;
	//return this;
}

private int getRandomNumber(){
	return u.getRandomNumber();
}

void createRandomInsertSeq(){
	int i;
	int number;

	if(needToCreate){
		insert.setNElem(0);
		for(i=0; i < insert.getLength() ; i++){
		number = getRandomNumber();
		////System.out.println("number=" + number);
		insert.putElem(i,number);
		}
		needToCreate = false;
		return;
	}
	else if(!needToCreate && needToGrow){
		for(i=insert.getNElems(); i < insert.getLength() ; i++){
		number = getRandomNumber();
		////System.out.println("number=" + number);
		insert.putElem(i,number);
		}
		needToGrow = false;
		return;
	}
	else if(!needToCreate && !needToGrow){

	}
}

void createSemiOrderInsertSeq(){
	createRandomInsertSeq();
	//System.out.printf("Creando secuencia semi ordenada de inserción\n");
	
	//System.out.println("INSERT " + insert.getNElems() + ":" + insert.print());
	insert.sort();
	insert.shuffle(insert.getNElems()/4);
	
	//System.out.println("Insert semi ordenado: " + insert.print());
	
	//randomInsert = false;
	//semiOrdered = true;  
	return;
}

void handleInsertSeqCase(int seqcase){
	if(seqcase == case1 || seqcase == case4){
		createRandomInsertSeq();
	}
	else{
		createSemiOrderInsertSeq();
	}
	return ;
}

void handleDelSeqCase(Array tengo,int seqcase){
	if(seqcase == case1 || seqcase == case3){
		tengo.shuffle(tengo.getNElems()/2);
		//System.out.println("Tengo random " + tengo.getNElems() + ":" + tengo.print());
	}
	else{
		tengo.sort();
		tengo.shuffle(tengo.getNElems()/4);
		//System.out.println("Tengo semiordenado " + tengo.getNElems() + ":" + tengo.print());
	}
	return;
}

private void checkBounds(Array tengo,int _case){
	if(_case == 0){ /*inser*/
		if(tengo.isFull())
			needToExit = true;
	}
	else if(_case == 1){/*borrar*/
		if(tengo.isEmpty())
			needToExit = true;
	}
}

public void createSequence(int seqcase){
	int i,j,s,auxelem;
	Array tengo;
	boolean needToExit;

	del.setNElem(0);
	search.setNElem(0);
	
	tengo = new Array(k*n);
	handleInsertSeqCase(seqcase);

	for(j=0,s=0; s< n ;){
		for(i=0;i < k;i++){
			tengo.putElem(insert.getElem(j++ % n));
			s++;
		}
		handleDelSeqCase(tengo,seqcase);
		for(i=0;i < k;i++){
			auxelem = tengo.getElem(0);
			del.putElem(auxelem);
			tengo.delElemIndex(0);
			s--;
		}
		for(i=0;i < k;i++){
			tengo.putElem(insert.getElem(j++ % n));
			s++;
			}
	}
	tengo.shuffle(tengo.getNElems()/2);
	for(s=0;s< n;s++){
		search.putElem(tengo.getElem(s));
	}
	
	for(s=n-1;s>=0 ;){
		handleDelSeqCase(tengo,seqcase);
		for(i=0;i < k;i++){
			auxelem = tengo.getElem(0);
			del.putElem(auxelem);
			tengo.delElemIndex(0);
			s--;
		}
		for(i=0;i < k;i++){
			tengo.putElem(insert.getElem(j++ % n));
			s++;
		}
		handleDelSeqCase(tengo,seqcase);
		for(i=0;i < k;i++){
			auxelem = tengo.getElem(0);
			del.putElem(auxelem);
			tengo.delElemIndex(0);
			s--;
		}
	}
	return;
}

void createSemiOrderDeleteSearchSeq(){
	//createRandomDeleteSearchSeq();
	del.sort();
	search.sort();

	del.shuffle(del.getNElems()/4);
	search.shuffle(search.getNElems()/4);
	return;
}

}