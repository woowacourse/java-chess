package chess.domain.board;

import static chess.domain.board.Rank.EIGHT;
import static chess.domain.board.Rank.ONE;
import static chess.domain.board.Rank.SEVEN;
import static chess.domain.board.Rank.TWO;
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
	@ValueSource(strings = {"A", "B", "C", "D", "E", "F", "G", "H"})
	void checkStartPawn(File file) {
		assertThat(board.get(Position.of(TWO, file))).isInstanceOf(Pawn.class);
		assertThat(board.get(Position.of(SEVEN, file))).isInstanceOf(Pawn.class);
	}

	@ParameterizedTest
	@MethodSource("createPositionAndPiece")
	void checkStartSpecialPiece(File file, Class<Piece> piece) {
		assertThat(board.get(Position.of(ONE, file))).isInstanceOf(piece);
		assertThat(board.get(Position.of(EIGHT, file))).isInstanceOf(piece);
	}

	private static Stream<Arguments> createPositionAndPiece() {
		return Stream.of(
				Arguments.of("A", Rook.class),
				Arguments.of("B", Knight.class),
				Arguments.of("C", Bishop.class),
				Arguments.of("D", Queen.class),
				Arguments.of("E", King.class),
				Arguments.of("F", Bishop.class),
				Arguments.of("G", Knight.class),
				Arguments.of("H", Rook.class)
		);
	}
}
