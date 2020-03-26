package chess.domain.piece;

import static chess.domain.position.PositionFixture.*;
import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.position.Position;

class KingTest {
	static Stream<Arguments> generatePositions() {
		return Stream.of(
			Arguments.of(E1, D2, true),
			Arguments.of(E1, D1, false),
			Arguments.of(E1, F1, true)
		);
	}

	@Test
	public void initTest() {
		assertThat(new King("k", Color.WHITE)).isInstanceOf(King.class);
	}

	@ParameterizedTest
	@MethodSource("generatePositions")
	void findMovablePositionsTest(Position currentPosition, Position destination, boolean expect) {
		Map<Position, Piece> pieces = new HashMap<>();
		pieces.put(D2, new Bishop("b", Color.BLACK));
		Board board = new Board(BoardFactory.initializeKingQueen(pieces));
		Piece king = board.findPieceBy(currentPosition);

		Set<Position> positions = king.findMovablePositions(currentPosition, board);
		assertThat(positions.contains(destination)).isEqualTo(expect);
	}
}