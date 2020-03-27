package chess.domain;

public enum Team {
    BLACK(),
    WHITE(),
    BLANK();

	public boolean isNotWhite() {
		return (this == WHITE) == false;
	}

	public boolean isNotBlack() {
		return (this == BLACK) == false;
	}
}
