package chess.domain.piece;

import chess.domain.piece.pieces.Pieces;
import chess.domain.piece.pieces.TestPiecesFactory;
import chess.domain.position.Position;
import chess.domain.position.PositionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class BishopTest {
	@DisplayName("move 유효한 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"a1", "b2", "c3", "e5", "f6", "g7", "h8", "g1", "f2", "e3", "c5", "b6", "a7"})
	void move_normal_test(String input) {
		Position position = PositionFactory.of("d4");
		Piece bishop = TestPieceFactory.createBishop(position, Color.WHITE);

		assertThat(bishop.createMovablePositions(Collections.emptyList())).contains(PositionFactory.of(input));
	}

	@DisplayName("move 코너 유효한 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"b2", "c3", "d4", "e5", "f6", "g7", "h8"})
	void move_normal_corner_test(String input) {
		Position position = PositionFactory.of("a1");
		Piece bishop = TestPieceFactory.createBishop(position, Color.WHITE);

		assertThat(bishop.createMovablePositions(Collections.emptyList())).contains(PositionFactory.of(input));
	}

	@DisplayName("createMovablePositions 아군 말이 경로를 막고있는 경우 갈 수 있는 Position의 개수 반환 테스트")
	@Test
	void createMovablePositions_blocking_count_test() {
		Position position = PositionFactory.of("d4");
		Piece bishop = TestPieceFactory.createBishop(position, Color.WHITE);

		Pieces pieces = TestPiecesFactory.of(Arrays.asList(
				PositionFactory.of("a1"),
				PositionFactory.of("c5")
		));

		assertThat(bishop.createMovablePositions(pieces.getPieces())).size().isEqualTo(9);
	}

	@DisplayName("createMovablePositions 아군 말이 경로를 막고있는 경우 갈 수 있는 Position 반환 테스트")
	@ParameterizedTest
	@ValueSource(strings = {"b2", "c3", "e5", "f6", "g7", "h8", "g1", "f2", "e3"})
	void createMovablePositions_blocking_test(String input) {
		Position position = PositionFactory.of("d4");
		Piece bishop = TestPieceFactory.createBishop(position, Color.WHITE);

		Pieces pieces = TestPiecesFactory.of(Arrays.asList(
				PositionFactory.of("a1"),
				PositionFactory.of("c5")
		));

		assertThat(bishop.createMovablePositions(pieces.getPieces())).contains(PositionFactory.of(input));
	}
}
