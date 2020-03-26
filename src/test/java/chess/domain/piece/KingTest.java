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
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class KingTest {

	@DisplayName("createMovablePositions 유효한 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"a1", "a2", "a3", "b1", "b3", "c1", "c2", "c3"})
	void createMovablePositions_normal_test(String input) {
		Position position = Board.of("b2");
		King king = new King(position, "k", Color.WHITE);

		Set<Position> positions = king.createMovablePositions(Collections.emptyList());
		assertThat(positions).contains(Board.of(input));
	}

	@DisplayName("createMovablePositions 코너 유효한 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"a2", "b1", "b2"})
	void createMovablePositions_normal_corner_test(String input) {
		Position position = Board.of("a1");
		King king = new King(position, "k", Color.WHITE);

		assertThat(king.createMovablePositions(Collections.emptyList())).contains(Board.of(input));
	}

	@DisplayName("createMovablePositions 아군 말이 경로를 막고있는 경우 갈 수 있는 Position의 개수 반환 테스트")
	@Test
	void createMovablePositions_blocking_count_test() {
		Position position = Board.of("b2");
		King king = new King(position, "k", Color.WHITE);

		Pieces pieces = TestPiecesFactory.of(Arrays.asList(
				Board.of("a3"),
				Board.of("c1")
		));

		assertThat(king.createMovablePositions(pieces.getPieces())).size().isEqualTo(6);
	}

	@DisplayName("createMovablePositions 아군 말이 경로를 막고있는 경우 갈 수 있는 Position 반환 테스트")
	@ParameterizedTest
	@ValueSource(strings = {"a1", "a2", "b1", "b3", "c2", "c3"})
	void createMovablePositions_blocking_test(String input) {
		Position position = Board.of("b2");
		King king = new King(position, "k", Color.WHITE);

		Pieces pieces = TestPiecesFactory.of(Arrays.asList(
				Board.of("a3"),
				Board.of("c1")
		));

		assertThat(king.createMovablePositions(pieces.getPieces())).contains(Board.of(input));
	}
}
