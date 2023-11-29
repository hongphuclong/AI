package lab5;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class GreedySearchAlgo implements IPuzzleAlgo{

	@Override
	public Node execute(Puzzle model) {
		Queue<Node> frontier = new PriorityQueue<Node>((PuzzleUtils.HeuristicComparatorByH));
		List<Node> exployed = new ArrayList<Node>();
		frontier.add(model.getInitialState());
		exployed.add(model.getInitialState());
		model.getInitialState().setH(model.computeH2(model.getInitialState()));
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getH() == 0) return current;
			List<Node> listNodeState = model.getSuccessors(current);
			for (Node node : listNodeState) {
				if (!frontier.contains(node) && !exployed.contains(node)) {
					node.setH(model.computeH2(node));
					frontier.add(node);
					exployed.add(node);
				}
			}
		}
		return null;
	}
	

}
