package lab6;


public class SA {
	public Node execute (Node initial) {
		Node current = initial;
        Node next = null;
        int T = 1000;

        for (int i = 0; i < T; i++) {
            next = current.selectNextRandomCandidate();

            int deltaE = next.getH() - current.getH();

            if (deltaE < 0 || Math.random() < Math.exp(-deltaE / T)) {
                current = new Node(next);
            }

            T *= 0.95;
        }

        return current;
	}
}
