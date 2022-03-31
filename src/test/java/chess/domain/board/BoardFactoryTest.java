package chess.domain.board;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class BoardFactoryTest {

	private final Map<Position, Piece> board = BoardFactory.initiate();

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8})
	void checkStartPawn(int column) {
		assertThat(board.get(Position.of(2, column))).isInstanceOf(Pawn.class);
		assertThat(board.get(Position.of(7, column))).isInstanceOf(Pawn.class);
	}

	@ParameterizedTest
	@MethodSource("createPositionAndPiece")
	void checkStartSpecialPiece(int column, Class<Piece> piece) {
		assertThat(board.get(Position.of(1, column))).isInstanceOf(piece);
		assertThat(board.get(Position.of(8, column))).isInstanceOf(piece);
	}

	private static Stream<Arguments> createPositionAndPiece() {
		return Stream.of(
				Arguments.of(1, Rook.class),
				Arguments.of(2, Knight.class),
				Arguments.of(3, Bishop.class),
				Arguments.of(4, Queen.class),
				Arguments.of(5, King.class),
				Arguments.of(6, Bishop.class),
				Arguments.of(7, Knight.class),
				Arguments.of(8, Rook.class)
		);
	}
}
