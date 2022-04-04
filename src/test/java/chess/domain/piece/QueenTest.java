package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Position;
import chess.domain.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

	@ParameterizedTest
	@CsvSource(value = {"4, 4, 8, 8", "4, 4, 3, 5", "4, 4, 3, 3", "4, 4, 5, 3"})
	void validateMovement(int startRow, int startCol, int endRow, int endCol) {
		Queen queen = new Queen(Team.BLACK);
		assertDoesNotThrow(() -> queen.checkReachable(new Blank(), Position.of(startRow, startCol), Position.of(endRow, endCol)));
	}

	@Test
	void validateMovementException() {
		Queen queen = new Queen(Team.BLACK);
		assertThatThrownBy(() -> queen.checkReachable(new Blank(), Position.of(1, 1), Position.of(3, 2)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 기물은 그곳으로 이동할 수 없습니다.");
	}
}
