package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {

	@ParameterizedTest
	@CsvSource(value = {"3:4", "4:5", "5:5", "5:4", "3:3", "4:3", "3:5", "5:3"}, delimiter = ':')
	@DisplayName("Queen을 이동시킨다.")
	void moveKing(int row, int column) {
		King king = King.createBlack(4,4);
		king.move(row, column);
		assertThat(king.isSamePosition(row, column)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"6:4", "6:5", "7:5", "2:4", "1:3", "4:1", "6:7", "1:2"}, delimiter = ':')
	@DisplayName("Queen을 이동시킨다.")
	void moveKingException(int row, int column) {
		King king = King.createBlack(4,4);
		assertThatThrownBy(() -> king.move(row, column))
			.isInstanceOf(IllegalArgumentException.class);
	}
}