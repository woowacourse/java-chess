package chess.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import chess.domain.piece.Piece;
import chess.domain.piece.rook.Rook;
import chess.domain.position.Position;

class BoardTest {

	@Test
	void getReversed() {
		Map<String, Piece> map = new LinkedHashMap<>();
		map.put("a1", new Rook(Position.of("a1")));
		Board board = new Board(map);

		assertThat(board.getReversed().get("h8")).isInstanceOf(Rook.class);
	}
}