/*Estructura array*/
typedef struct array{
	int values[];
	int length;
}Array;

void newArray(int length){
	Array *a;

	a = (Array *)malloc (sizeof(Array *));
	a->length = length;
	a->values = int[length];

	for(int i=0;i<length;i++)
		(a->values)[i]= -1;

	return a;
}

void newSet(Universe *u,Array *a);