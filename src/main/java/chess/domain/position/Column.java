package chess.domain.position;

public enum Column {
	EIGHT("8", 8),
	SEVEN("7", 7),
	SIX("6", 6),
	FIVE("5", 5),
	FOUR("4", 4),
	THREE("3", 3),
	TWO("2", 2),
	ONE("1", 1);

	private final String name;
	private final int column;

	Column(String name, int column) {
		this.name = name;
		this.column = column;
	}

	public String getName() {
		return name;
	}
}
