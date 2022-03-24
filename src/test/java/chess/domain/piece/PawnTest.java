package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.Position;

class PawnTest {

	private final Pawn whitePawn = Pawn.createWhite();
	private final Pawn blackPawn = Pawn.createBlack();

	@ParameterizedTest
	@CsvSource(value = {"6:4", "5:4"}, delimiter = ':')
	@DisplayName("블랙 폰은 처음 움직일 때만 최대 2칸 남쪽으로 움직일 수 있다.")
	void moveBlackPawnFirst(int row, int column) {
		assertThat(blackPawn.isMovable(
			new Position(7, 4),
			new Position(row, column))
		).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"3:4", "2:4", "1:4", "1:7", "6:4"}, delimiter = ':')
	@DisplayName("블랙 폰은 처음 움직이는게 아니면 최대 1칸 남쪽으로 움직일 수 있다.")
	void moveBlackPawnNotFirst(int row, int column) {
		assertThat(blackPawn.isMovable(
			new Position(5, 4),
			new Position(row, column))
		).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"4:3", "4:5"}, delimiter = ':')
	@DisplayName("블랙 폰은 남동 남서 대각선으로 1칸 이동할 수 있다.")
	void moveBlackPawnDiagonal (int row, int column) {
		assertThat(blackPawn.isMovable(
			new Position(5, 4),
			new Position(row, column))
		).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"3:4", "4:4"}, delimiter = ':')
	@DisplayName("화이트 폰은 처음 움직일 때만 최대 2칸 북쪽으로 움직일 수 있다.")
	void moveWhitePawnFirst(int row, int column) {
		assertThat(whitePawn.isMovable(
			new Position(2, 4),
			new Position(row, column))
		).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"5:4", "6:4", "2:4", "5:6", "1:7"}, delimiter = ':')
	@DisplayName("화이트 폰은 처음 움직이는게 아니면 최대 1칸 북쪽으로 움직일 수 있다.")
	void moveWhitePawnNotFirst(int row, int column) {
		assertThat(whitePawn.isMovable(
			new Position(3, 4),
			new Position(row, column))
		).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"3:3", "3:5"}, delimiter = ':')
	@DisplayName("화이트 폰은 북동 북서 대각선으로 1칸 이동할 수 있다.")
	void moveWhitePawnDiagonal (int row, int column) {
		assertThat(whitePawn.isMovable(
			new Position(2, 4),
			new Position(row, column))
		).isTrue();
	}
}