package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.coordinate.Coordinate;
import chess.domain.piece.Knight;

public class BoardTest {

	@Test
	@DisplayName("move시 해당 칸에 체스 말이 없으면 에러를 발생시킨다.")
	void move() {
		Board board = Board.create();

		assertThatThrownBy(() -> board.move("a3", "a4"))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
    @DisplayName("정상적으로 move시 해당 칸에 체스 말이 있는지 확인")
	void normal_move() {
		Board board = Board.create();

		Board newBoard = board.move("b1", "c3");

		assertThat(newBoard.findByCoordinate(Coordinate.of("c3"))).isInstanceOf(Knight.class);
	}

	@Test
	@DisplayName("왕이 둘다 살아있으면 true를 반환")
	void is_both_king_lived() {
		Board board = Board.create();

		assertThat(board.isBothKingAlive()).isTrue();
	}
}
