package domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Position;
import chess.domain.piece.Knight;
import chess.domain.piece.Team;
import org.junit.jupiter.api.Test;

public class KnightTest {

	@Test
	void checkBlackTeamSymbol() {
		Knight knight = new Knight(Team.BLACK);
		assertThat(knight.getSymbol()).isEqualTo("N");
	}

	@Test
	void checkWhiteTeamSymbol() {
		Knight knight = new Knight(Team.WHITE);
		assertThat(knight.getSymbol()).isEqualTo("n");
	}

	@Test
	void validateMovement() {
		Knight knight = new Knight(Team.BLACK);
		assertDoesNotThrow(() -> knight.validateMovement(Position.of(1, 1), Position.of(3, 2)));
	}

	@Test
	void validateMovementException() {
		Knight knight = new Knight(Team.BLACK);
		assertThatThrownBy(() -> knight.validateMovement(Position.of(1, 1), Position.of(2, 2)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 기물은 그곳으로 이동할 수 없습니다.");
	}
}
