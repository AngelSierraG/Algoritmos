import java.util.Random;
import java.util.Date;

public class Universe{
private long range;
private Date currentDate;
private Random generator;

public Universe(long range2){
	this.range = range2;
	this.currentDate = new Date();
	this.generator = new Random(this.currentDate.getTime());
	
	System.out.printf("range=%d\n",this.range);
	return;
}

public void restart(){
	this.generator = new Random(this.currentDate.getTime());
}

public void newRange(int _range){
	this.range= _range;
	return;
}

public long getRandomNumber(){
	long number;
	
	System.out.printf("random num=%d\t range=%d \n",this.generator.nextInt(),this.range);
	number = this.generator.nextLong() % this.range;
	
	if(number <0)
		number = number * -1;
	
	System.out.printf("random num=%d\t range=%d \n",this.generator.nextInt(),this.range);
	number = this.generator.nextLong() % this.range;
	
	return number;
}

}