public class Test {

public int type,n,k,range;
public Array insert,del,search;
public Universe u;

public Test(int type,int n,int k,int range){
	printf("creando estructura de test \n");
	this.type = type;
	this.n = n;
	this.k = k;
	this.range = range;
	this.u= new Universe(range);
	this.insert = new Array(k * n * 3);
	this.del = new Array(k * n * 3);
	this.search = new Array(k*n);
	//return this;
}

private int getRandomNumber(){
	return u.getRandomNumber();
}

void createRandomInsertSeq(Test t){
	int i;
	
	System.out.println("creating random inserte sequence");
	for(i=0; i < getLength(insert) ; i++)
		t.insert.putElem(i,t.getRandomNumber());
	return;
}

void createRandomDeleteSearchSeq(Test t){
	int i,j,s,k,auxelem;
	Array tengo;

	tengo = newArray(t.k * t.n);

	for(j=0; j< t.n*t.k ;){
		for(i=0;i < t.k;i++){
			t.tengo.putElem(t.insert.getElem(j++));
		}
		tengo.shuffleArray(t.k/2);
		for(i=0;i < t.k;i++){
			auxelem = t.tengo.getElem(i);
			t.del.putElem(auxelem);
			tengo.delElem(auxelem);
		}
		for(i=0;i < t.k;i++)
			t.tengo.putElem(t.insert.getElem(j++));
	}
	
	tengo.shuffleArray(t.k/2);
	for(s=0;s< t.n*t.k;s++){
		t.search.putElem(t.tengo.getElem(s));
	}
	
	for(;j< t.n*t.k ;){
		tengo.shuffleArray(t.k/2);
		for(i=0;i < t.k;i++){
			auxelem = tengo.getElem(i);
			t.del.putElem(auxelem);
			tengo.delElem(auxelem);
		}
		for(i=0;i < t.k;i++){
			t.tengo.putElem(t.insert.getElem(j++));
		}
		tengo.shuffleArray(t.k/2);
		for(i=0;i < t.k;i++){
			auxelem = tengo.getElem(i);
			t.del.putElem(auxelem);
			tengo.delElem(auxelem);
		}
	}
	return;
}

void createSemiOrderInsertSeq(Test t){
	createRandomInsertSeq(t);
	t.insert.sortArray();
	t.insert.shuffleArray(t.n/4);
	return;
}

void createSemiOrderDeleteSearchSeq(Test t){
	createRandomDeleteSearchSeq(t);
	t.del.sortArray();
	t.search.sortArray();

	t.del.shuffleArray(t.n/4);
	t.search.shuffleArray(t.n/4);
	return;
}

}