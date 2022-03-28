package domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

	@ParameterizedTest
	@CsvSource(value = {"4, 4, 8, 8", "4, 4, 3, 5", "4, 4, 3, 3", "4, 4, 5, 3"})
	void validateMovement(int startRow, int startCol, int endRow, int endCol) {
		Bishop bishop = new Bishop(Team.BLACK);
		assertDoesNotThrow(() -> bishop.checkReachable(Position.of(startRow, startCol), Position.of(endRow, endCol)));
	}

	@Test
	void validateMovementException() {
		Bishop bishop = new Bishop(Team.BLACK);
		assertThatThrownBy(() -> bishop.checkReachable(Position.of(1, 1), Position.of(1, 2)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 기물은 그곳으로 이동할 수 없습니다.");
	}
}
