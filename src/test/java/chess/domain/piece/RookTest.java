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

class RookTest {
	static Stream<Arguments> generatePositions() {
		return Stream.of(
			Arguments.of(A1, A3, true),
			Arguments.of(A1, C1, true),
			Arguments.of(A1, B2, false),
			Arguments.of(A1, B3, false),
			Arguments.of(A1, A1, false),
			Arguments.of(A1, A8, true), //먹는것
			Arguments.of(A1, H1, false)
		);
	}

	@ParameterizedTest
	@MethodSource("generatePositions")
	void findMovablePositionsTest(Position currentPosition, Position destination, boolean expect) {
		Map<Position, Piece> pieces = new HashMap<>();
		pieces.put(A1, new Piece(Color.WHITE, PieceType.ROOK));
		pieces.put(A8, new Piece(Color.BLACK, PieceType.ROOK));
		pieces.put(H1, new Piece(Color.WHITE, PieceType.ROOK));
		Board board = new Board(pieces);

		Piece rook = board.findPieceBy(currentPosition);

		Set<Position> positions = rook.findMovablePositions(currentPosition, board::findPieceBy);
		assertThat(positions.contains(destination)).isEqualTo(expect);
	}
}
