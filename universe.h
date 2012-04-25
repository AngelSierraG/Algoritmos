#include <stdlib.h>
#include <math.h>

typedef struct universe{
 int range;
};Universe

Universe *newUniverse(int range){
	/* range = 2^24 -1 o 2^16-1*/
	Universe u;
	u = (Universe *)malloc(sizeof(Universe *));
	u->range = range;
	return u;
}

int getrandomNumber(Universe *u){
	int max_number;
	double random_number;

	max_number = pow (2,(int)u->range);

	/* Numero al azar entre 0 y max_number*/
	random_number = (int)(srand (time(NULL)) % max_number );
	return random_number;
}