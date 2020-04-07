package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

	@Test
	@DisplayName("버그를 찾자")
	void findBug() {
		Board board = new Board();
		board.movePiece(new Position("h2"), new Position("h4"));
		board.movePiece(new Position("g7"), new Position("g5"));
		board.movePiece(new Position("h4"), new Position("g5"));

		assertThat(board.getPieces().findByPosition(new Position("h7"))).isNotNull();
	}
}
