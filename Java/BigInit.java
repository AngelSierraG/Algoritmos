
public class BigInit extends BigListPart{
 	BigListPart next;
	BigListPart top;
	long length;
	
	public BigInit(long len){
		if (len ==0){
			next = new BigEnd();
			top = this;
			length = 0;
		}
		length = len;
		next = new BigNode(len);
		top = next;
		}
	}
