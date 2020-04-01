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
import chess.domain.board.BoardFactory;
import chess.domain.position.Position;

class KnightTest {
	static Stream<Arguments> generatePositions() {
		return Stream.of(
			Arguments.of(B1, D2, false),
			Arguments.of(B1, C3, true),
			Arguments.of(B1, B3, false),
			Arguments.of(G1, E2, true)
		);
	}

	@ParameterizedTest
	@MethodSource("generatePositions")
	void findMovablePositionsTest(Position currentPosition, Position destination, boolean expect) {
		Map<Position, Piece> pieces = new HashMap<>();
		pieces.putAll(BoardFactory.initializePawn(pieces));
		pieces.put(E2, new Pawn(Color.BLACK));
		pieces.put(B1, new Knight(Color.WHITE));
		pieces.put(G1, new Knight(Color.WHITE));
		Board board = new Board(pieces);
		Piece knight = board.findPieceBy(currentPosition);

		Set<Position> positions = knight.findMovablePositions(currentPosition, board::findPieceBy);
		System.out.println(positions);
		assertThat(positions.contains(destination)).isEqualTo(expect);
	}
}