package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Camp;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;

public class RookTest {

	private final Rook rook = new Rook(Camp.WHITE);

	@DisplayName("룩은 앞으로 움직일 수 있다.")
	@Test
	void move_d1_d6() {
		//given
		Position d1 = new Position(Column.D, Row.ONE);
		Position d6 = new Position(Column.D, Row.SIX);
		//when
		//then
		assertThat(rook.canMove(d1, d6)).isTrue();
	}

	@DisplayName("룩은 뒤로 움직일 수 있다.")
	@Test
	void move_d6_d1() {
		//given
		Position d6 = new Position(Column.D, Row.SIX);
		Position d1 = new Position(Column.D, Row.ONE);
		//when
		//then
		assertThat(rook.canMove(d6, d1)).isTrue();
	}

	@DisplayName("룩은 우로 움직일 수 있다.")
	@Test
	void move_d1_e1() {
		//given
		Position d1 = new Position(Column.D, Row.ONE);
		Position e1 = new Position(Column.E, Row.ONE);
		//when
		//then
		assertThat(rook.canMove(d1, e1)).isTrue();
	}

	@DisplayName("룩은 좌로 움직일 수 있다.")
	@Test
	void move_e1_d1() {
		//given
		Position e1 = new Position(Column.E, Row.ONE);
		Position d1 = new Position(Column.D, Row.ONE);
		//when
		//then
		assertThat(rook.canMove(e1, d1)).isTrue();
	}

	@DisplayName("룩은 대각선으로 움직일 수 없다.")
	@Test
	void move_d1_g4() {
		//given
		Position d1 = new Position(Column.D, Row.ONE);
		Position g4 = new Position(Column.G, Row.FOUR);
		//when
		//then
		assertThat(rook.canMove(d1, g4)).isFalse();
	}

	@DisplayName("룩은 직선이 아닌 방향으로는 움직일 수 없다.")
	@Test
	void move_d1_f2() {
		//given
		Position d1 = new Position(Column.D, Row.ONE);
		Position f2 = new Position(Column.F, Row.TWO);
		//when
		//then
		assertThat(rook.canMove(d1, f2)).isFalse();
	}
}
