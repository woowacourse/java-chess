package chess.domain.piece;

import static chess.domain.TestFixture.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Side;
import chess.domain.position.Column;

class PieceTest {
	@DisplayName("canMove에서 Piece의 Position과 같은 Position을 인자로 넘겼을 때 false 반환")
	@Test
	void canMove_SamePosition_False() {
		assertThat(ROOK_D4.canMove(D4)).isFalse();
	}

	@DisplayName("canNotMove에서 Piece의 Position과 같은 Position을 인자로 넘겼을 때 true 반환")
	@Test
	void canMove_SamePosition_True() {
		assertThat(ROOK_D4.canNotMove(D4)).isTrue();
	}

	@DisplayName("isBlock에서 Position 인자들의 사이가 같은 열로 막혀있고, 두 포지션이 piece와 같은 방향이면 true 반환")
	@Test
	void isBlock_SameColumnPosition_True() {
		assertThat(ROOK_D4.isBlock(D3, D5)).isTrue();
	}

	@DisplayName("isBlock에서 Position 인자들의 사이가 같은 행으로 막혀있고, 두 포지션이 piece와 같은 방향이면 true 반환")
	@Test
	void isBlock_SameRowPosition_True() {
		assertThat(ROOK_D4.isBlock(C4, E4)).isTrue();
	}

	@DisplayName("isBlock에서 Position 인자들의 사이가 같은 대각선으로 막혀있고, 두 포지션이 piece와 같은 방향이면 true 반환")
	@Test
	void isBlock_SameDiagonalPosition_True() {
		assertThat(ROOK_D4.isBlock(C3, E5)).isTrue();
	}

	@DisplayName("isForwardAttack에서 Position 인자가 Side의 공격 방향인 경우 true 반환")
	@Test
	void isForwardAttack_PositionInRightDirectionOfSide_True() {
		assertThat(new Pawn(Side.WHITE, D4).isForwardAttack(D5)).isTrue();
		assertThat(new Pawn(Side.BLACK, D4).isForwardAttack(D3)).isTrue();
	}

	@DisplayName("isSamePosition에서 같은 position을 인자로 넘겼을 때 true 반환")
	@Test
	void isSamePosition_SamePosition_True() {
		assertThat(ROOK_D4.isSamePosition(D4)).isTrue();
	}

	@DisplayName("isSameSide에서 같은 Side를 인자로 넘겼을 때 true 반환")
	@Test
	void isSameSide_SameSide_True() {
		assertThat(new Rook(Side.BLACK, D2).isSameSide(new Rook(Side.BLACK, D3))).isTrue();
	}

	@DisplayName("isSameCol에서 같은 Column을 인자로 넘겼을 때 true 반환")
	@Test
	void isSameCol_SameColumn_True() {
		assertThat(ROOK_D4.isSameCol(Column.FOUR)).isTrue();
	}
}