package lab6;

public class HillClimbing {
	int stepClimbed = 0;
	int stepClimbedAfterRandomRestart = 0;
	int randomRestarts = 0;
	public Node execute(Node inititalState) {
		Node current = inititalState;
		Node neighbor = null;
		while (true) {
			stepClimbed++;
			stepClimbedAfterRandomRestart++;
			neighbor = current.getBestCandidate(current.generateAllCandidates());
			if (neighbor.getH() < current.getH()) {
				current = neighbor;
			} else {
				return current;
			}
		}
	}

	public Node executeHillClimbingWithRandomRestart(Node initialState) {
		Node goalState = execute(initialState);
		while(goalState.getH() != 0) {
			goalState.generateBoard();
			randomRestarts++;
			stepClimbedAfterRandomRestart = 0;
			goalState = execute(goalState);
		}
		System.out.println("StepClimbed: " + stepClimbed);
		System.out.println("StepClimbedAfterRandomRestart: " + stepClimbedAfterRandomRestart);
		System.out.println("RandomRestarts: " + randomRestarts);
		return goalState;
	}
}
