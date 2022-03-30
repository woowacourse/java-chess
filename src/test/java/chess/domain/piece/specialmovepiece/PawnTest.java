package chess.domain.piece.specialmovepiece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.Color;
import chess.domain.piece.mulitiplemovepiece.Bishop;
import chess.domain.piece.unitmovepiece.None;
import chess.domain.piece.unitmovepiece.Pawn;
import chess.domain.position.Direction;

public class PawnTest {
	@Test
	@DisplayName("화이트 폰을 위쪽으로 한칸 이동 가능하다")
	void canMove_a1_a2() {
		Pawn pawn = new Pawn(Color.WHITE);
		Boolean canMove = pawn.canMove(new Direction(0, 1), new None(Color.NONE));

		assertThat(canMove).isTrue();
	}

	@Test
	@DisplayName("화이트폰을 오른쪽으로 한칸, 위쪽으로 두칸 이동 불가능하다")
	void canMove_a1_b3() {
		Pawn pawn = new Pawn(Color.WHITE);
		Boolean canMove = pawn.canMove(new Direction(1, 2), new None(Color.NONE));

		assertThat(canMove).isFalse();
	}

	@Test
	@DisplayName("화이트폰을 위쪽으로 세칸 이동 불가능하다")
	void canMove_a1_a4() {
		Pawn pawn = new Pawn(Color.WHITE);
		Boolean canMove = pawn.canMove(new Direction(0, 3), new None(Color.NONE));

		assertThat(canMove).isFalse();
	}

	@Test
	@DisplayName("화이트폰의 첫 이동이면 위쪽으로 두칸 이동 가능하다")
	void canMove_a1_a3() {
		Pawn pawn = new Pawn(Color.WHITE);
		Boolean canMove = pawn.canMove(new Direction(0, 2), new None(Color.NONE));

		assertThat(canMove).isTrue();
	}

	@Test
	@DisplayName("화이트폰의 첫 이동이 아니면 위로 두 칸 이동 불가능하다")
	void canMove_a2_a4() {
		Pawn pawn = new Pawn(Color.WHITE);
		boolean canMove = pawn.canMove(new Direction(0, 2), new None(Color.NONE));
		canMove = pawn.canMove(new Direction(0, 2), new None(Color.NONE));

		assertThat(canMove).isFalse();
	}

	@Test
	@DisplayName("불랙폰을 아래쪽으로 한칸 이동 가능하다")
	void canMove_a8_a7() {
		Pawn pawn = new Pawn(Color.BLACK);
		Boolean canMove = pawn.canMove(new Direction(0, -1), new None(Color.NONE));

		assertThat(canMove).isTrue();
	}

	@Test
	@DisplayName("블랙폰의 첫 이동이면 아래쪽으로 두칸 이동 가능하다")
	void canMove_a8_a6() {
		Pawn pawn = new Pawn(Color.BLACK);
		Boolean canMove = pawn.canMove(new Direction(0, -2), new None(Color.NONE));

		assertThat(canMove).isTrue();
	}

	@Test
	@DisplayName("폰은 공격을 위해 대각선으로 한칸 이동할 수 있다")
	public void moveDiagonalToAttack() {
		Pawn pawn = new Pawn(Color.BLACK);
		Boolean canMove = pawn.canMove(new Direction(-1, -1), new Pawn(Color.WHITE));

		assertThat(canMove).isTrue();
	}

	@Test
	@DisplayName("폰은 공격이 아니면 대각선으로 한칸 이동할 없다")
	public void cantMoveDiagonalGeneral() {
		Pawn pawn = new Pawn(Color.BLACK);
		Boolean canMove = pawn.canMove(new Direction(-1, -1), new None(Color.NONE));

		assertThat(canMove).isFalse();
	}

	@Test
	@DisplayName("같은 편이 있는 위치로는 이동 불가능하다")
	void cantMove_sameTeamPosition() {
		Pawn pawn = new Pawn(Color.BLACK);
		assertThatThrownBy(() -> pawn.canMove(new Direction(2, 2), new Bishop(Color.BLACK)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 사격 중지!! 아군이다!! ><\n");
	}
}
