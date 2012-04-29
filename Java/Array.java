public class Array {

/*Estructura array*/
private int values[];
private int length;
private int top;
private Date currentDate;
private Random generator;
private SortingA s;

public Array(int len){
	int i;
	
	this.length = length;
	this.values = new int[len];
	this.top =0;

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
	if !checkIndex(k){
		System.out.printf("Index out of bounds: %d!!!",k);
		System.exit(true);
	}
	return;
}

public void deleteAll(){
	int i;
	for(i=0;i<this.length;i++)
		this.values[i] = -1;
	this.length = 0;
	this.top = 0;
	return;
}

static void putElem( int k,int elem){
	handleBounds(k);
	this.values[k] = elem;
	this.top++;
	return;
}
int getElem(int k){
	handleBounds(k);
	return this.values[k];
}

int getLength(){
	return this.length;
}

boolean delElem(int k){
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
	generator = new new Random(this.currentDate.getTime());

	for(i=0; i < iter ; i++){
		srand(time(NULL));
		j= generator.nexInt() % this.getLength();
		k= generator.nexInt() % this.getLength();
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
			this.top--;
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
			return true; /*true*/
	return false; /*false*/
}

void sort(int type){
	if type == 0
		s = new Quicksort().getInstance();
		s.sort(this.values,0,this.length-1);
}
}