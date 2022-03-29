package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

	@DisplayName("이동하려는 위치에 같은 팀 기물이 있으면 갈 수 없다")
	@Test
	void move_h1_h2() {
		Board board = new Board();

		Position h1 = new Position(Column.H, Row.ONE);
		Position h2 = new Position(Column.H, Row.TWO);

		assertThatThrownBy(() -> board.move(h1, h2))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("같은 팀 기물이 있는 위치로는 이동할 수 없습니다.");
	}

	@DisplayName("이동하려는 위치가 빈 칸이면 이동할 수 있다.")
	@Test
	void move_a2_a4() {
		Board board = new Board();

		Position a2 = new Position(Column.A, Row.TWO);
		Position a4 = new Position(Column.A, Row.FOUR);

		assertThatNoException().isThrownBy(() -> board.move(a2, a4));
	}

	@DisplayName("빈칸의 위치를 출발지로 둘 수 없다.")
	@Test
	void move_blank_exception() {
		Board board = new Board();
		Position a3 = new Position(Column.A, Row.THREE);
		Position a4 = new Position(Column.A, Row.FOUR);
		assertThatThrownBy(() -> board.move(a3, a4))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("이동할 수 있는 기물이 없습니다.");
	}

	@DisplayName("경로에 기물이 있을 경우 움직일 수 없다.")
	@Test
	void move_obstacle_exception() {
		Board board = new Board();
		Position a1 = new Position(Column.A, Row.ONE);
		Position a4 = new Position(Column.A, Row.FOUR);
		assertThatThrownBy(() -> board.move(a1, a4))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("경로에 기물이 있어 움직일 수 없습니다.");
	}

	@DisplayName("초기 상태의 체스판에서 흑색 진영의 점수는 38점이다.")
	@Test
	void calculateScoreOfBlack_38() {
		Board board = new Board();

		assertThat(board.calculateScoreOfBlack()).isEqualTo(38);
	}

	@DisplayName("체스판에서 두 pawn이 한 열에 있을 떄 흑색 진영의 점수는 37점이다.")
	@Test
	void calculateScoreOfBlack_37() {
		Board board = new Board();
		Position b2 = new Position(Column.B, Row.TWO);
		Position b4 = new Position(Column.B, Row.FOUR);
		Position c7 = new Position(Column.C, Row.SEVEN);
		Position c5 = new Position(Column.C, Row.FIVE);
		Position d2 = new Position(Column.D, Row.TWO);
		Position d4 = new Position(Column.D, Row.FOUR);

		board.move(b2, b4);
		board.move(c7, c5);
		board.move(d2, d4);
		board.move(c5, b4);
		assertThat(board.calculateScoreOfBlack()).isEqualTo(37);
	}

	@DisplayName("초기 상태의 체스판에서 백색 진영의 점수는 38점이다.")
	@Test
	void calculateScoreOfWhite_38() {
		Board board = new Board();

		assertThat(board.calculateScoreOfWhite()).isEqualTo(38);
	}

	@DisplayName("체스판에서 두 pawn이 한 열에 있을 떄 백색 진영의 점수는 37점이다.")
	@Test
	void calculateScoreOfWhite_37() {
		Board board = new Board();
		Position b2 = new Position(Column.B, Row.TWO);
		Position b4 = new Position(Column.B, Row.FOUR);
		Position c7 = new Position(Column.C, Row.SEVEN);
		Position c5 = new Position(Column.C, Row.FIVE);
		board.move(b2, b4);
		board.move(c7, c5);
		board.move(b4, c5);
		assertThat(board.calculateScoreOfWhite()).isEqualTo(37);
	}
}
