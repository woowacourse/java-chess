package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

	@Test
	@DisplayName("목적지에 같은 팀이 있을 때 예외를 잘 처리하는지 확인")
	void sameTeamOnDestination() {
		Board board = new Board();
		Pieces pieces = board.getPieces();
		assertThatThrownBy(() ->
			board.movePiece(new Position("e1"), new Position("e2"))
		).isInstanceOf(IllegalArgumentException.class);
	}

}
