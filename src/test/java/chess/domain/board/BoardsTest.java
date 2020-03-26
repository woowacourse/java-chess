package chess.domain.board;

import static chess.domain.position.Fixtures.*;
import static org.assertj.core.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import chess.domain.piece.Piece;
import chess.domain.piece.rook.Rook;
import chess.domain.position.Path;

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
		Path path = Path.of(A1, A3);

		assertThat(boards.hasPieceIn(path.path())).isTrue();
	}

	@Test
	void hasPieceIn_Return_True_When_UpperBoardHas() {
		Map<String, Piece> lowerBoard = new LinkedHashMap<>();
		Map<String, Piece> upperBoard = new LinkedHashMap<>();
		upperBoard.put("g7", new Rook(G7));

		Boards boards = Boards.of(lowerBoard, upperBoard);
		Path path = Path.of(B1, B3);

		assertThat(boards.hasPieceIn(path.path())).isTrue();
	}
}