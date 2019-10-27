import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class simQueue {
	public static Random r = new Random(97);
	public static double meanT = 0;
	public static int maxWaitTime = 0;
	public static int minWaitTime = 0;
	public static int aver = 0;
	public static int inService = 0;
	public static int completedService = 0;

	public static void main(String[] args) {

		Queue<Customer> line = new LinkedList<Customer>();
		ArrayList<Integer> servers = new ArrayList<Integer>();

		int numServers = displayMenu(); // Displays menu and gets number of servers
		
		while (numServers > 0) { // Adds specific amount servers
			servers.add(null);
			numServers--;
		}

		for (int i = 0; i < 20; i++) { // Each loop is 1 tick.
			int numCust = getPoissonRandom(2); // Number of customers

			for (int j = 0; j < servers.size(); j++) { // Releases a customer when done
				if (servers.isEmpty() || servers.get(j) == null)
					break;
				else if (servers.get(j) == 0) { // Remove customer and service time to servers
					Customer c = line.remove();
					servers.set(j, c.serviceTime);
				}
				servers.set(j, servers.get(j) - 1); // Decrement service timeS
			}

			for (Customer c : line) { // Adds time to remaining customers
				c.setWaitTime(c.getWaitTime() + 1);
			}

			for (int j = 0; j < numCust; j++) { // Adds customers to line
				line.add(new Customer(getPoissonRandom(meanT)));
			}

			System.out.println(line.size() + " Customers in queue:");

			for (int j = 0; j < line.size(); j++) {
				Customer c = line.remove();
				max(c.waitTime);
				min(c.waitTime);
				aver += c.waitTime;
				line.add(c);
			}
			System.out.println("Total Wait Time: " + aver);
			System.out.println("Wait Time: " + minWaitTime + ", " + aver / line.size() + ", " + maxWaitTime);
			System.out.println("======================");

		}

	}

	public static int getPoissonRandom(double mean) { // Poisson random number generator
		double L = Math.exp(-mean);
		int k = 0;
		double p = 1.0;
		do {
			p = p * r.nextDouble();
			k++;
		} while (p > L);
		return k - 1;

	}

	public static int displayMenu() { // Displays menu
		Scanner input = new Scanner(System.in);

		System.out.println("Select an option:");
		System.out.println("1. Heavy Demand");
		System.out.println("2. Low Demand \n >");
		int choice = input.nextInt();

		if (choice == 1) {
			meanT = 2;
			return 7;
		} else {
			meanT = 0.25;
			return 4;
		}

	}

	public static void max(int c) {
		int min = c;
		if (min > maxWaitTime)
			maxWaitTime = min;
	}

	public static void min(int c) {
		int max = c;
		if (max < minWaitTime)
			minWaitTime = max;
	}
}
