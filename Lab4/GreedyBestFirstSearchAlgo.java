package Lab4;

import java.util.PriorityQueue;
import java.util.Queue;


public class GreedyBestFirstSearchAlgo implements IInformedSearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		Queue<Node> queue = new PriorityQueue<Node>(new NodeComparatorByHn());
		queue.add(root);
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			if (current.getLabel() == goal) return current;
			for (Node node : current.getChildrenNodes()) {
				if (!queue.contains(node)) {
					node.setParent(current);
					queue.add(node);
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		boolean starting = false;
		Queue<Node> queue = new PriorityQueue<Node>(new NodeComparatorByHn());
		queue.add(root);
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			if(current.getLabel() == start) {
				starting = true;
				current.setParent(null);
				queue.clear();
			}
			if (current.getLabel() == goal && starting) return current;
			for (Node node : current.getChildrenNodes()) {
				if (!queue.contains(node)) {
					node.setParent(current);
					queue.add(node);
				}
			}
		}
		return null;
	}

}