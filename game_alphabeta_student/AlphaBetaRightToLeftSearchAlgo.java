package game_alphabeta_student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlphaBetaRightToLeftSearchAlgo implements ISearchAlgo {

	@Override
	public void execute(Node node) {
		int result = maxValue(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
		node.setValue(result);
	}

	public int maxValue(Node node, int alpha, int beta) {
		if (node.isTerminal()) {
			return node.getValue();
		}

		int v = Integer.MIN_VALUE;
		List<Node> reversedChildren = new ArrayList<>(node.getChildren());
		Collections.reverse(reversedChildren);

		for (Node child : reversedChildren) {
			int childValue = minValue(child, alpha, beta);
			v = Math.max(v, childValue);

			if (v >= beta) {
				return v;
			}

			alpha = Math.max(alpha, v);
		}
		return v;
	}

	public int minValue(Node node, int alpha, int beta) {
		if (node.isTerminal()) {
			return node.getValue();
		}

		int v = Integer.MAX_VALUE;
		List<Node> reversedChildren = new ArrayList<>(node.getChildren());
		Collections.reverse(reversedChildren);

		for (Node child : reversedChildren) {
			int childValue = maxValue(child, alpha, beta);
			v = Math.min(v, childValue);

			if (v <= alpha) {
				return v;
			}

			beta = Math.min(beta, v);
		}
		return v;
	}
}
