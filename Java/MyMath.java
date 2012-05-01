public class MyMath{
	static public int pow(int base,int range){  
	int i;
	int result;

	if(range == 0)
		return 1;
	result = base;
	for(i=1;i<range;i++)
		result = result * base;
	return result;

}
}