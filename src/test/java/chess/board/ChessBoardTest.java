package chess.board;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.piece.*;
import chess.view.OutputView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ChessBoardTest {

	private ChessBoard chessBoard;

	@BeforeEach
	void setUp() {
		chessBoard = new ChessBoard();
		chessBoard.initBoard();
	}

	@ParameterizedTest
	@DisplayName("체스 초기 기물 배치 확인")
	@CsvSource(value = {"0, 4, K", "7, 4, k", "1, 0, P", "6, 0, p", "5, 0, ."}, delimiter = ',')
	void pieceLocationCheck(int i, int j, String value) {
		assertThat(chessBoard.getPieceAt(Position.of(i, j)).getName()).isEqualTo(value);
	}

	@Test
	void movePawnSuccess() {
		chessBoard.move("b2", "b3");
		assertThat(chessBoard.getPieceAt(Position.of(5, 1)).getName()).isEqualTo("p");
	}

	@Test
	void movePawnStart() {
		chessBoard.move("b2", "b4");
		assertThat(chessBoard.getPieceAt(Position.of(4, 1)).getName()).isEqualTo("p");
	}

	@Test
	void attackPawnSuccess() {
		chessBoard.move("c7", "c5");
		chessBoard.move("c5", "c4");
		chessBoard.move("c4", "c3");
		chessBoard.move("b2", "c3");
		assertThat(chessBoard.getPieceAt(Position.of(5, 2)).getName()).isEqualTo("p");
	}

	@Test
	void attackPawnFail() {
		chessBoard.move("c7", "c5");
		chessBoard.move("c5", "c4");
		chessBoard.move("c4", "c3");
		assertThatThrownBy(() -> {
			chessBoard.move("c2", "c3");
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void movePawnFailLinear() {
		assertThatThrownBy(() -> {
			chessBoard.move("b2", "b1");
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void movePawnFailDiagonal() {
		assertThatThrownBy(() -> {
			chessBoard.move("b2", "c3");
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void movePawnFailSame() {
		assertThatThrownBy(() -> {
			chessBoard.move("b2", "b2");
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void moveRookSuccess() {
		chessBoard.move("a2", "a3");
		chessBoard.move("a1", "a2");
		assertThat(chessBoard.getPieceAt(Position.of(6, 0)).getName()).isEqualTo("r");
	}

	@Test
	void moveRookFail() {
		assertThatThrownBy(() -> {
			chessBoard.move("a1", "a2");
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void attackRookSuccess() {
		chessBoard.move("a2", "a4");
		chessBoard.move("a1", "a3");
		chessBoard.move("a3", "b3");
		chessBoard.move("b3", "b7");
		assertThat(chessBoard.getPieceAt(Position.of(1, 1)).getName()).isEqualTo("r");
	}

	@Test
	void moveBishopSuccess() {
		chessBoard.move("b2", "b3");
		chessBoard.move("c1", "b2");
		assertThat(chessBoard.getPieceAt(Position.of(6, 1)).getName()).isEqualTo("b");
	}

	@Test
	void moveBishopFail() {
		assertThatThrownBy(() -> {
			chessBoard.move("c1", "b2");
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void attackBishopSuccess() {
		chessBoard.move("b2", "b3");
		chessBoard.move("c1", "a3");
		chessBoard.move("a3", "e7");
		assertThat(chessBoard.getPieceAt(Position.of(1, 4)).getName()).isEqualTo("b");
	}

	@Test
	void moveKnightSuccess() {
		chessBoard.move("b1", "a3");
		assertThat(chessBoard.getPieceAt(Position.of(5, 0)).getName()).isEqualTo("n");
	}

	@Test
	void moveKnightFail() {
		assertThatThrownBy(() -> {
			chessBoard.move("b1", "d2");
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void scoreTest() {
		chessBoard.move("b2", "b4");
		chessBoard.move("c7", "c5");
		chessBoard.move("b4", "c5");
		assertThat(chessBoard.getScore(Color.WHITE)).isEqualTo(37.0);
		assertThat(chessBoard.getScore(Color.BLACK)).isEqualTo(37.0);
	}

	@Test
	void scoreTest2() {
		ChessBoard emptyChessBoard = new ChessBoard();

		emptyChessBoard.replace(Position.of("b8"), new King(Color.BLACK, Position.of("b8")));
		emptyChessBoard.replace(Position.of("c8"), new Rook(Color.BLACK, Position.of("c8")));
		emptyChessBoard.replace(Position.of("a7"), new Pawn(Color.BLACK, Position.of("a7")));
		emptyChessBoard.replace(Position.of("c7"), new Pawn(Color.BLACK, Position.of("c7")));
		emptyChessBoard.replace(Position.of("d7"), new Bishop(Color.BLACK, Position.of("d7")));
		emptyChessBoard.replace(Position.of("b6"), new Pawn(Color.BLACK, Position.of("b6")));
		emptyChessBoard.replace(Position.of("e6"), new Queen(Color.BLACK, Position.of("e6")));

		emptyChessBoard.replace(Position.of("f4"), new Knight(Color.WHITE, Position.of("f4")));
		emptyChessBoard.replace(Position.of("g4"), new Queen(Color.WHITE, Position.of("g4")));
		emptyChessBoard.replace(Position.of("f3"), new Pawn(Color.WHITE, Position.of("f3")));
		emptyChessBoard.replace(Position.of("h3"), new Pawn(Color.WHITE, Position.of("h3")));
		emptyChessBoard.replace(Position.of("f2"), new Pawn(Color.WHITE, Position.of("f2")));
		emptyChessBoard.replace(Position.of("g2"), new Pawn(Color.WHITE, Position.of("g2")));
		emptyChessBoard.replace(Position.of("e1"), new Rook(Color.WHITE, Position.of("e1")));
		emptyChessBoard.replace(Position.of("f1"), new King(Color.WHITE, Position.of("f1")));

		assertThat(emptyChessBoard.getScore(Color.BLACK)).isEqualTo(20.0);
		assertThat(emptyChessBoard.getScore(Color.WHITE)).isEqualTo(19.5);
	}
}
