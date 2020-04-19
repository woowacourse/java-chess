package chess.domain.position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import chess.domain.chesspiece.ChessPiece;

public class Positions {
	private static final String HAVE_ROADBLOCK_MESSAGE = "이동 경로에 장애물이 있습니다.";

	private final List<Position> positions;

	public Positions(List<Position> positions) {
		this.positions = new ArrayList<>(positions);
	}

	public void validateCanMovePath(Function<Position, ChessPiece> function) {
		if (containsNotBlank(function)) {
			throw new IllegalArgumentException(HAVE_ROADBLOCK_MESSAGE);
		}
	}

	private boolean containsNotBlank(Function<Position, ChessPiece> function) {
		return positions.stream()
			.map(function)
			.anyMatch(chessPiece -> chessPiece.isNotBlankPiece());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Positions positions1 = (Positions)o;
		return positions.equals(positions1.positions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(positions);
	}
}
