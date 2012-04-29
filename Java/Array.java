import java.util.Date;
import java.util.Random;

public class Array {

/*Estructura array*/
private BigList values;
private long length;
private Date currentDate;
private Random generator;
private SortingA s;

public Array(long len){
	
	this.length = len;
	this.values = new BigList(len);
	return;
}

public boolean isEmpty(){
	if (this.length == 0)
		return true;
	else
		return false;
}

private boolean checkIndex(int k){
	if (k >= this.length){
		return false;
	}
	return true;
}

private void handleBounds(int j){
	if (!checkIndex(j)){
		System.out.printf("Index out of bounds: %d!!!",j);
		System.exit(1);
	}
	return;
}

public void deleteAll(){
	int i;
	for(i=0;i<this.length;i++)
		this.values.deleteAll();
	this.length = 0;
	return;
}

public void putElem( long j,long l){
	//handleBounds(k);
	this.values.put(j,l);
	return;
}
int getElem(long j){
	//handleBounds(j);
	return this.values.get(j);
}

long getLength(){
	return this.length;
}

public boolean delElemI(int k){
	handleBounds(k);
	this.values.delete(k);
	return true;
}

void copy(Array out){
	/*copiar a en out*/
	int i;
	if(this.getLength()!= out.getLength())
		System.out.printf("Copy out of bounds %d into %d\n", this.getLength(),out.getLength());
	for(i=0; i < this.getLength() ; i++){
		out.putElem(i,this.getElem(i));
	}
	return;
}

private void swapElems(long j,long k){
	int aux;
	aux = this.getElem(j);
	this.putElem(j,this.getElem(k));
	this.putElem(k,aux);
	return;
}

public void shuffle( long l){
	long i,j,k;

	this.currentDate = new Date();
	this.generator = new Random(this.currentDate.getTime());

	for(i=0; i < l ; i++){
		j= generator.nextLong() % this.getLength();
		k= generator.nextLong() % this.getLength();
		swapElems(j,k);
	}
	return;
}

void putElem(int elem){
	int i;
	for(i=0; i < this.getLength() ; i++)
		if (this.getElem(i)!= -1){
			this.putElem(i,elem);
			return ;
		}
	return;
}
void delElem(int elem){
	int i;
	for(i=0; i < this.getLength() ; i++)
		if (this.getElem(i) == elem){
			swapElems(i,this.getLength()-1);
			this.length--;
			return ;
		}
	return;
}

/*Arreglo desordenado*/
int searchElem(int elem){
	int i;
	for(i=0 ; i < this.getLength() ; i++)
		if(this.getElem(i) == elem )
			return elem; /*true*/
	return -1; /*false*/
}

void sort(){
	s = new BigListSort();
	s.sort(this.values,0,this.length-1);
}
}