package chess.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.domain.piece.Piece;
import chess.domain.piece.rook.Rook;
import chess.domain.position.Position;

class BoardTest {
	private Board board;
	private Piece rook;

	@BeforeEach
	void setUp() {
		rook = new Rook(Position.of("a1"));
		Map<String, Piece> map = new LinkedHashMap<>();
		map.put("a1", rook);
		board = new Board(map);
	}

	@Test
	void getReversed() {
		assertThat(board.getReversed().get("h8")).isEqualTo(rook);
	}

	@Test
	void get() {
		assertThat(board.get("a1")).isEqualTo(rook);
	}
}