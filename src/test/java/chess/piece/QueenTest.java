package chess.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.piece.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.Piece.NOT_MOVABLE_POSITION_ERROR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class QueenTest {
	private ChessBoard chessBoard;

	@BeforeEach
	void setUp() {
		chessBoard = new ChessBoard();
		chessBoard.initBoard();
	}

	@DisplayName("퀸이 빈 공간으로 제대로 이동하는지")
	@Test
	void moveQueen_blank_movePosition() {
		chessBoard.move("e2", "e4");
		chessBoard.move("d1", "g4");
		assertThat(chessBoard.getPiece(Position.of("g4")).getName()).isEqualTo("q");
	}

	@DisplayName("퀸이 이동하는 경로 도중에 아군이나 적군이 존재하면 에러를 반환 하는지")
	@Test
	void moveQueen_noBlankOnPath_throwError() {
		chessBoard.replace(Position.of("b3"), new Queen(Color.WHITE, Position.of("b3")));

		chessBoard.replace(Position.of("d5"), new Pawn(Color.WHITE, Position.of("d5"))); // 아군
		assertThatThrownBy(() -> chessBoard.move("b3", "e6"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage(NOT_MOVABLE_POSITION_ERROR);

		chessBoard.replace(Position.of("d5"), new Knight(Color.BLACK, Position.of("d5"))); // 적군
		assertThatThrownBy(() -> chessBoard.move("b3", "e6"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage(NOT_MOVABLE_POSITION_ERROR);
	}

	@DisplayName("퀸이 이동하는 자리에 아군이 존재하면 에러를 반환 하는지")
	@Test
	void moveQueen_allyAtDestination_throwError() {
		assertThatThrownBy(() -> chessBoard.move("d1", "d2"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage(NOT_MOVABLE_POSITION_ERROR);
	}

	@DisplayName("퀸이 이동하는 자리에 적군이 존재하면 적군을 제대로 죽이는지")
	@Test
	void moveQueen_enemyAtDestination_movePosition() {
		chessBoard.replace(Position.of("a3"), new Bishop(Color.WHITE, Position.of("a3")));
		chessBoard.move("a3", "e7");
		assertThat(chessBoard.getPiece(Position.of("e7")).getName()).isEqualTo("b");
	}
}
