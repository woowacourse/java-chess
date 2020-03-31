package chess.domain.piece;

import static chess.domain.position.PositionFixture.*;
import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.board.Board;
import chess.domain.position.Position;

class QueenTest {
	static Stream<Arguments> generatePositions() {
		return Stream.of(
			Arguments.of(D1, B1, true),
			Arguments.of(D1, B3, true),
			Arguments.of(D1, A4, false),
			Arguments.of(D1, A2, false),
			Arguments.of(D1, F1, false),//
			Arguments.of(D1, F3, false),
			Arguments.of(D1, E1, false),//
			Arguments.of(D1, H1, false)//
		);
	}

	@ParameterizedTest
	@MethodSource("generatePositions")
	void findMovablePositionsTest(Position currentPosition, Position destination, boolean expect) {
		Map<Position, Piece> pieces = new HashMap<>();
		pieces.put(B1, new Piece(Color.BLACK, PieceType.BISHOP));
		pieces.put(B3, new Piece(Color.BLACK, PieceType.BISHOP));
		pieces.put(F3, new Piece(Color.WHITE, PieceType.BISHOP));
		pieces.put(D1, new Piece(Color.WHITE, PieceType.QUEEN));
		pieces.put(E1, new Piece(Color.WHITE, PieceType.KING));
		Board board = new Board(pieces);
		Piece queen = board.findPieceBy(currentPosition);

		Set<Position> positions = queen.findMovablePositions(currentPosition, board::findPieceBy);
		assertThat(positions.contains(destination)).isEqualTo(expect);
	}
}