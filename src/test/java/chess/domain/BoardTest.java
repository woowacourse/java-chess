package chess.domain;

import static chess.domain.position.Fixtures.*;
import static org.assertj.core.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.domain.piece.Piece;
import chess.domain.piece.rook.Rook;

class BoardTest {
	private Board board;
	private Piece rook;

	@BeforeEach
	void setUp() {
		rook = new Rook(A1);
		Map<String, Piece> map = new LinkedHashMap<>();
		map.put("a1", rook);
		map.put("a5", new Rook(A5));
		board = new Board(map);
	}

	@Test
	void getReversed() {
		assertThat(board.getReversedBoard().get("h8")).isEqualTo(rook);
	}

	@Test
	void get_When_Success() {
		assertThat(board.get("a1")).isEqualTo(rook);
	}

	@Test
	void get_When_Fail() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			board.get("a2")
		).withMessage("기물이 존재하지 않습니다.");
	}

	@Test
	void containsKey() {
		assertThat(board.containsKey("a1")).isTrue();
		assertThat(board.containsKey("a2")).isFalse();
	}

	@Test
	void update_When_Success() {
		board.update("a1", "a3");
		assertThat(board.get("a3")).isEqualTo(rook);
		assertThatIllegalArgumentException().isThrownBy(() ->
			board.get("a1")
		).withMessage("기물이 존재하지 않습니다.");
	}

	@Test
	void update_When_Fail() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			board.update("a1", "a5")
		).withMessage("아군 기물이 위치하고 있습니다.");
	}
}