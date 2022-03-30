package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class QueenTest {

	@Test
	void checkBlackTeamSymbol() {
		Piece queen = new Queen(Team.BLACK);
		assertThat(queen.getSymbol()).isEqualTo("Q");
	}

	@Test
	void checkWhiteTeamSymbol() {
		Piece queen = new Queen(Team.WHITE);
		assertThat(queen.getSymbol()).isEqualTo("q");
	}

	@ParameterizedTest
	@CsvSource(value = {"8, 8", "3, 5", "3, 3", "5, 3", "8, 4", "3, 4", "4, 5", "4, 3"})
	void validateMovement(int targetRow, int targetCol) {
		Position source = Position.of(4, 4);
		Piece sourceQueen = new Queen(Team.BLACK);
		Position target = Position.of(targetRow, targetCol);
		Piece targetQueen = new Queen(Team.WHITE);

		assertDoesNotThrow(() -> sourceQueen.validateMovement(source, target, targetQueen));
	}

	@Test
	void validateDirectionException() {
		Position source = Position.of(1, 1);
		Piece queen = new Queen(Team.BLACK);
		Position target = Position.of(3, 2);
		Piece blank = new Blank();

		assertThatThrownBy(() -> queen.validateMovement(source, target, blank))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 기물은 그곳으로 이동할 수 없습니다.");
	}

	@Test
	void validateCatchAllyException() {
		Position source = Position.of(4, 4);
		Piece sourceQueen = new Queen(Team.BLACK);
		Position target = Position.of(5, 4);
		Piece targetQueen = new Queen(Team.BLACK);

		assertThatThrownBy(() -> sourceQueen.validateMovement(source, target, targetQueen))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("같은 팀의 기물을 잡을 수 없습니다.");
	}
}
