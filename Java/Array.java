import java.util.Date;
import java.util.Random;

public class Array {

/*Estructura array*/
private int values[];
private int length;
private Date currentDate;
private Random generator;
private SortingA s;

public Array(int len){
	int i;
	
	this.length = len;
	this.values = new int[len];

	for(i=0;i<length;i++)
		(this.values)[i]= -1;
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

private void handleBounds(int k){
	if (!checkIndex(k)){
		System.out.printf("Index out of bounds: %d!!!",k);
		System.exit(1);
	}
	return;
}

public void deleteAll(){
	int i;
	for(i=0;i<this.length;i++)
		this.values[i] = -1;
	this.length = 0;
	return;
}

public void putElem( int k,int elem){
	handleBounds(k);
	this.values[k] = elem;
	return;
}
int getElem(int k){
	handleBounds(k);
	return this.values[k];
}

int getLength(){
	return this.length;
}

public boolean delElemI(int k){
	handleBounds(k);
	this.values[k] = -1;
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

private void swapElems(int i,int j){
	int aux;
	aux = this.getElem(i);
	this.putElem(i,this.getElem(j));
	this.putElem(j,aux);
	return;
}

public void shuffle( int iter){
	int i,j,k;

	this.currentDate = new Date();
	this.generator = new Random(this.currentDate.getTime());

	for(i=0; i < iter ; i++){
		j= generator.nextInt() % this.getLength();
		k= generator.nextInt() % this.getLength();
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
	s = new Quicksort().getInstance();
	s.sort(this.values,0,this.length-1);
}
}