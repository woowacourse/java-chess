package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class KingTest {

	@DisplayName("getAvailablePositions 유효한 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"a1", "a2", "a3", "b1", "b3", "c1", "c2", "c3"})
	void getAvailablePositions_normal_test(String input) {
		Position position = Board.of("b2");
		King king = new King(position, "k", Color.WHITE);

		assertThat(king.getAvailablePositions()).contains(Board.of(input));
	}

	@DisplayName("getAvailablePositions 코너 유효한 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"a2", "b1", "b2"})
	void getAvailablePositions_normal_corner_test(String input) {
		Position position = Board.of("a1");
		King king = new King(position, "k", Color.WHITE);

		assertThat(king.getAvailablePositions()).contains(Board.of(input));
	}

	@DisplayName("findRemovablePositions 다른 말이 경로를 막고있는 경우 막고있는 말들을 정확히 반환하는지 개수 테스트")
	@Test
	void findRemovablePositions_normal_count_test() {
		Position position = Board.of("b2");
		King king = new King(position, "k", Color.WHITE);

		List<Piece> pieces = Arrays.asList(
				new Knight(Board.of("a3"), "k", Color.WHITE),
				new Queen(Board.of("c1"), "q", Color.WHITE)
		);

		assertThat(king.findRemovablePositions(king.getAvailablePositions(), pieces).size()).isEqualTo(2);
	}

	@DisplayName("findRemovablePositions 다른 말이 경로를 막고있는 경우 막고있는 말의 포지션 반환 테스트")
	@ParameterizedTest
	@ValueSource(strings = {"a3", "c1"})
	void findRemovablePositions_normal_test(String input) {
		Position position = Board.of("b2");
		King king = new King(position, "k", Color.WHITE);

		List<Piece> pieces = Arrays.asList(
				new Knight(Board.of("a3"), "k", Color.WHITE),
				new Queen(Board.of("c1"), "q", Color.WHITE)
		);

		assertThat(king.findRemovablePositions(king.getAvailablePositions(), pieces)).contains(Board.of(input));
	}
}
