package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class BishopTest {
	@DisplayName("move 유효한 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"a1", "b2", "c3", "e5", "f6", "g7", "h8", "g1", "f2", "e3", "c5", "b6", "a7"})
	void move_normal_test(String input) {
		Position position = Board.of("d4");
		Bishop bishop = new Bishop(position, "b");

		assertThat(bishop.getAvailablePositions()).contains(Board.of(input));
	}

	@DisplayName("move 코너 유효한 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"b2", "c3", "d4", "e5", "f6", "g7", "h8"})
	void move_normal_corner_test(String input) {
		Position position = Board.of("a1");
		Bishop bishop = new Bishop(position, "b");

		assertThat(bishop.getAvailablePositions()).contains(Board.of(input));
	}
}
