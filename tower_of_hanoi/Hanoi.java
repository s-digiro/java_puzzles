import java.util.Scanner;

public class Hanoi
{
	public static void main(String args[])
	{
		Scanner kb = new Scanner(System.in);
		int size = 0;

		// Prompts user for disk amount, enters loop if amount is invalid
		System.out.print("How many disks?: ");
		size = kb.nextInt();
		while (size < 1) {
			System.out.print("INVALID (Must have more than 0 disks) Enter new number: ");
			size = kb.nextInt();
		}

		// Creates 3 Stacks to represent towers and fills first tower with integers size -> 1
		Stack<Integer> tower1 = new Stack<Integer>("tower1");
		for (int i = 0; i < size; i++) {
			tower1.push(size - i);
		}
		Stack<Integer> tower2 = new Stack<Integer>("tower2");
		Stack<Integer> tower3 = new Stack<Integer>("tower3");

		solve(size, tower1, tower3, tower2);

		// Prints the contents of the three towers
		while (!tower1.empty() || !tower2.empty() || !tower3.empty()) {
			System.out.println(tower1.pop() + "		" + tower2.pop() + "		" + tower3.pop());
		}
	}
	public static boolean solve(int size, Stack<Integer> source, Stack<Integer> dest, Stack<Integer> other)
	{
		// Base value, moves last disk from source to destination
		if (size == 1) {
			System.out.println("Move " + source.peek() + " from " + source + " to " + dest + ".");
			dest.push(source.pop());
			return true;
		} else {
			// Moves all but bottom disk in source to transfer tower
			solve(size - 1, source, other, dest);
			System.out.println("Move " + source.peek() + " from " + source + " to " + dest + ".");
			// Moves bottom disk to destination
			dest.push(source.pop());
			// Moves disks from transfer tower to destination tower
			solve(size - 1, other, dest, source);
			return true;
		}
	}
}
