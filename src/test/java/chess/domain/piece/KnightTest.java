package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;

public class KnightTest {

	private final Knight knight = new Knight(Color.WHITE);

	@DisplayName("나이트는 앞으로 2칸 왼쪽으로 1칸 움직일 수 있다.")
	@Test
	void move_c4_b6() {
		//given
		Position c4 = Position.of(Column.C, Row.FOUR);
		Position b6 = Position.of(Column.B, Row.SIX);
		//then
		assertThat(knight.canMove(c4, b6)).isTrue();
	}

	@DisplayName("나이트는 앞으로 2칸 오른쪽으로 1칸 움직일 수 있다.")
	@Test
	void move_c4_d6() {
		//given
		Position c4 = Position.of(Column.C, Row.FOUR);
		Position b6 = Position.of(Column.B, Row.SIX);
		//then
		assertThat(knight.canMove(c4, b6)).isTrue();
	}

	@DisplayName("나이트는 뒤로 2칸 왼쪽으로 1칸 움직일 수 있다.")
	@Test
	void move_c4_b2() {
		//given
		Position c4 = Position.of(Column.C, Row.FOUR);
		Position b2 = Position.of(Column.B, Row.TWO);
		//then
		assertThat(knight.canMove(c4, b2)).isTrue();
	}

	@DisplayName("나이트는 뒤로 2칸 오른쪽으로 1칸 움직일 수 있다.")
	@Test
	void move_c4_d2() {
		//given
		Position c4 = Position.of(Column.C, Row.FOUR);
		Position d2 = Position.of(Column.D, Row.TWO);
		//then
		assertThat(knight.canMove(c4, d2)).isTrue();
	}

	@DisplayName("나이트는 왼쪽으로 2칸 위로 1칸 움직일 수 있다.")
	@Test
	void move_c4_a5() {
		//given
		Position c4 = Position.of(Column.C, Row.FOUR);
		Position a5 = Position.of(Column.A, Row.FIVE);
		//then
		assertThat(knight.canMove(c4, a5)).isTrue();
	}

	@DisplayName("나이트는 왼쪽으로 2칸 아래로 1칸 움직일 수 있다.")
	@Test
	void move_c4_a3() {
		//given
		Position c4 = Position.of(Column.C, Row.FOUR);
		Position a3 = Position.of(Column.A, Row.THREE);
		//then
		assertThat(knight.canMove(c4, a3)).isTrue();
	}

	@DisplayName("나이트는 오른쪽으로 2칸 위로 1칸 움직일 수 있다.")
	@Test
	void move_c4_e5() {
		//given
		Position c4 = Position.of(Column.C, Row.FOUR);
		Position e5 = Position.of(Column.E, Row.FIVE);
		//then
		assertThat(knight.canMove(c4, e5)).isTrue();
	}

	@DisplayName("나이트는 오른쪽으로 2칸 아래로 1칸 움직일 수 있다.")
	@Test
	void move_c4_e3() {
		//given
		Position c4 = Position.of(Column.C, Row.FOUR);
		Position e3 = Position.of(Column.E, Row.THREE);
		//then
		assertThat(knight.canMove(c4, e3)).isTrue();
	}

	@DisplayName("나이트는 왼쪽으로 2칸만 움직일 수 없다.")
	@Test
	void move_c4_a4() {
		//given
		Position c4 = Position.of(Column.C, Row.FOUR);
		Position a4 = Position.of(Column.A, Row.FOUR);
		//then
		assertThat(knight.canMove(c4, a4)).isFalse();
	}

	@DisplayName("나이트는 오른쪽으로 2칸만 움직일 수 없다.")
	@Test
	void move_c4_e4() {
		//given
		Position c4 = Position.of(Column.C, Row.FOUR);
		Position e4 = Position.of(Column.E, Row.FOUR);
		//then
		assertThat(knight.canMove(c4, e4)).isFalse();
	}

	@DisplayName("나이트는 위로 3칸 왼쪽으로 1칸 움직일 수 없다.")
	@Test
	void move_c4_b7() {
		//given
		Position c4 = Position.of(Column.C, Row.FOUR);
		Position b7 = Position.of(Column.B, Row.SEVEN);
		//then
		assertThat(knight.canMove(c4, b7)).isFalse();
	}
}
