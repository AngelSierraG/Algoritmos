import java.util.Random;
import java.util.Math;
import java.util.Date;

public class Universe{
private int range;
private int max;
private Date currentDate;
private Random generator;

public Universe(int _range){
	this.range = _range;
	this.currentDate = new Date();
	this.generator = new Random(this.currentDate.getTime());
	this.max = (Integer) MyMath.pow(2,this.range);
	return;
}

public void restart(){
	this.generator = new Random(this.currentDate.getTime());
}

public void newRange(int _range){
	this.range= _range;
	this.max = MyMath.pow(2,this.range);
	return;
}

public int getRandomNumber(){
	return generator.nextInt() % max;
}

}