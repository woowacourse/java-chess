package chess.domain.piece;

public enum Team {
	WHITE("white"),
	BLACK("black"),
	NEUTRALITY("neutrality");

	private final String value;

	Team(final String value) {
		this.value = value;
	}

	public boolean isBlack() {
		return this == Team.BLACK;
	}

	public boolean isWhite() {
		return this == Team.WHITE;
	}

	public String getValue() {
		return value;
	}
}
