package Lab2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearchAlgo implements ISearchAlgo{

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		Stack<Node> frontier = new Stack<Node>();
		List<Node> explored = new ArrayList<Node>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node current = frontier.pop();
			explored.add(root);
			if(current.getLabel().equals(goal))
				return current;
			List<Node> list = current.getChildrenNodes();
			for (int i = list.size() -1 ; i >= 0; i--) {
				Node node = list.get(i);
				if(!explored.contains(node) && !frontier.contains(node)) {
					node.setParent(current);
					frontier.add(node);
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		boolean started = false;
		Stack<Node> frontier = new Stack<Node>();
		frontier.add(root);
		List<Node> exployred = new ArrayList<Node>();
		while(!frontier.isEmpty()) {
			Node current = frontier.pop();
			if(current.getLabel().equals(start)) {
				started = true;
				frontier.clear();
				exployred.clear();
				current.setParent(null);
			}
			if(current.getLabel().equals(goal) && started) {
				return current;
			}else {
				exployred.add(current);
				List<Node> children = current.getChildrenNodes();
				for (Node node : children) {
					if(!frontier.contains(node) && !exployred.contains(node)) {
						frontier.add(node);
						node.setParent(current);
					}
				}
			}
		}

		return null;
	}

	@Override
	public Node executeTreeSearch(Node root, String goal) {
		// TODO Auto-generated method stub
		Stack<Node> frontier = new Stack<Node>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node node = frontier.pop();
			if (node.getLabel().equals(goal)) {
				return node;
			}
			for (Node child : node.getChildrenNodes()) {
				if (!frontier.contains(child)) {
					frontier.add(child);
					child.setParent(node);
				}
			}
		}
		return null;
	}

	@Override
	public Node executeTreeSearch(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		boolean started = false;
		Stack<Node> frontier = new Stack<Node>();
		frontier.add(root);
		while(!frontier.isEmpty()) {
			Node current = frontier.pop();
			if(current.getLabel().equals(start)) {
				started = true;
				frontier.clear();

				current.setParent(null);
			}
			if(current.getLabel().equals(goal) && started) {
				return current;
			}else {

				List<Node> children = current.getChildrenNodes();
				for (Node node : children) {
					if(!frontier.contains(node) ) {
						frontier.add(node);
						node.setParent(current);
					}
				}
			}
		}

		return null;
	}

}