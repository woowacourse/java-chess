package chess.domain.board;

import chess.domain.Turn;
import chess.domain.piece.Piece;
import chess.domain.piece.king.King;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.rook.Rook;
import chess.domain.position.Path;
import chess.domain.position.Position;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static chess.domain.position.Fixtures.*;
import static org.assertj.core.api.Assertions.*;

class BoardsTest {
	@Test
	void create_By_Factory() {
		assertThat(BoardFactory.create()).isInstanceOf(Boards.class);
	}

	@Test
	void getTotal() {
		Map<String, String> expected = new LinkedHashMap<>();
		expected.put("a1", "r");
		expected.put("h8", "R");

		Map<String, Piece> board = new LinkedHashMap<>();
		board.put("a1", new Rook(A1));

		assertThat(Boards.of(board, board).getTotal()).isEqualTo(expected);
	}

	@Test
	void hasPieceIn_Return_True_When_LowerBoardHas() {
		Map<String, Piece> lowerBoard = new LinkedHashMap<>();
		lowerBoard.put("a2", new Rook(A2));
		Map<String, Piece> upperBoard = new LinkedHashMap<>();

		Boards boards = Boards.of(lowerBoard, upperBoard);

		assertThat(boards.hasPieceIn(Path.valueOf(A1, A3))).isTrue();
	}

	@Test
	void hasPieceIn_Return_True_When_UpperBoardHas() {
		Map<String, Piece> lowerBoard = new LinkedHashMap<>();
		Map<String, Piece> upperBoard = new LinkedHashMap<>();
		upperBoard.put("g7", new Rook(G7));

		Boards boards = Boards.of(lowerBoard, upperBoard);

		assertThat(boards.hasPieceIn(Path.valueOf(B1, B3))).isTrue();
	}

	@Test
	void pawnMove_Success() {
		Map<String, Piece> lowerBoard = new LinkedHashMap<>();
		lowerBoard.put("a2", new Pawn(A2));
		Map<String, Piece> upperBoard = new LinkedHashMap<>();

		Boards boards = Boards.of(lowerBoard, upperBoard);

		assertThatCode(() -> boards.move("a2", "a3", Turn.LOWER))
				.doesNotThrowAnyException();
	}

	@Test
	void pawnMove_Throw_Exception_When_TryFrontAttack() {
		Map<String, Piece> lowerBoard = new LinkedHashMap<>();
		lowerBoard.put("a2", new Pawn(A2));
		Map<String, Piece> upperBoard = new LinkedHashMap<>();
		upperBoard.put(Position.getReversedNameOf("a3"), new Pawn(A3.reverse()));

		Boards boards = Boards.of(lowerBoard, upperBoard);

		assertThatIllegalArgumentException()
				.isThrownBy(() -> boards.move("a2", "a3", Turn.LOWER))
				.withMessage("폰은 전방의 적을 공격할 수 없습니다.");
	}

	@Test
	void pawnMove_Throw_Exception_When_TryDiagonalMoveToEmptyPosition() {
		Map<String, Piece> lowerBoard = new LinkedHashMap<>();
		lowerBoard.put("a2", new Pawn(A2));
		Map<String, Piece> upperBoard = new LinkedHashMap<>();

		Boards boards = Boards.of(lowerBoard, upperBoard);

		assertThatIllegalArgumentException()
				.isThrownBy(() -> boards.move("a2", "b3", Turn.LOWER))
				.withMessage("폰은 공격이 아니면 대각선 이동이 불가합니다.");
	}

	@Test
	void isKingDead_Return_True_When_BoardsHasNotTwoKing() {
		Map<String, Piece> lowerBoard = new LinkedHashMap<>();
		lowerBoard.put("a2", new King(A2));
		Map<String, Piece> upperBoard = new LinkedHashMap<>();

		Boards boards = Boards.of(lowerBoard, upperBoard);

		assertThat(boards.isKingDead()).isTrue();
	}

	@Test
	void isKingDead_Return_False_When_BoardsHasTwoKing() {
		Map<String, Piece> lowerBoard = new LinkedHashMap<>();
		lowerBoard.put("a2", new King(A2));
		Map<String, Piece> upperBoard = new LinkedHashMap<>();
		upperBoard.put("a2", new King(A2));

		Boards boards = Boards.of(lowerBoard, upperBoard);

		assertThat(boards.isKingDead()).isFalse();
	}
}