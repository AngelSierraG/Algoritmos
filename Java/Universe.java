import java.util.Random;
import java.util.Date;

public class Universe{ 
private int range;
private Date currentDate;
private Random generator;

public Universe(int range2){
	this.range = range2; 
	this.currentDate = new Date();
	this.generator = new Random(this.currentDate.getTime()); 
	
	//System.out.printf("range=%d\n",this.range);
	return;
}

public void restart(){
	this.generator = new Random(this.currentDate.getTime());
}

public void newRange(int _range){
	this.range= _range;
	return;
}

public int getRandomNumber(){
	int number;
	number = this.generator.nextInt() % this.range;
	if(number < 0)
		number = number * -1;
	return number;
}

}