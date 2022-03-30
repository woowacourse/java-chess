package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

class BoardInitializerTest {

	private final Map<Position, Piece> board = BoardInitializer.createBoard();

	@DisplayName("체스판은 64칸이다.")
	@Test
	void chess_board_size_64() {
		//then
		assertThat(board).hasSize(64);
	}

	@MethodSource("provideRookPosition")
	@ParameterizedTest(name = "{0}에 룩이 있다")
	void check_rook_positions(Position expectedPosition) {
		//then
		assertThat(board.get(expectedPosition))
			.isInstanceOf(Rook.class);
	}

	@MethodSource("provideBishopPosition")
	@ParameterizedTest(name = "{0}에 비숍이 있다")
	void check_bishop_positions(Position expectedPosition) {
		//then
		assertThat(board.get(expectedPosition))
			.isInstanceOf(Bishop.class);
	}

	@MethodSource("provideKnightPosition")
	@ParameterizedTest(name = "{0}에 나이트가 있다")
	void check_knight_positions(Position expectedPosition) {
		//then
		assertThat(board.get(expectedPosition))
			.isInstanceOf(Knight.class);
	}

	@MethodSource("provideQueenPosition")
	@ParameterizedTest(name = "{0}에 퀸이 있다")
	void check_queen_positions(Position expectedPosition) {
		//then
		assertThat(board.get(expectedPosition))
			.isInstanceOf(Queen.class);
	}

	@MethodSource("provideKingPosition")
	@ParameterizedTest(name = "{0}에 킹이 있다")
	void check_king_positions(Position expectedPosition) {
		//then
		assertThat(board.get(expectedPosition))
			.isInstanceOf(King.class);
	}

	@MethodSource("providePawnPosition")
	@ParameterizedTest(name = "{0}에 폰이 있다")
	void check_pawn_positions(Position expectedPosition) {
		//then
		assertThat(board.get(expectedPosition))
			.isInstanceOf(Pawn.class);
	}

	@MethodSource("provideWhiteRow")
	@ParameterizedTest(name = "{0}행의 모든 말은 white이다.")
	void check_color_white(Row row) {
		//then
		for (Column column : Column.values()) {
			Piece piece = board.get(Position.of(column, row));
			assertThat(piece.isBlack()).isFalse();
		}
	}

	@MethodSource("provideBlackRow")
	@ParameterizedTest(name = "{0}행의 모든 말은 black이다.")
	void check_color_black(Row row) {
		//then
		for (Column column : Column.values()) {
			Piece piece = board.get(Position.of(column, row));
			assertThat(piece.isBlack()).isTrue();
		}
	}

	private static Stream<Arguments> provideRookPosition() {
		return Stream.of(
			Arguments.of(Position.of(Column.A, Row.ONE)),
			Arguments.of(Position.of(Column.H, Row.ONE)),
			Arguments.of(Position.of(Column.A, Row.EIGHT)),
			Arguments.of(Position.of(Column.H, Row.EIGHT))
		);
	}

	private static Stream<Arguments> provideBishopPosition() {
		return Stream.of(
			Arguments.of(Position.of(Column.C, Row.ONE)),
			Arguments.of(Position.of(Column.F, Row.ONE)),
			Arguments.of(Position.of(Column.C, Row.EIGHT)),
			Arguments.of(Position.of(Column.F, Row.EIGHT))
		);
	}

	private static Stream<Arguments> provideKnightPosition() {
		return Stream.of(
			Arguments.of(Position.of(Column.B, Row.ONE)),
			Arguments.of(Position.of(Column.G, Row.ONE)),
			Arguments.of(Position.of(Column.B, Row.EIGHT)),
			Arguments.of(Position.of(Column.G, Row.EIGHT))
		);
	}

	private static Stream<Arguments> provideQueenPosition() {
		return Stream.of(
			Arguments.of(Position.of(Column.D, Row.ONE)),
			Arguments.of(Position.of(Column.D, Row.EIGHT))
		);
	}

	private static Stream<Arguments> provideKingPosition() {
		return Stream.of(
			Arguments.of(Position.of(Column.E, Row.ONE)),
			Arguments.of(Position.of(Column.E, Row.EIGHT))
		);
	}

	private static Stream<Arguments> providePawnPosition() {
		return Stream.of(
			Arguments.of(Position.of(Column.A, Row.TWO)),
			Arguments.of(Position.of(Column.B, Row.TWO)),
			Arguments.of(Position.of(Column.C, Row.TWO)),
			Arguments.of(Position.of(Column.D, Row.TWO)),
			Arguments.of(Position.of(Column.E, Row.TWO)),
			Arguments.of(Position.of(Column.F, Row.TWO)),
			Arguments.of(Position.of(Column.G, Row.TWO)),
			Arguments.of(Position.of(Column.H, Row.TWO)),
			Arguments.of(Position.of(Column.A, Row.SEVEN)),
			Arguments.of(Position.of(Column.B, Row.SEVEN)),
			Arguments.of(Position.of(Column.C, Row.SEVEN)),
			Arguments.of(Position.of(Column.D, Row.SEVEN)),
			Arguments.of(Position.of(Column.E, Row.SEVEN)),
			Arguments.of(Position.of(Column.F, Row.SEVEN)),
			Arguments.of(Position.of(Column.G, Row.SEVEN)),
			Arguments.of(Position.of(Column.H, Row.SEVEN))
		);
	}

	private static Stream<Arguments> provideWhiteRow() {
		return Stream.of(
			Arguments.of(Row.ONE),
			Arguments.of(Row.TWO)
		);
	}

	private static Stream<Arguments> provideBlackRow() {
		return Stream.of(
			Arguments.of(Row.SEVEN),
			Arguments.of(Row.EIGHT)
		);
	}
}