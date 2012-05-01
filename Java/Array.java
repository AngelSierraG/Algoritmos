import java.util.Date;
import java.util.Random;

public class Array {

/*Estructura array*/ 
private int values[];
private int length;
private int nelem;
private Date currentDate; 
private Random generator;
private SortingA s;

public Array(int len){
	//int i;
	length = len;
	values = new int[len];
	nelem = 0;
	//for(i=0; i< len ; i++){
	//	values[i] = -1;
	//}
	
	return;
}

public void setNElem(int elem){
	nelem = elem;
}

private boolean checkIndex(int k){
	if (k >= length){
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
	for(i=0;i<length;i++)
		values[i]= -1;
	this.nelem = 0;
	return;
}

public void putElem( int j,int l){
	//handleBounds(j);
	values[j] =l;
	nelem++;
	return;
}
int getElem(int j){
	//handleBounds(j);
	return values[j];
}

int getLength(){
	return length;
}

int getNElems(){
	return nelem;
}

public boolean delElemIndex(int j){
	//handleBounds(j);
	values[j]= -1;
	swapElems(j,nelem-1);
	nelem--;
	return true;
}

void copy(Array out){
	/*copiar a en out*/
	int i;
	if(getLength()!= out.getLength())
		System.out.printf("Copy out of bounds %d into %d\n", getLength(),out.getLength());
	for(i=0; i < getLength() ; i++){
		out.putElem(i,getElem(i));
	}
	return;
}

private void swapElems(int j,int k){
	int aux;
	
	//if(nelem-1 == k)
	//	System.out.println("swap j="+j+" por k="+k+" donde v[j]="+values[j]+" y v[k]="+ values[k] );
	
	aux = values[j];
	values[j] = values[k];
	values[k] = aux;
	return;
}

public void shuffle( int l){
	int i,j,k;
	
	//System.out.println("Shuffling array, nelem=" + nelem);

	currentDate = new Date();
	generator = new Random(currentDate.getTime());

	for(i=0; i < l ; i++){
		j=Math.abs( generator.nextInt() % nelem);
		k=Math.abs( generator.nextInt() % nelem);
		
		//System.out.println("Swap j=" + j + "for k=" + k);
		if(j ==nelem && j>0)
			j--;
		if(k== nelem && k>0)
			k--;
		swapElems(j,k);
	}
	return;
}

void putElem(int elem){
	//handleBounds(nelem);
	values[nelem] = elem;
	nelem++;
	return ;
}
void delElem(int elem){
	int i;
	for(i=0; i < getLength() ; i++)
		if (getElem(i) == elem){
			swapElems(i,getNElems()-1);
			nelem--;
			return ;
		}
	return;
}

/*Arreglo desordenado*/
int searchElem(int elem){ 
	int i;
	for(i=0 ; i < getLength() ; i++)
		if(getElem(i) == elem )
			return elem; /*true*/
	return -1; /*false*/
}

void sort(){
	s = new Quicksort();
	s.sort(values,0,nelem-1); 
}

String print(){
	if(nelem == 0){ 
		return "[]";
	}
	String out;
	int i;
	out = "[";
	for(i=0; i < nelem ; i++)
		out = out + values[i] + ",";
	out = out.substring(0, out.lastIndexOf(","));
	out = out + "]";
	return out;
}

public boolean isFull(){
	if(nelem == length)
		return true;
	else
		return false;
}
public boolean isEmpty(){
	if(nelem == 0){
		return true;
	}
	else
		return false;
}
}