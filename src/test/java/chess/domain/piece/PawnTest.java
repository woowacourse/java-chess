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

public class PawnTest {

	@DisplayName("createMovablePositions 유효한 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"a3", "b3", "c3"})
	void createMovablePositions_normal_test(String input) {
		Position position = Board.of("b2");
		Pawn pawn = new Pawn(position, "p", Color.WHITE);

		Pieces pieces = TestPiecesFactory.of(Arrays.asList(
				Board.of("a3"),
				Board.of("c3")
		), Color.BLACK);

		assertThat(pawn.createMovablePositions(pieces.getPieces())).contains(Board.of(input));
	}

	@DisplayName("createMovablePositions 코너 유효한 position입력시 정상 동작")
	@ParameterizedTest
	@ValueSource(strings = {"a2", "b2"})
	void createMovablePositions_normal_corner_test(String input) {
		Position position = Board.of("a1");
		Pawn pawn = new Pawn(position, "p", Color.WHITE);

		Pieces pieces = TestPiecesFactory.of(Arrays.asList(
				Board.of("b2"),
				Board.of("c3")
		), Color.BLACK);

		assertThat(pawn.createMovablePositions(pieces.getPieces())).contains(Board.of(input));
	}

	@DisplayName("createMovablePositions 이동 가능한 경로에 처음이고 아무 말도 없을시 직진 두 칸과 한 칸 가능")
	@Test
	void createMovablePositions_initial_all_empty_test() {
		Position position = Board.of("b2");
		Pawn pawn = new Pawn(position, "p", Color.WHITE);

		assertThat(pawn.createMovablePositions(Collections.emptyList()).size()).isEqualTo(2);
	}

	@DisplayName("createMovablePositions 이동 가능한 경로에 처음이 아니고 아무 말도 없을시 직진 한 칸만 가능")
	@Test
	void createMovablePositions_not_initial_all_empty_test() {
		Position position = Board.of("b4");
		Pawn pawn = new Pawn(position, "p", Color.WHITE);

		assertThat(pawn.createMovablePositions(Collections.emptyList()).size()).isEqualTo(1);
	}

	@DisplayName("createMovablePositions 이동 가능한 경로에 처음이고 바로 앞에 말이 있을시 이동 불가")
	@Test
	void createMovablePositions_initial_blocked_test() {
		Position position = Board.of("b2");
		Pawn pawn = new Pawn(position, "p", Color.WHITE);
		Pieces pieces = TestPiecesFactory.of(Collections.singletonList(
				Board.of("b3")
		), Color.BLACK);

		assertThat(pawn.createMovablePositions(pieces.getPieces()).size()).isEqualTo(0);
	}
}
