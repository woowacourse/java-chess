package chess.domain.piece;

import static chess.domain.piece.PieceType.*;
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
import chess.domain.board.BoardFactory;
import chess.domain.position.Position;

class PawnTest {
	static Stream<Arguments> generatePositions() {
		return Stream.of(
			Arguments.of(B2, A3, true),
			Arguments.of(B2, C3, false),
			Arguments.of(A2, A3, false),
			Arguments.of(B2, B3, true),
			Arguments.of(C2, C3, false),
			Arguments.of(C2, C4, false),
			Arguments.of(A2, B3, false),
			Arguments.of(D2, D4, true)
		);
	}

	@ParameterizedTest
	@MethodSource("generatePositions")
	void findMovablePositionsTest(Position currentPosition, Position destination, boolean expect) {
		Map<Position, Piece> pieces = new HashMap<>();
		pieces.put(A3, new Piece(Color.BLACK, BISHOP));
		pieces.put(C3, new Piece(Color.WHITE, BISHOP));
		Board board = new Board(BoardFactory.initializePawn(pieces));
		Piece pawn = board.findPieceBy(currentPosition);

		Set<Position> positions = pawn.findMovablePositions(currentPosition, board::findPieceBy);
		assertThat(positions.contains(destination)).isEqualTo(expect);
	}
}