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

public class QueenTest {

	@DisplayName("createMovablePositions 유효한 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"a1", "b2", "c3", "e5", "f6", "g7", "h8", "g1",
			"f2", "e3", "c5", "b6", "a7", "a4", "b4", "c4", "e4", "f4", "g4",
			"h4", "d1", "d2", "d3", "d5", "d6", "d7", "d8"})
	void move_normal_test(String input) {
		Position position = Board.of("d4");
		Queen queen = new Queen(position, "q", Color.WHITE);

		assertThat(queen.createMovablePositions(Collections.emptyList())).contains(Board.of(input));
	}

	@DisplayName("createMovablePositions 코너 유효한 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"a2", "a3", "a4", "a5", "a6", "a7", "a8", "b1", "c1", "d1", "e1", "f1", "g1", "h1", "b2", "c3", "d4", "e5", "f6", "g7", "h8"})
	void move_normal_corner_test(String input) {
		Position position = Board.of("a1");
		Queen queen = new Queen(position, "q", Color.WHITE);

		assertThat(queen.createMovablePositions(Collections.emptyList())).contains(Board.of(input));
	}

	@DisplayName("createMovablePositions 아군 말이 경로를 막고있는 경우 갈 수 있는 Position의 개수 반환 테스트")
	@Test
	void createMovablePositions_blocking_count_test() {
		Position position = Board.of("d4");
		Queen queen = new Queen(position, "q", Color.WHITE);

		Pieces pieces = TestPiecesFactory.of(Arrays.asList(
				Board.of("b2"),
				Board.of("c3"),
				Board.of("e5"),
				Board.of("f6"),
				Board.of("d5")
		));

		assertThat(queen.createMovablePositions(pieces.getPieces())).size().isEqualTo(16);
	}

	@DisplayName("createMovablePositions 아군 말이 경로를 막고있는 경우 갈 수 있는 Position 반환 테스트")
	@ParameterizedTest
	@ValueSource(strings = {"g1", "f2", "e3", "c5", "b6", "a7", "a4", "b4", "c4", "e4",
			"f4", "g4", "h4", "d1", "d2", "d3"})
	void createMovablePositions_blocking_test(String input) {
		Position position = Board.of("d4");
		Queen queen = new Queen(position, "q", Color.WHITE);

		Pieces pieces = TestPiecesFactory.of(Arrays.asList(
				Board.of("b2"),
				Board.of("c3"),
				Board.of("e5"),
				Board.of("f6"),
				Board.of("d5")
		));

		assertThat(queen.createMovablePositions(pieces.getPieces())).contains(Board.of(input));
	}
}
