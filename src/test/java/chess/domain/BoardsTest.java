package chess.domain;

import static chess.domain.position.Fixtures.*;
import static org.assertj.core.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import chess.domain.piece.Piece;
import chess.domain.piece.rook.Rook;

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

		assertThat(Boards.of(board,board).getTotal()).isEqualTo(expected);
	}
}