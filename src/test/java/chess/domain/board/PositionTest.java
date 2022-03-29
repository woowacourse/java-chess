package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {

	@DisplayName("a1을 수평 반전 하면 h1이 된다.")
	@Test
	void flip_horizontal_a1_to_h1() {
		//given
		Position a1 = new Position(Column.A, Row.ONE);
		Position expected = new Position(Column.H, Row.ONE);

		//when
		Position actual = a1.flipHorizontally();

		//then
		assertThat(actual).isEqualTo(expected);
	}

	@DisplayName("a1을 수직 반전 하면 a8이 된다.")
	@Test
	void flip_horizontal_a1_to_a8() {
		//given
		Position a1 = new Position(Column.A, Row.ONE);
		Position expected = new Position(Column.A, Row.EIGHT);

		//when
		Position actual = a1.flipVertically();

		//then
		assertThat(actual).isEqualTo(expected);
	}

	@DisplayName("a1을 대각 반전 하면 h8이 된다.")
	@Test
	void flip_diagonally_a1_to_h8() {
		//given
		Position a1 = new Position(Column.A, Row.ONE);
		Position expected = new Position(Column.H, Row.EIGHT);

		//when
		Position actual = a1.flipDiagonally();

		//then
		assertThat(actual).isEqualTo(expected);
	}

	@DisplayName("체스판 좌표로 비교했을 때 a1은 a8보다 크다.")
	@Test
	void a1_greater_than_a8() {
		//given
		Position A1 = new Position(Column.A, Row.ONE);
		Position A8 = new Position(Column.A, Row.EIGHT);

		// when & then
		assertThat(A1).isGreaterThan(A8);
	}

	@DisplayName("체스판 좌표로 비교했을 때 a2은 b2보다 작다.")
	@Test
	void a2_less_than_b2() {
		//given
		Position A2 = new Position(Column.A, Row.TWO);
		Position B2 = new Position(Column.B, Row.TWO);
		//when
		//then
		assertThat(A2).isLessThan(B2);
	}

	@DisplayName("체스판 좌표로 비교했을 때 a7은 a8보다 크다.")
	@Test
	void a7_greater_than_a8() {
		//given
		Position A7 = new Position(Column.A, Row.SEVEN);
		Position A8 = new Position(Column.A, Row.EIGHT);
		//when
		//then
		assertThat(A7).isGreaterThan(A8);
	}

	@DisplayName("체스판 좌표로 비교했을 때 h1은 b2보다 크다.")
	@Test
	void h1_greater_than_b2() {
		//given
		Position B2 = new Position(Column.B, Row.TWO);
		Position H1 = new Position(Column.H, Row.ONE);
		//when
		//then
		assertThat(H1).isGreaterThan(B2);
	}

	@DisplayName("a1에서 a3 사이에는 a2가 있다.")
	@Test
	void path_a1_a3() {
		//given
		Position a1 = new Position(Column.A, Row.ONE);
		Position a3 = new Position(Column.A, Row.THREE);
		//then
		assertThat(a1.existObstacleToOtherPosition(a3,
			(position -> Objects.equals(position, new Position(Column.A, Row.TWO))))).isTrue();
	}

	@DisplayName("a1에서 c1 사이에는 b1이 있다.")
	@Test
	void path_a1_c1() {
		//given
		Position a1 = new Position(Column.A, Row.ONE);
		Position c1 = new Position(Column.C, Row.ONE);
		//then
		assertThat(a1.existObstacleToOtherPosition(c1,
			(position -> Objects.equals(position, new Position(Column.B, Row.ONE))))).isTrue();
	}

	@DisplayName("e1에서 c3 사이에는 d2가 있다.")
	@Test
	void path_e1_c3() {
		//given
		Position e1 = new Position(Column.E, Row.ONE);
		Position c3 = new Position(Column.C, Row.THREE);
		//then
		assertThat(e1.existObstacleToOtherPosition(c3,
			(position -> Objects.equals(position, new Position(Column.D, Row.TWO))))).isTrue();
	}
}
