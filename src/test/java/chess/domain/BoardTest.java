package chess.domain;

import static chess.domain.position.PositionFixture.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.position.Position;

class BoardTest {
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
		assertThat(board.isNotEmptyPosition(A3)).isFalse();
	}

	@Test
	void isNotEmptyPositionTest() {
		Board board = BoardFactory.create();
		assertThat(board.isNotEmptyPosition(A6)).isFalse();
	}
}
