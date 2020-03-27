package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.piece.exception.NotMovableException;

public class BoardTest {
	private Board board;

	@BeforeEach
	void setUp() {
		board = Board.create();
	}

	@Test
	@DisplayName("체스판이 정상적으로 생성된 경우")
	void constructor() {
		assertThat(board).isInstanceOf(Board.class);
	}

	@Test
	@DisplayName("체스판 초기화")
	void initialize() {
		assertThat(board.getBoard().size()).isEqualTo(64);
	}

	@Test
	@DisplayName("체스판 말 위치 이동")
	void move() {
		Position source = Position.from("a2");
		Position target = Position.from("a3");
		Piece sourcePiece = board.findPiece(source);
		Piece targetPiece = board.findPiece(target);
		sourcePiece.move(targetPiece);
		assertThat(sourcePiece).isEqualTo(board.findPiece(target));
	}

	@Test
	@DisplayName("체스판 말 이동 경로에 장애물이 존재하는 경우 예외 발생")
	void move_invalid_path_by_obstacle() {
		Position source = Position.from("d1");
		Position target = Position.from("d7");
		Piece sourcePiece = board.findPiece(source);
		Piece targetPiece = board.findPiece(target);
		assertThatExceptionOfType(NotMovableException.class).isThrownBy(
				() -> sourcePiece.move(targetPiece));
	}
}
