package domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Position;
import chess.domain.piece.Pawn;
import chess.domain.piece.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PawnTest {

	@Test
	void checkBlackTeamSymbol() {
		Pawn pawn = new Pawn(Team.BLACK);
		assertThat(pawn.getSymbol()).isEqualTo("P");
	}

	@Test
	void checkWhiteTeamSymbol() {
		Pawn pawn = new Pawn(Team.WHITE);
		assertThat(pawn.getSymbol()).isEqualTo("p");
	}

	@ParameterizedTest
	@CsvSource(value = {"3, 3, BLACK", "3, 4, BLACK", "3, 5, BLACK", "5, 3, WHITE", "5, 4, WHITE", "5, 5, WHITE"})
	void validateMovement(int targetRow, int targetColumn, Team team) {
		Pawn pawn = new Pawn(team);
		assertDoesNotThrow(() -> pawn.checkReachable(Position.of(4, 4), Position.of(targetRow, targetColumn)));
	}

	@ParameterizedTest
	@CsvSource(value = {"3, 3, WHITE", "3, 4, WHITE", "3, 5, WHITE", "5, 3, BLACK", "5, 4, BLACK", "5, 5, BLACK"})
	void validateMovementException(int targetRow, int targetColumn, Team team) {
		Pawn pawn = new Pawn(team);
		assertThatThrownBy(() -> pawn.checkReachable(Position.of(4, 4), Position.of(targetRow, targetColumn)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 기물은 그곳으로 이동할 수 없습니다.");
	}
}
