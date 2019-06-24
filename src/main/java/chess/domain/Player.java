package chess.domain;

public enum Player {
	WHITE,
	BLACK;

	public Player changePlayer() {
		if(this == WHITE) {
			return BLACK;
		}
		return WHITE;
	}
}
