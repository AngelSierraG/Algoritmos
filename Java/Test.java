public class Test {

public int type,n,k,range;
public Array insert,del,search;
public Universe u;

public Test(int n,int k,int range){
	System.out.printf("creando estructura de test \n");
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

void createRandomInsertSeq(){
	int i;
	
	System.out.println("creating random inserte sequence");
	for(i=0; i < this.insert.getLength() ; i++)
		this.insert.putElem(i,this.getRandomNumber());
	return;
}

void createRandomDeleteSearchSeq(){
	int i,j,s,auxelem;
	Array tengo;

	tengo = new Array(this.k * this.n);

	for(j=0; j< this.n*this.k ;){
		for(i=0;i < this.k;i++){
			tengo.putElem(this.insert.getElem(j++));
		}
		tengo.shuffle(this.k/2);
		for(i=0;i < this.k;i++){
			auxelem = tengo.getElem(i);
			this.del.putElem(auxelem);
			tengo.delElem(auxelem);
		}
		for(i=0;i < this.k;i++)
			tengo.putElem(this.insert.getElem(j++));
	}
	
	tengo.shuffle(this.k/2);
	for(s=0;s< this.n*this.k;s++){
		this.search.putElem(tengo.getElem(s));
	}
	
	for(;j< this.n*this.k ;){
		tengo.shuffle(this.k/2);
		for(i=0;i < this.k;i++){
			auxelem = tengo.getElem(i);
			this.del.putElem(auxelem);
			tengo.delElem(auxelem);
		}
		for(i=0;i < this.k;i++){
			tengo.putElem(this.insert.getElem(j++));
		}
		tengo.shuffle(this.k/2);
		for(i=0;i < this.k;i++){
			auxelem = tengo.getElem(i);
			this.del.putElem(auxelem);
			tengo.delElem(auxelem);
		}
	}
	return;
}

void createSemiOrderInsertSeq(){
	createRandomInsertSeq();
	this.insert.sort();
	this.insert.shuffle(this.n/4);
	return;
}

void createSemiOrderDeleteSearchSeq(){
	createRandomDeleteSearchSeq();
	this.del.sort();
	this.search.sort();

	this.del.shuffle(this.n/4);
	this.search.shuffle(this.n/4);
	return;
}

}