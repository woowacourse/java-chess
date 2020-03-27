package chess.domain;

public class Turn {
	private boolean isWhiteTurn;

	public Turn() {
		this.isWhiteTurn = true;
	}

	public boolean isWhiteTurn() {
		return isWhiteTurn;
	}

	public void changeTurn() {
		this.isWhiteTurn = !this.isWhiteTurn;
	}
}
