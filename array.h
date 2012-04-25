/*Estructura array*/
typedef struct array{
	int *values;
	int length;
}Array;

bool isEmptyArray(Array *a){
	if (a->length == 0)
		return true;
	else
		return false;
}

void checkIndex(Array *a,int k){
	if (k >= a->length){
		printf("Index out of bounds: %d!!!",k);
		exit(1);
	}
	return;
}

Array *newArray(int length){
	Array *a;
	int i,*aux;

	a = (Array *)malloc (sizeof(Array *));
	aux= (int *)aux[length];

	a->length = length;
	a->values = aux;

	for(i=0;i<length;i++)
		(a->values)[i]= -1;

	return a;
}

void deleteArrayElements(Array *a){
	int i;
	for(i=0;i<a->length;i++)
		a->values[i] = -1;
	a->length = 0;
	return;
}

void putArrayElem(Array *a, int k,int elem){
	checkIndex(a,k);
	a->values[k] = elem;
	return;
}
int getArrayElem(Array *a,int k){
	checkIndex(a,k);
	return a->values[k];
}

int getArrayLength(Array *a){
	return a->length;
}

int delArrayElem(Array *a,int k){
	checkIndex(a,k);
	a->values[k] = -1;
}

int compareElem(const void *a,const void *b){
	if(*(int *)a<*(int *)b)
		return(-1);
	else if(*(int *)a>*(int *)b)
		return(1);
	else 
		return(0);
}

void sortArray(Array *a){
	/*void qsort(void *base, size_t nmiemb, size_t tam,
        int (*compar)(const void *, const void *));*/
	qsort(a->values,sizeof(getArrayLength(a)),sizeof(a->values[0]),compareElem);
	return;
}