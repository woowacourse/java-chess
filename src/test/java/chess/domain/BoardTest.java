package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BoardTest {

	@ParameterizedTest
	@CsvSource(value = {"1:5:♚", "1:8:♜"}, delimiter = ':')
	@DisplayName("체스판 좌표로 체스말을 찾는다.")
	void findSymbolAt(int row, int column, String result) {
		Board board = new Board();
		String symbol = board.findSymbolAt(row, column);
		assertThat(symbol).isEqualTo(result);
	}

	@ParameterizedTest
	@CsvSource(value = {"3:3", "4:7"}, delimiter = ':')
	@DisplayName("체스판 좌표에 체스말이 없으면 예외가 발생한다.")
	void findSymbolAtException(int row, int column) {
		Board board = new Board();
		assertThatThrownBy(() -> board.findSymbolAt(row, column))
			.isInstanceOf(IllegalArgumentException.class);
	}
}