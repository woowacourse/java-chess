package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.board.Board;
import chess.domain.board.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

	@DisplayName("getAvailablePositions 유효한 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"a3", "b3", "c3"})
	void getAvailablePositions_normal_test(String input) {
		Position position = Board.of("b2");
		Pawn pawn = new Pawn(position, "p", Direction.whitePawnDirection());

		assertThat(pawn.getAvailablePositions()).contains(Board.of(input));
	}

	@DisplayName("getAvailablePositions 코너 유효한 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"a2", "b2"})
	void getAvailablePositions_normal_corner_test(String input) {
		Position position = Board.of("a1");
		Pawn pawn = new Pawn(position, "p", Direction.whitePawnDirection());

		assertThat(pawn.getAvailablePositions()).contains(Board.of(input));
	}
}
