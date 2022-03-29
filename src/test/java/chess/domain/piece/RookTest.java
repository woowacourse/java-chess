package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Position;
import chess.domain.piece.Blank;
import chess.domain.piece.Rook;
import chess.domain.Team;
import org.junit.jupiter.api.Test;

public class RookTest {

	@Test
	void checkBlackTeamSymbol() {
		Rook rook = new Rook(Team.BLACK);
		assertThat(rook.getSymbol()).isEqualTo("R");
	}

	@Test
	void checkWhiteTeamSymbol() {
		Rook rook = new Rook(Team.WHITE);
		assertThat(rook.getSymbol()).isEqualTo("r");
	}

	@Test
	void validateMovement() {
		Rook rook = new Rook(Team.BLACK);
		assertDoesNotThrow(() -> rook.checkReachable(new Blank(), Position.of(1, 1), Position.of(1, 7)));
	}

	@Test
	void validateMovementException() {
		Rook rook = new Rook(Team.BLACK);
		assertThatThrownBy(() -> rook.checkReachable(new Blank(), Position.of(1, 1), Position.of(2, 2)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 기물은 그곳으로 이동할 수 없습니다.");
	}
}
