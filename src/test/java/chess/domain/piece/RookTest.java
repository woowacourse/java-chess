package chess.domain.piece;

import chess.domain.position.PositionFactory;
import chess.domain.position.Position;
import chess.domain.piece.pieces.Pieces;
import chess.domain.piece.pieces.TestPiecesFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class RookTest {

	@DisplayName("createMovablePositions 유효한 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"c1", "c2", "c4", "c5", "c6", "c7", "c8", "a3", "b3", "d3", "e3", "f3", "g3", "h3"})
	void move_normal_test(String input) {
		Position position = PositionFactory.of("c3");
		Rook rook = new Rook(position, "r", Color.WHITE);

		assertThat(rook.createMovablePositions(Collections.emptyList())).contains(PositionFactory.of(input));
	}

	@DisplayName("createMovablePositions 코너 유효한 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"a2", "a3", "a4", "a5", "a6", "a7", "a8", "b1", "c1", "d1", "e1", "f1", "g1", "h1"})
	void move_normal_corner_test(String input) {
		Position position = PositionFactory.of("a1");
		Rook rook = new Rook(position, "r", Color.WHITE);

		assertThat(rook.createMovablePositions(Collections.emptyList())).contains(PositionFactory.of(input));
	}

	@DisplayName("createMovablePositions 아군 말이 경로를 막고있는 경우 갈 수 있는 Position의 개수 반환 테스트")
	@Test
	void createMovablePositions_blocking_count_test() {
		Position position = PositionFactory.of("c3");
		Rook rook = new Rook(position, "r", Color.WHITE);

		Pieces pieces = TestPiecesFactory.of(Arrays.asList(
				PositionFactory.of("c1"),
				PositionFactory.of("b3")
		));

		assertThat(rook.createMovablePositions(pieces.getPieces())).size().isEqualTo(11);
	}

	@DisplayName("createMovablePositions 아군 말이 경로를 막고있는 경우 갈 수 있는 Position 반환 테스트")
	@ParameterizedTest
	@ValueSource(strings = {"c2", "c4", "c5", "c6", "c7", "c8", "d3", "e3", "f3", "g3", "h3"})
	void createMovablePositions_blocking_test(String input) {
		Position position = PositionFactory.of("c3");
		Rook rook = new Rook(position, "r", Color.WHITE);

		Pieces pieces = TestPiecesFactory.of(Arrays.asList(
				PositionFactory.of("c1"),
				PositionFactory.of("b3")
		));

		assertThat(rook.createMovablePositions(pieces.getPieces())).contains(PositionFactory.of(input));
	}
}
