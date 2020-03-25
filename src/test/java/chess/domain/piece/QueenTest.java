package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class QueenTest {
	@DisplayName("move 유효한 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"a1", "b2", "c3", "e5", "f6", "g7", "h8", "g1", "f2", "e3", "c5", "b6", "a7", "a4", "b4", "c4", "e4", "f4", "g4", "h4", "d1", "d2", "d3", "d5", "d6", "d7", "d8"})
	void move_normal_test(String input) {
		Position position = Board.of("d4");
		Queen queen = new Queen(position, "q");

		assertThat(queen.getAvailablePositions()).contains(Board.of(input));
	}

	@DisplayName("move 코너 유효한 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"a2", "a3", "a4", "a5", "a6", "a7", "a8", "b1", "c1", "d1", "e1", "f1", "g1", "h1", "b2", "c3", "d4", "e5", "f6", "g7", "h8"})
	void move_normal_corner_test(String input) {
		Position position = Board.of("a1");
		Queen queen = new Queen(position, "q");

		assertThat(queen.getAvailablePositions()).contains(Board.of(input));
	}
}
