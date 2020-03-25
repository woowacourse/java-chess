package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.Position;

public class KingTest {

	@ParameterizedTest
	@DisplayName("킹이 움직일 수 없는 위치가 들어갔을 때 예외를 잘 처리하는지 확인")
	@CsvSource(value = {"5, 3", "3, 1"})
	void kingMove(int x, int y) {
		King king = new King(new Position("e1"), Team.WHITE);
		assertThatThrownBy(() ->
			king.validateMove(new Position(x, y))
		).isInstanceOf(IllegalArgumentException.class);
	}
}
