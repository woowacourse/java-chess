package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.pieces.Pieces;
import chess.domain.piece.pieces.TestPiecesFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class KnightTest {

	@DisplayName("createMovablePositions 유효한 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"a2", "a4", "b1", "b5", "d1", "d5", "e2", "e4"})
	void createMovablePositions_normal_test(String input) {
		Position position = Board.of("c3");
		Knight knight = new Knight(position, "n", Color.WHITE);

		assertThat(knight.createMovablePositions(Collections.emptyList())).contains(Board.of(input));
	}

	@DisplayName("createMovablePositions 유효한 코너 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"b3", "c2"})
	void createMovablePositions_corner_test(String input) {
		Position position = Board.of("a1");
		Knight knight = new Knight(position, "n", Color.WHITE);

		assertThat(knight.createMovablePositions(Collections.emptyList())).contains(Board.of(input));
	}

	@DisplayName("createMovablePositions 아군 말이 경로를 막고있는 경우 갈 수 있는 Position의 개수 반환 테스트")
	@Test
	void createMovablePositions_blocking_count_test() {
		Position position = Board.of("c3");
		Knight knight = new Knight(position, "n", Color.WHITE);

		Pieces pieces = TestPiecesFactory.of(Arrays.asList(
				Board.of("a2"),
				Board.of("e4")
		));

		assertThat(knight.createMovablePositions(pieces.getPieces()).size()).isEqualTo(6);
	}

	@DisplayName("createMovablePositions 아군 말이 경로를 막고있는 경우 갈 수 있는 Position 반환 테스트")
	@ParameterizedTest
	@ValueSource(strings = {"a4", "b1", "b5", "d1", "d5", "e2"})
	void createMovablePositions_blocking_test(String input) {
		Position position = Board.of("c3");
		Knight knight = new Knight(position, "n", Color.WHITE);

		Pieces pieces = TestPiecesFactory.of(Arrays.asList(
				Board.of("a2"),
				Board.of("e4")
		));

		assertThat(knight.createMovablePositions(pieces.getPieces())).contains(Board.of(input));
	}
}
