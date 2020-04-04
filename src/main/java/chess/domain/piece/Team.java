package chess.domain.piece;

public enum Team {
	BLACK, WHITE, NONE;

	public boolean isNotBlank() {
		return this != NONE;
	}

	public boolean isBlack() {
		return this == BLACK;
	}

	public boolean isWhite() {
		return this == WHITE;
	}

	public Team getOppositeTeam() {
		if (this == NONE) {
			throw new UnsupportedOperationException("해당 팀에서는 사용할 수 없는 연산입니다.");
		}
		if (this.isBlack()) {
			return WHITE;
		}
		return BLACK;
	}
}
