package chess.domain.piece;

public enum Color {
	WHITE("white"),
	BLACK("black"),
	BLANK("blank");

	private final String name;

	Color(String name) {
		this.name = name;
	}

	public Color changeColor() { // TODO: 2020/03/25 테스트 블랭크일 경우 블랭크 돌리게 수정
		if (isWhite()) {
			return BLACK;
		}
		return WHITE;
	}

	public boolean isWhite() {
		return WHITE.equals(this);
	}

	public boolean isBlack() {
		return BLACK.equals(this);
	}

	public boolean isBlank() {
		return BLANK.equals(this);
	}

	public boolean isSame(Color color) {
		return this.equals(color);
	}

	public String getName() {
		return name;
	}
}
