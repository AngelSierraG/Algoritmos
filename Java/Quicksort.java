public class Quicksort{

public Quicksort(){
    super();
}
private int put(int v[], int b, int t){
    int i;
    int valor_pivote;
    int pivote;
    int temp;
 
    pivote = b;
    valor_pivote = v[pivote];
    for (i=b+1; i<=t; i++){
        if (v[i] < valor_pivote){
                 pivote++;    
                temp=v[i];
                v[i]=v[pivote];
                v[pivote]=temp;
        }
    }
    temp=v[b];
    v[b]=v[pivote];
    v[pivote]=temp;
    return pivote;
} 
 
public void sort(int v[], int b, int t){
     int pivote;
     if(b < t){
        pivote=put(v, b, t);
        sort(v, b, pivote-1);
        sort(v, pivote+1, t);
     }  
}
}