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

public class KnightTest {

	@DisplayName("getAvailablePositions 유효한 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"a2", "a4", "b1", "b5", "d1", "d5", "e2", "e4"})
	void getAvailablePositions_normal_test(String input) {
		Position position = Board.of("c3");
		Knight knight = new Knight(position, "n", Color.WHITE);

		assertThat(knight.getAvailablePositions()).contains(Board.of(input));
	}

	@DisplayName("getAvailablePositions 유효한 코너 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"b3", "c2"})
	void getAvailablePositions_corner_test(String input) {
		Position position = Board.of("a1");
		Knight knight = new Knight(position, "n", Color.WHITE);

		assertThat(knight.getAvailablePositions()).contains(Board.of(input));
	}

	@DisplayName("findRemovablePositions 다른 말이 경로를 막고있는 경우 막고있는 말들을 정확히 반환하는지 개수 테스트")
	@Test
	void findRemovablePositions_normal_count_test() {
		Position position = Board.of("c3");
		Knight knight = new Knight(position, "n", Color.WHITE);

		List<Piece> pieces = Arrays.asList(
				new Knight(Board.of("a2"), "k", Color.WHITE),
				new Queen(Board.of("e4"), "q", Color.WHITE)
		);

		assertThat(knight.findRemovablePositions(knight.getAvailablePositions(), pieces).size()).isEqualTo(2);
	}

	@DisplayName("findRemovablePositions 다른 말이 경로를 막고있는 경우 막고있는 말들을 정확히 반환하는지 테스트")
	@ParameterizedTest
	@ValueSource(strings = {"a2", "e4"})
	void findRemovablePositions_normal_test(String input) {
		Position position = Board.of("c3");
		Knight knight = new Knight(position, "n", Color.WHITE);

		List<Piece> pieces = Arrays.asList(
				new Knight(Board.of("a2"), "k", Color.WHITE),
				new Queen(Board.of("e4"), "q", Color.WHITE)
		);

		assertThat(knight.findRemovablePositions(knight.getAvailablePositions(), pieces)).contains(Board.of(input));
	}
}
