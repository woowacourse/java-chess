package chess.domain;

import static chess.domain.position.PositionFixture.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.piece.BoardFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import chess.view.OutputView;

class BoardTest {
	static Stream<Arguments> generatePosition() {
		return Stream.of(Arguments.of(A1, true),
			Arguments.of(C3, false));
	}

	@Test
	void initBoardTest() {
		assertThat(BoardFactory.create()).isInstanceOf(Board.class);
	}

	@Test
	void initRookTest() {
		Board board = BoardFactory.create();
		Map<Position, Piece> pieces = board.getPieces();

		assertThat(pieces.get(A1)).isInstanceOf(Rook.class);
	}

	@Test
	void isEmptyPositionTest() {
		Board board = BoardFactory.create();
		assertThat(board.isNotEmptyPosition(A3)).isTrue();
	}

	@Test
	void isNotEmptyPositionTest() {
		Board board = BoardFactory.create();
		assertThat(board.isNotEmptyPosition(A6)).isFalse();
	}

	@ParameterizedTest
	@MethodSource("generatePosition")
	void findPieceBy(Position position, boolean expect) {
		Board board = BoardFactory.create();
		assertThat(board.findPieceBy(position)).isEqualTo(expect);
	}

	@Test
	void showBoard() {
		OutputView.printBoard(BoardFactory.create());
	}
}
