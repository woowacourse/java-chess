package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;

class PawnTest {

	private final Pawn whitePawn = new Pawn(Color.WHITE);
	private final Pawn blackPawn = new Pawn(Color.BLACK);

	@DisplayName("흰색 진영의 폰은 최초 이동시 위로 2칸 움직일 수 있다.")
	@Test
	void white_pawn_move_a2_a4() {
		//given
		Position a2 = new Position(Column.A, Row.TWO);
		Position a4 = new Position(Column.A, Row.FOUR);
		//then
		assertThat(whitePawn.canMove(a2, a4)).isTrue();
	}

	@DisplayName("흑색 진영의 폰은 최초 이동시 아래로 2칸 움직일 수 있다.")
	@Test
	void black_pawn_move_a7_a5() {
		//given
		Position a7 = new Position(Column.A, Row.SEVEN);
		Position a5 = new Position(Column.A, Row.FIVE);
		//then
		assertThat(blackPawn.canMove(a7, a5)).isTrue();
	}

	@DisplayName("흰색 진영의 폰은 2번째 이동부터 위로 2칸 움직일 수 없다.")
	@Test
	void white_pawn_cant_move_a4_a6_at_second() {
		//given
		Position a2 = new Position(Column.A, Row.TWO);
		Position a4 = new Position(Column.A, Row.FOUR);
		Position a6 = new Position(Column.A, Row.SIX);
		whitePawn.move(a2, a4, (piece -> {
		}));
		//then
		assertThat(whitePawn.canMove(a4, a6)).isFalse();
	}

	@DisplayName("흰색 진영의 폰은 아래로 1칸 움직일 수 없다.")
	@Test
	void white_pawn_cant_move_a5_a4() {
		//given
		Position a5 = new Position(Column.A, Row.FIVE);
		Position a4 = new Position(Column.A, Row.FOUR);
		//then
		assertThat(whitePawn.canMove(a5, a4)).isFalse();
	}

	@DisplayName("흑색 진영의 폰은 위로 1칸 움직일 수 없다.")
	@Test
	void black_pawn_cant_move_a5_a6() {
		//given
		Position a5 = new Position(Column.A, Row.FIVE);
		Position a6 = new Position(Column.A, Row.SIX);
		//then
		assertThat(blackPawn.canMove(a5, a6)).isFalse();
	}

	@DisplayName("흰색 진영의 폰은 2번째 이동부터 위로 1칸 움직일 수 있다.")
	@Test
	void white_pawn_can_move_a4_a5_at_second() {
		//given
		Position a2 = new Position(Column.A, Row.TWO);
		Position a4 = new Position(Column.A, Row.FOUR);
		Position a5 = new Position(Column.A, Row.FIVE);
		whitePawn.move(a2, a4, (piece -> {
		}));
		//then
		assertThat(whitePawn.canMove(a4, a5)).isTrue();
	}

	@DisplayName("흑색 진영의 폰은 2번째 이동부터 아래로 1칸 움직일 수 있다.")
	@Test
	void black_pawn_can_move_a5_a4_at_second() {
		//given
		Position a7 = new Position(Column.A, Row.SEVEN);
		Position a5 = new Position(Column.A, Row.FIVE);
		Position a4 = new Position(Column.A, Row.FOUR);
		blackPawn.move(a7, a5, (piece -> {
		}));
		//then
		assertThat(blackPawn.canMove(a5, a4)).isTrue();
	}

	@DisplayName("폰은 가로로 움직일 수 없다.")
	@Test
	void pawn_cant_move_d3_e3() {
		//given
		Position d3 = new Position(Column.D, Row.THREE);
		Position e3 = new Position(Column.E, Row.THREE);
		//then
		assertThat(blackPawn.canMove(d3, e3)).isFalse();
	}

	@DisplayName("폰은 대각선으로 움직일 수 없다.")
	@Test
	void pawn_cant_move_d2_e3() {
		//given
		Position d2 = new Position(Column.D, Row.TWO);
		Position e3 = new Position(Column.E, Row.THREE);
		//then
		assertThat(blackPawn.canMove(d2, e3)).isFalse();
	}

	@DisplayName("상대 진영의 기물을 잡는 경우에는 대각선 앞으로 1칸 이동할 수 있다.")
	@Test
	void pawn_can_capture_f6_g7() {
		//given
		Position f6 = new Position(Column.F, Row.SIX);
		Position g7 = new Position(Column.G, Row.SEVEN);
		//then
		assertThatNoException().isThrownBy(() -> whitePawn.capture(f6, g7, (piece -> {
		})));
	}

	@DisplayName("상대 진영의 기물을 잡는 경우에는 앞으로 1칸 이동할 수 없다.")
	@Test
	void pawn_can_capture_f6_f7() {
		//given
		Position f6 = new Position(Column.F, Row.SIX);
		Position f7 = new Position(Column.F, Row.SEVEN);
		//then
		assertThatThrownBy(() -> whitePawn.capture(f6, f7, (piece -> {
		})))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("이동할 수 없는 위치입니다.");
	}
}