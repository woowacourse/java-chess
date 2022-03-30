package chess.domain.piece.unitmovepiece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.Color;
import chess.domain.piece.None;
import chess.domain.piece.mulitiplemovepiece.Bishop;
import chess.domain.position.Direction;

public class KingTest {
	@Test
	@DisplayName("킹을 오른쪽으로 한칸 움직일 수 있다")
	void canMove_a1_a2() {
		King king = new King(Color.BLACK);
		Boolean canMove = king.canMove(new Direction(0, 1), new None(Color.NONE));

		assertThat(canMove).isTrue();
	}

	@Test
	@DisplayName("킹을 위쪽으로 한칸 이동 가능하다")
	void canMove_a1_b1() {
		King king = new King(Color.BLACK);
		Boolean canMove = king.canMove(new Direction(1, 0), new None(Color.NONE));

		assertThat(canMove).isTrue();
	}

	@Test
	@DisplayName("킹을 오른쪽으로 한칸, 위쪽으로 한칸 이동 가능하다")
	void canMove_a1_b2() {
		King king = new King(Color.BLACK);
		Boolean canMove = king.canMove(new Direction(1, 1), new None(Color.NONE));

		assertThat(canMove).isTrue();
	}

	@Test
	@DisplayName("킹을 오른쪽으로 두칸, 위쪽으로 두칸 이동 불가능하다")
	void canMove_a1_c3() {
		King king = new King(Color.BLACK);
		Boolean canMove = king.canMove(new Direction(2, 2), new None(Color.NONE));

		assertThat(canMove).isFalse();
	}

	@Test
	@DisplayName("같은 편이 있는 위치로는 이동 불가능하다")
	void cantMove_sameTeamPosition() {
		King king = new King(Color.BLACK);
		assertThatThrownBy(() -> king.canMove(new Direction(2, 2), new Bishop(Color.BLACK)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 사격 중지!! 아군이다!! ><\n");
	}
}
