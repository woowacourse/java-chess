package chess.domain.piece;

public enum Team {
	BLACK("BLACK"),
	WHITE("WHITE"),
	NONE("NONE");

    private final String name;

    Team(String name) {
        this.name = name;
    }

	public boolean isSame(Team team) {
		return this == team;
	}

	public boolean isNotNone() {
		return this != NONE;
	}

    public String getName() {
        return name;
    }
}
