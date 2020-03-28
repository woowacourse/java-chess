package chess.domain;

public enum Team {
	BLACK("BLACK"),
	WHITE("WHITE"),
	BLANK("BLANK");

	private final String name;

	Team(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
