package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class KnightTest {

	@DisplayName("move 유효한 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"a2", "a4", "b1", "b5", "d1", "d5", "e2", "e4"})
	void move_normal_test(String input) {
		Position position = Board.of("c3");
		Knight knight = new Knight(position, "n");

		assertThat(knight.getAvailablePositions()).contains(Board.of(input));
	}

	@DisplayName("move 유효한 코너 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"b3", "c2"})
	void move_corner_test(String input) {
		Position position = Board.of("a1");
		Knight knight = new Knight(position, "n");

		assertThat(knight.getAvailablePositions()).contains(Board.of(input));
	}
}
