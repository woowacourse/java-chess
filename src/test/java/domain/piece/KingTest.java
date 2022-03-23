package domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Position;
import chess.domain.piece.King;
import chess.domain.piece.Team;
import org.junit.jupiter.api.Test;

public class KingTest {

	@Test
	void checkBlackTeamSymbol() {
		King king = new King(Team.BLACK);
		assertThat(king.getSymbol()).isEqualTo("K");
	}

	@Test
	void checkWhiteTeamSymbol() {
		King king = new King(Team.WHITE);
		assertThat(king.getSymbol()).isEqualTo("k");
	}

	@Test
	void validateMovement() {
		King king = new King(Team.BLACK);
		assertDoesNotThrow(() -> king.validateMovement(Position.of(1, 1), Position.of(2, 2)));
	}

	@Test
	void validateMovementException() {
		King king = new King(Team.BLACK);
		assertThatThrownBy(() -> king.validateMovement(Position.of(1, 1), Position.of(7, 5)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 기물은 그곳으로 이동할 수 없습니다.");
	}
}
