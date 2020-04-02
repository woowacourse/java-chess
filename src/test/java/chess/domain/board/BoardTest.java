package chess.domain.board;

import static chess.domain.position.PositionFixture.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Knight;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;

class BoardTest {
	@Test
	@DisplayName("보드 초기화 테스트")
	void initBoardTest() {
		Board board = BoardFactory.create();
		assertThat(board).isInstanceOf(Board.class);
	}

	@Test
	@DisplayName("초기 보드 에서 A3 위치는 비어 있어야 한다.")
	void isEmptyPositionTest() {
		Board board = BoardFactory.create();
		assertThat(board.isNotEmptyPosition(A3)).isFalse();
	}

	@Test
	@DisplayName("초기 보드 에서 A2 위치는 비어 있지 않다.")
	void isNotEmptyPositionTest() {
		Board board = BoardFactory.create();
		assertThat(board.isNotEmptyPosition(A2)).isTrue();
	}

	@ParameterizedTest
	@MethodSource("generatePositions")
	@DisplayName("초기 보드에서 특정 위치 Piece 가져오는 테스트")
	void findPieceByTest(Position position, Class<Object> expectObject) {
		Board board = BoardFactory.create();
		assertThat(board.findPieceBy(position)).isInstanceOf(expectObject);
	}

	static Stream<Arguments> generatePositions() {
		return Stream.of(
			Arguments.of(A1, Rook.class),
			Arguments.of(B1, Knight.class),
			Arguments.of(C1, Bishop.class),
			Arguments.of(D1, Queen.class));
	}

	@Test
	@DisplayName("특정위치로 움직인 이후 그위치는 비어있지 않다.")
	void movePieceTest() {
		Board board = BoardFactory.create();
		board.movePiece(A2, A3);

		assertThat(board.isNotEmptyPosition(A3)).isTrue();
	}

	@Test
	@DisplayName("특정 색의 King이 존재하는지 여부 확인 테스트")
	void isKingAliveTest() {
		Board board = BoardFactory.create();

		assertThat(board.isKingAliveOf(Color.WHITE)).isTrue();
	}

	@Test
	@DisplayName("게임이 진행된후 King이 존재하는지 여부 확인 테스트")
	void isKingAliveAfterGameTest() {
		Board board = BoardFactory.create();
		board.movePiece(C2,C3);
		board.movePiece(D7,D6);
		board.movePiece(D1,A4);
		board.movePiece(E8,D7);
		board.movePiece(A4,D7); // 블랙 King 이 죽는 무빙

		assertThat(board.isKingAliveOf(Color.BLACK)).isFalse();
	}

}
