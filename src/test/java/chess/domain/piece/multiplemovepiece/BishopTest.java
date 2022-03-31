package chess.domain.piece.multiplemovepiece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.Color;
import chess.domain.piece.None;
import chess.domain.piece.mulitiplemovepiece.Bishop;
import chess.domain.position.Direction;

public class BishopTest {
	@Test
	@DisplayName("비숍을 오른쪽으로 2칸 위쪽으로 2칸 이동 가능하다")
	void canMove_a1_c3() {
		Bishop bishop = new Bishop(Color.BLACK);
		Boolean canMove = bishop.canMove(new Direction(2, 2), new None(Color.NONE));

		assertThat(canMove).isTrue();
	}

	@Test
	@DisplayName("비숍을 왼쪽으로 3칸 아래쪽으로 3칸 이동 가능하다")
	void canMove_d1_a4() {
		Bishop bishop = new Bishop(Color.BLACK);
		Boolean canMove = bishop.canMove(new Direction(-3, -3), new None(Color.NONE));

		assertThat(canMove).isTrue();
	}

	@Test
	@DisplayName("비숍을 위쪽으로 칸 이동 불가능하다")
	void canMove_a1_a5() {
		Bishop bishop = new Bishop(Color.BLACK);
		Boolean canMove = bishop.canMove(new Direction(0, 4), new None(Color.NONE));

		assertThat(canMove).isFalse();
	}

	@Test
	@DisplayName("같은 편이 있는 위치로는 이동 불가능하다")
	void cantMove_sameTeamPosition() {
		Bishop bishop = new Bishop(Color.BLACK);
		assertThatThrownBy(() -> bishop.canMove(new Direction(2, 2), new Bishop(Color.BLACK)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 사격 중지!! 아군이다!! ><\n");
	}
}
