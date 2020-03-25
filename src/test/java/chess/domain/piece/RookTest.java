package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class RookTest {

	@DisplayName("move 유효한 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"c1", "c2", "c4", "c5", "c6", "c7", "c8", "a3", "b3", "d3", "e3", "f3", "g3", "h3"})
	void move_normal_test(String input) {
		Position position = Board.of("c3");
		Rook rook = new Rook(position, "r", Color.WHITE);

		assertThat(rook.getAvailablePositions()).contains(Board.of(input));
	}

	@DisplayName("move 코너 유효한 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"a2", "a3", "a4", "a5", "a6", "a7", "a8", "b1", "c1", "d1", "e1", "f1", "g1", "h1"})
	void move_normal_corner_test(String input) {
		Position position = Board.of("a1");
		Rook rook = new Rook(position, "r", Color.WHITE);

		assertThat(rook.getAvailablePositions()).contains(Board.of(input));
	}
}
