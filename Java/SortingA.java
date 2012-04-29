interface SortingA{
	private static SortingA INSTANCE = new SortingA();

	public static SortingA getInstance(){
		return INSTANCE;
	}
	public void sort(int array[],int a,int b);

}