package domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Position;
import chess.domain.piece.Queen;
import chess.domain.piece.Team;
import org.junit.jupiter.api.Test;

public class QueenTest {

	@Test
	void checkBlackTeamSymbol() {
		Queen queen = new Queen(Team.BLACK);
		assertThat(queen.getSymbol()).isEqualTo("Q");
	}

	@Test
	void checkWhiteTeamSymbol() {
		Queen queen = new Queen(Team.WHITE);
		assertThat(queen.getSymbol()).isEqualTo("q");
	}

	@Test
	void validateMovement() {
		Queen queen = new Queen(Team.BLACK);
		assertDoesNotThrow(() -> queen.validateMovement(Position.of(1, 1), Position.of(8, 8)));
	}

	@Test
	void validateMovementException() {
		Queen queen = new Queen(Team.BLACK);
		assertThatThrownBy(() -> queen.validateMovement(Position.of(1, 1), Position.of(3, 2)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 기물은 그곳으로 이동할 수 없습니다.");
	}
}
