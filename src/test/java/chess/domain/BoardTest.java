package chess.domain;

import static chess.domain.TestFixture.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.Piece;

class BoardTest {
	@DisplayName("Pawn을 경로에 맞게 이동")
	@Test
	void movePawn() {
		Board board = PawnTestingBoard();
		Piece sourcePiece = board.findPieceBy(D5);

		board.move(D5, D6);

		assertThat(board.findPieceBy(D6)).isEqualTo(sourcePiece);
	}

	@DisplayName("findPieceBy에서 Position 자리에 기물이 없으면 예외 발생")
	@Test
	void findPieceBy() {
		Board board = PawnTestingBoard();
		assertThatThrownBy(() -> board.findPieceBy(D2)).isInstanceOf(IllegalArgumentException.class);
	}
}
