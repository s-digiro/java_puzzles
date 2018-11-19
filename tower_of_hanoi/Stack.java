/*
 * Implement a reference based stack
 */
public class Stack<E> {
	
	private Node<E> top;
	private String name;

	public Stack() {
		this.top = null;
		this.name = "stack";
	}

	public Stack(String s) {
		this.top = null;
		this.name = s;
	}

	public void name(String s) {
		this.name = s;
	}

	public String toString() {
		return name;
	}
	
	
	/*
	 * places element on the top of the stack
	 */
	public void push(E element){
		//Fill in
		Node<E> n = new Node<E>(element);

		n.next = this.top;
		this.top = n;
	}
	
	/*
	 * remove the top node and return its contents
	 */
	public E pop(){
		//Fill in;
		// Empty Stack
		if (this.top == null) {
			return null;
		}
		// Else
		E retval = this.top.item;
		this.top = this.top.next;

		return retval; //replace
	}
	
	/*
	 * Look at the top element of the Stack and return it, without removing
	 */
	public E peek(){
		//Fill in;
		// Empty stack
		if (this.top == null) {
			return null;
		}
		// Else
		return this.top.item; //replace
	}
	
	public boolean empty(){
		return (this.top == null);  //replace
	}
}
