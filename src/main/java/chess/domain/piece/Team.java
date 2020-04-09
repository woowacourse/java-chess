package chess.domain.piece;

import java.util.Arrays;

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

	public static Team of(String team) {
		return Arrays.stream(values())
			.filter(val -> val.name().equals(team))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("유효하지 않는 입력 값입니다."));
	}

	public String getTeam() {
		return name().toLowerCase();
	}
}
