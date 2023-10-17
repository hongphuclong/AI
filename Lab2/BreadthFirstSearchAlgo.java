package Lab2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearchAlgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		Queue<Node> frontier = new LinkedList<Node>();
		List<Node> explored = new ArrayList<Node>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			explored.add(root);
			if(current.getLabel().equals(goal))
				return current;
			List<Node> list = current.getChildrenNodes();
			for (int i = 0; i <current.getChildrenNodes().size(); i++) {
				Node node = list.get(i);
				if(!explored.contains(node) && !frontier.contains(node)) {
					node.setParent(current);
					frontier.add(list.get(i));
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		boolean started = false;
		Queue<Node> frontier = new LinkedList<>();
		frontier.add(root);
		List<Node> exployred = new ArrayList<Node>();
		while(!frontier.isEmpty()) {
			Node current = frontier.poll();
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
		Queue<Node> frontier = new LinkedList<Node>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node node = frontier.poll();
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
		Queue<Node> frontier = new LinkedList<>();
		frontier.add(root);
		while(!frontier.isEmpty()) {
			Node current = frontier.poll();
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
					if(!frontier.contains(node) ) 
						frontier.add(node);
					node.setParent(current);
				}
			}
		}
		return null;
	}


}