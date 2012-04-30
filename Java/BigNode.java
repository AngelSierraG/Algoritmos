public class BigNode extends BigListPart{
	BigListPart next;
	long index;
	long value;
	public BigNode(long l) {
		if (l == 0){
			next = new BigEnd();
		}
	}
}
