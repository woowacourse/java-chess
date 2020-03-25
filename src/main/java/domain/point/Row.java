package domain.point;

public enum Row {
	ONE("1", 7),
	TWO("2", 6),
	THREE("3", 5),
	FOUR("4", 4),
	FIVE("5", 3),
	SIX("6", 2),
	SEVEN("7", 1),
	EIGHT("8", 0);

	private String row;
	private int index;

	Row(String row, int index) {
		this.row = row;
		this.index = index;
	}
}
