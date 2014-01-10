package p903_LinkedList;

import java.util.Random;
import java.util.Scanner;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			
	
		int i = 1;
		if (args.length == 1) {
			i = Integer.parseInt(args[0]);
		}
		if (i == 0)
			generate();
		else {

			Scanner in = new Scanner(System.in);
			SinglyLinkedList<Integer> sll = new SinglyLinkedList<Integer>();
			while (in.hasNextLine()) {
				String cmd = in.next();
				int arg = in.nextInt();
				int pos = in.nextInt();
				System.out.println(cmd + " " + arg + " " + pos);
				
				if (cmd.equals("add")) {
					sll.add(arg);
				}
				else if (cmd.equals("addp")) {
					try {
						sll.add(pos,arg);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
				}
				else if (cmd.equals("get")) {
					try {
						System.out.println(sll.get(arg));
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				else if (cmd.equals("indexOf")) {
					try {
						System.out.println(sll.indexOf(arg));
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				else if (cmd.equals("contains")) {
					System.out.println(sll.contains(arg));
				} 
				else if (cmd.equals("remove")) {
					try {
						sll.remove(arg);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				else if (cmd.equals("set")) {
					try {
						sll.set(pos,arg);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				else if (cmd.equals("print")) {
					System.out.println(sll);
				}

				in.nextLine(); // discard rest of line
			}
			
		}	

	}
	
	
	
	static void generate() {
		
		Random generator = new Random();
		
		String[] cmds = {
				"add","addp","get","indexOf", "contains",
				"remove","set","print"
				
		};
		
		for (int line = 1; line <= 100; line++) {
			// randomly generate idx for command
			int idx = generator.nextInt(8);
			
			// randomly generate arguments
			int arg1 = generator.nextInt(100);
			int arg2 = generator.nextInt(10);
			System.out.println(cmds[idx] + " " + arg1 + " " + arg2);
		}
				
	}

}
