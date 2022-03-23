package chess.domain.board;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

@DisplayName("Board 테스트")
class BoardTest {

	@DisplayName("알맞은 개수의 말이 생성된다.")
	@ParameterizedTest(name = "{index} {displayName} piece={0} count={1}")
	@MethodSource("providePieceAndExpectedCount")
	void valid_Count(final Piece piece, final int expected) {
		Board board = Board.createInitialBoard();
		Map<Position, Piece> pieces = board.getPieces();
		final int actual = (int)pieces.values()
			.stream()
			.filter(piece::equals)
			.count();

		assertThat(actual).isEqualTo(expected);
	}

	private static Stream<Arguments> providePieceAndExpectedCount() {
		return Stream.of(
			Arguments.of(new Pawn(Color.BLACK), 8),
			Arguments.of(new Rook(Color.BLACK), 2),
			Arguments.of(new Bishop(Color.BLACK), 2),
			Arguments.of(new Knight(Color.BLACK), 2),
			Arguments.of(new King(Color.BLACK), 1),
			Arguments.of(new Queen(Color.BLACK), 1)
		);
	}
}