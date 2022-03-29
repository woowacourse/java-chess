package domain.board;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.Team;
import org.junit.jupiter.api.Test;

class BoardTest {

	@Test
	void checkStartBoard() {
		Board board = new Board();

		assertAll(
				() -> assertThat(board.isBlank(Position.of(4, 4))).isTrue(),
				() -> assertThat(board.isBlank(Position.of(1, 1))).isFalse(),
				() -> assertThat(board.isBlank(Position.of(8, 8))).isFalse()
		);
	}

	@Test
	void moveWithWrongSource() {
		Board board = new Board();
		Position source = Position.of(4, 4);
		Position target = Position.of(5, 5);
		assertThatThrownBy(() -> board.move(source, target))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 위치에 기물이 없습니다.");
	}

	@Test
	void moveToSameTeam() {
		Board board = new Board();
		Position source = Position.of(1, 5);
		Position target = Position.of(1, 4);
		assertThatThrownBy(() -> board.move(source, target))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("같은 팀의 기물을 잡을 수 없습니다.");
	}

	@Test
	void moveBishopBlock() {
		Board board = new Board();
		Position source = Position.of(1, 3);
		Position target = Position.of(3, 5);
		assertThatThrownBy(() -> board.move(source, target))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 위치로 기물을 옮길 수 없습니다.");
	}

	@Test
	void moveKnight() {
		Board board = new Board();
		Position source = Position.of(1, 2);
		Position target = Position.of(3, 3);
		assertDoesNotThrow(() -> board.move(source, target));
	}

	@Test
	void moveToEnemy() {
		Board board = new Board();

		Position whitePawn = Position.of(2, 4);
		Position whitePawnTarget = Position.of(4, 4);
		board.move(whitePawn, whitePawnTarget);

		Position blackPawn = Position.of(7, 5);
		Position blackPawnTarget = Position.of(5, 5);
		board.move(blackPawn, blackPawnTarget);

		assertDoesNotThrow(() -> board.move(whitePawnTarget, blackPawnTarget));
	}

	@Test
	void calculateScore() {
		Board board = new Board();

		assertThat(board.calculateScore(Team.BLACK)).isEqualTo(38);
	}

	@Test
	void calculateScoreWithSameColumnPawn() {
		Board board = new Board();

		Position whitePawn = Position.of(2, 4);
		Position whitePawnTarget = Position.of(4, 4);
		board.move(whitePawn, whitePawnTarget);

		Position blackPawn = Position.of(7, 5);
		Position blackPawnTarget = Position.of(5, 5);
		board.move(blackPawn, blackPawnTarget);

		board.move(whitePawnTarget, blackPawnTarget);

		assertThat(board.calculateScore(Team.WHITE)).isEqualTo(37);
	}
}
