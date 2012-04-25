typedef struct universe{
 int range;
}Universe;

Universe *newUniverse(int _range){
	/* range = 2^24 -1 o 2^16-1*/
	Universe *u;
	u = (Universe *)malloc(sizeof(Universe *));
	u->range = _range;
	return u;
}

int getRandomNumber(Universe *u){
	int max_number, random_number;

	max_number = pow(2,u->range);

	/* Numero al azar entre 0 y max_number*/
	srand(time(NULL));
	random_number =  rand() % max_number;
	return random_number;
}