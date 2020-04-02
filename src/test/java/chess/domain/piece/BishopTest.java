package chess.domain.piece;

import static chess.domain.position.PositionFixture.*;
import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.board.Board;
import chess.domain.position.Position;

class BishopTest {
	static Stream<Arguments> generatePositions() {
		return Stream.of(
			Arguments.of(C1, A3, false),
			Arguments.of(C1, B2, true),
			Arguments.of(C1, H6, true),
			Arguments.of(C1, B1, false),
			Arguments.of(C1, C2, false),
			Arguments.of(C1, D1, false),
			Arguments.of(C1, C1, false)
		);
	}

	@ParameterizedTest
	@MethodSource("generatePositions")
	void findMovablePositionsTest(Position currentPosition, Position destination, boolean expect) throws SQLException {
		Map<Position, Piece> pieces = new HashMap<>();
		pieces.put(B2, new Bishop(Color.BLACK));
		pieces.put(C1, new Bishop(Color.WHITE));
		Board board = new Board(pieces);
		Piece bishop = board.findPieceBy(currentPosition);

		Set<Position> positions = bishop.findMovablePositions(currentPosition, board.getPieces());
		assertThat(positions.contains(destination)).isEqualTo(expect);
	}
}