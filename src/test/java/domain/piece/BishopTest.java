package domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.Team;
import org.junit.jupiter.api.Test;

class BishopTest {

	@Test
	void checkBlackTeamSymbol() {
		Bishop bishop = new Bishop(Team.BLACK);
		assertThat(bishop.getSymbol()).isEqualTo("B");
	}

	@Test
	void checkWhiteTeamSymbol() {
		Bishop bishop = new Bishop(Team.WHITE);
		assertThat(bishop.getSymbol()).isEqualTo("b");
	}

	@Test
	void validateMovement() {
		Bishop bishop = new Bishop(Team.BLACK);
		assertDoesNotThrow(() -> bishop.validateMovement(Position.of(3, 3), Position.of(2, 2)));
	}

	@Test
	void validateMovementException() {
		Bishop bishop = new Bishop(Team.BLACK);
		assertThatThrownBy(() -> bishop.validateMovement(Position.of(1, 1), Position.of(1, 2)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 기물은 그곳으로 이동할 수 없습니다.");
	}
}
