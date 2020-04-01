package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.domain.coordinates.Coordinates;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;

class BoardTest {
	private Board board;

	@BeforeEach
	void setup() {
		board = BoardFactory.createNewGame();
	}

	@Test
	void initBoardTest() {
		assertThat(BoardFactory.createNewGame()).isInstanceOf(Board.class);
	}

	@Test
	void isKingAlive() {
		assertThat(board.isKingAliveOf(Color.WHITE)).isTrue();
	}

	@Test
	void findPieceBy() {
		assertThat(board.findPieceBy(Coordinates.of("A2")).get()).isInstanceOf(Pawn.class);
	}

	@Test
	void movePiece() {
		board.movePiece(Coordinates.of("B2"), Coordinates.of("B3"));
		assertThat(board.findPieceBy(Coordinates.of("B3")).get()).isInstanceOf(Pawn.class);
	}

	@Test
	void movePiece_ExistAlly_ExceptionThrown() {
		assertThatThrownBy(() -> board.movePiece(Coordinates.of("D1"), Coordinates.of("D2")))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("같은 팀의 piece");
	}
}
