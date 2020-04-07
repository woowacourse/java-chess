package domain.piece.position;

public enum Row {
	ONE("1",1,0),
	TWO("2",2,1),
	THREE("3",3,2),
	FOUR("4",4,3),
	FIVE("5",5,4),
	SIX("6",6,5),
	SEVEN("7",7,6),
	EIGHT("8",8,7);

	private String row;
	private int number;
	private int rankIndex;

	Row(String row, int number, int rankIndex) {
		this.row = row;
		this.number = number;
		this.rankIndex = rankIndex;
	}

	public int gap(Row row) {
		return row.number - this.number;
	}

	public int getNumber() {
		return number;
	}

	public int getRankIndex() {
		return rankIndex;
	}

	public String getRow() {
		return row;
	}
}
