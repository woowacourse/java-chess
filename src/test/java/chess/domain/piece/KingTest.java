package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class KingTest {

	@DisplayName("move 유효한 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"a1", "a2", "a3", "b1", "b3", "c1", "c2", "c3"})
	void move_normal_test(String input) {
		Position position = Board.of("b2");
		King king = new King(position, "k");

		assertThat(king.getAvailablePositions()).contains(Board.of(input));
	}

	@DisplayName("move 코너 유효한 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"a2", "b1", "b2"})
	void move_normal_corner_test(String input) {
		Position position = Board.of("a1");
		King king = new King(position, "k");

		assertThat(king.getAvailablePositions()).contains(Board.of(input));
	}
}
