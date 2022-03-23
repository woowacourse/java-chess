package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {

	@ParameterizedTest
	@CsvSource(value = {"6:4", "5:4"}, delimiter = ':')
	@DisplayName("블랙 폰은 처음 움직일 때만 최대 2칸 남쪽으로 움직일 수 있다.")
	void moveBlackPawnFirst(int row, int column) {
		Pawn pawn = Pawn.createBlack(7,4);
		pawn.move(row, column);
		assertThat(pawn.isSamePosition(row, column)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"5:4:3:4"}, delimiter = ':')
	@DisplayName("블랙 폰은 처음 움직이는게 아니면 최대 1칸 남쪽으로 움직일 수 있다.")
	void moveBlackPawnNotFirst(int rowFirst, int columnFirst, int rowSecond, int columnSecond) {
		Pawn pawn = Pawn.createBlack(7,4);
		pawn.move(rowFirst, columnFirst);
		assertThatThrownBy(() -> pawn.move(rowSecond, columnSecond))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"6:3", "6:5"}, delimiter = ':')
	@DisplayName("블랙 폰은 남동 남서 대각선으로 1칸 이동할 수 있다.")
	void moveBlackPawnDiagonal (int row, int column) {
		Pawn pawn = Pawn.createBlack(7,4);
		pawn.move(row, column);
		assertThat(pawn.isSamePosition(row, column)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"4:4", "3:4"}, delimiter = ':')
	@DisplayName("화이트 폰은 처음 움직일 때만 최대 2칸 북쪽으로 움직일 수 있다.")
	void moveWhitePawnFirst(int row, int column) {
		Pawn pawn = Pawn.createWhite(2,4);
		pawn.move(row, column);
		assertThat(pawn.isSamePosition(row, column)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"4:4:6:4"}, delimiter = ':')
	@DisplayName("화이트 폰은 처음 움직이는게 아니면 최대 1칸 북쪽으로 움직일 수 있다.")
	void moveWhitePawnNotFirst(int rowFirst, int columnFirst, int rowSecond, int columnSecond) {
		Pawn pawn = Pawn.createWhite(2,4);
		pawn.move(rowFirst, columnFirst);
		assertThatThrownBy(() -> pawn.move(rowSecond, columnSecond))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"3:3", "3:5"}, delimiter = ':')
	@DisplayName("화이트 폰은 북동 북서 대각선으로 1칸 이동할 수 있다.")
	void moveWhitePawnDiagonal (int row, int column) {
		Pawn pawn = Pawn.createWhite(2,4);
		pawn.move(row, column);
		assertThat(pawn.isSamePosition(row, column)).isTrue();
	}
}