package chess.domain.piece;

public enum Team {
	White,
	Black,
	NEUTRALITY;
	
	public boolean isBlack() {
		return this == Team.Black;
	}
}
