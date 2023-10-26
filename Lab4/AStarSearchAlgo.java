package Lab4;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class AStarSearchAlgo implements IInformedSearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		Queue<Node> queue = new PriorityQueue<Node>(new NodeComparatorByHnAndGn());
		queue.add(root);
		root.setG(0);
		while (!queue.isEmpty()) {
			Node current = queue.poll();
			if (current.getLabel() == goal)
				return current;
			List<Edge> listEdges = current.getChildren();
			for (Edge edge : listEdges) {
				if (!queue.contains(edge.getEnd())) {
					edge.getEnd().setParent(edge.getBegin());
					edge.getEnd().setG(edge.getBegin().getG() + edge.getWeight());
					queue.add(edge.getEnd());
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		boolean starting = false;
		Queue<Node> queue = new PriorityQueue<Node>(new NodeComparatorByHnAndGn());
		queue.add(root);
		while (!queue.isEmpty()) {
			Node current = queue.poll();
			if (current.getLabel() == start) {
				starting = true;
				current.setParent(null);
				current.setG(0);
				queue.clear();
			}
			if (current.getLabel() == goal && starting)
				return current;
			List<Edge> listEdges = current.getChildren();
			for (Edge edge : listEdges) {
				if (!queue.contains(edge.getEnd())) {
					edge.getEnd().setParent(edge.getBegin());
					edge.getEnd().setG(edge.getBegin().getG() + edge.getWeight());
					queue.add(edge.getEnd());
				}
			}
		}
		return null;
	}
}