package chess.domain.piece;

public enum Team {
	WHITE,
	BLACK,
	NEUTRALITY;

	public boolean isBlack() {
		return this == Team.BLACK;
	}
}
