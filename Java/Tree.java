public interface Tree{

/* Dispatch de funciones dependiendo del tipo de arbol*/
void insert(int element);
void delete(int element);
int search(int element);
boolean isEmpty();
}