package lab6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Node {
	public static final int N = 8;
	private Queen[] state;

	public Node() {
		// generateBoard();
		state = new Queen[N];
	}

	public Node(Queen[] state) {
		this.state = new Queen[N];
		for (int i = 0; i < state.length; i++) {
			this.state[i] = new Queen(state[i].getRow(), state[i].getColumn());
		}
	}

	public Node(Node n) {
		this.state = new Queen[N];
		for (int i = 0; i < N; i++) {
			Queen qi = n.state[i];
			this.state[i] = new Queen(qi.getRow(), qi.getColumn());
		}
	}

	public void generateBoard() {
		Random random = new Random();
		for (int i = 0; i < N; i++) {
			state[i] = new Queen(random.nextInt(N), i);
		}
	}

	public int getH() {
		int heuristic = 0;
		for (int i = 0; i < N - 1; i++) {
			for (int j = i+1; j < N; j++) {
				if (state[i].isConflict(state[j])) heuristic++;
			}
		}
		return heuristic;
	}

	public List<Node> generateAllCandidates() {
		List<Node> result = new ArrayList<Node>();		
        for (int i = 0; i < N; i++) {
        	Node node = new Node(this.state);
        	node.state[i].move();
        	result.add(node);
        }
		return result;
	}

	public Node selectNextRandomCandidate() {
		Random random = new Random();
		int row = random.nextInt(N);
		int index = random.nextInt(N);
		Node n = new Node(state);
		n.state[index].setRow(row);
		return new Node(n);
	}

	public void displayBoard() {
		int[][] board = new int[N][N];
		// set queen position on the board
		for (int i = 0; i < N; i++) {
			board[state[i].getRow()][state[i].getColumn()] = 1;
		}
		// print board
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 1) {
					System.out.print("Q" + " ");
				} else {
					System.out.print("-" + " ");
				}
			}
			System.out.println();
		}
	}

	public Node getBestCandidate(List<Node> list) {
		list.sort((o1, o2) -> o1.getH() - o2.getH());
		return list.get(0);
	}
}
