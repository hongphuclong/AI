package Lab4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class AdmissibleH {
	public boolean isAdmissibleH(Node root, String goal) {
		// Create a map to store the true cost h*(n) for each node
		HashMap<String, Double> trueCostMap = new HashMap<>();

		// Initialize the true cost of the goal node as 0
		trueCostMap.put(goal, 0.0);

		// Create a priority queue for A* search
		PriorityQueue<Node> priorityQueue = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node node1, Node node2) {
				// Compare nodes based on the sum of their g(n) and the true cost
				double f1 = node1.getG() + trueCostMap.get(node1.getLabel());
				double f2 = node2.getG() + trueCostMap.get(node2.getLabel());
				return Double.compare(f1, f2);
			}
		});

		// Add the root node to the priority queue
		root.setG(0);
		priorityQueue.add(root);

		while (!priorityQueue.isEmpty()) {
			// Remove the node with the lowest f(n) value from the priority queue
			Node currentNode = priorityQueue.poll();

			// Update the true cost h*(n) for the current node
			trueCostMap.put(currentNode.getLabel(), currentNode.getG() + currentNode.getH());

			// Check if h(n) is greater than or equal to 0
			if (currentNode.getH() < 0) {
				return false; // Heuristic is not admissible
			}

			// Check h(n) <= h*(n) for all children
			for (Edge edge : currentNode.getChildren()) {
				Node child = edge.getEnd();
				double hN = child.getH();
				double hStarN = trueCostMap.get(child.getLabel()) - child.getG();

				if (hN > hStarN) {
					return false; // Heuristic is not admissible
				}

				// Update the cost g(n) for the child
				child.setG(currentNode.getG() + edge.getWeight());

				// Add the child to the priority queue for further exploration
				priorityQueue.add(child);
			}
		}

		// If the loop completes without finding any violations, the heuristic is admissible
		return true;
	}

}