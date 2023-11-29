package lab6;

import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node initial = new Node();
		initial.generateBoard();
		initial.displayBoard();
		System.out.println("----------------");
//		System.out.println("Initial State");
//		System.out.println(initial.getH());
		List<Node> list = initial.generateAllCandidates();
//		System.out.println(initial.getBestCandidate(list).getH());
//		for (Node node : list) {
//			System.out.println("-------------------------");
//			node.displayBoard();
//		}
//		System.out.println("Select Random");
//		Node n = initial.selectNextRandomCandidate();
//		n.displayBoard();
//		HillClimbing hc = new HillClimbing();
//		Node n = hc.execute(initial);
//		Node n1 = hc.executeHillClimbingWithRandomRestart(initial);
//		n.displayBoard();
		SA sa = new SA();
		Node saA = sa.execute(initial);
		saA.displayBoard();
	}

}
