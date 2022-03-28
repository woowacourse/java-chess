package domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Position;
import chess.domain.piece.Blank;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class KnightTest {

	@Test
	void checkBlackTeamSymbol() {
		Piece knight = new Knight(Team.BLACK);
		assertThat(knight.getSymbol()).isEqualTo("N");
	}

	@Test
	void checkWhiteTeamSymbol() {
		Piece knight = new Knight(Team.WHITE);
		assertThat(knight.getSymbol()).isEqualTo("n");
	}

	@ParameterizedTest(name = "[{index}] - to {0}, {1}")
	@CsvSource(value = {"2, 5", "2, 3", "6, 5", "6, 3", "5, 6", "3, 6", "5, 2", "3, 2"})
	void validateMovement(int targetRow, int targetCol) {
		Position source = Position.of(4, 4);
		Piece sourceKnight = new Knight(Team.BLACK);
		Position target = Position.of(targetRow, targetCol);
		Piece targetKnight = new Knight(Team.WHITE);

		assertDoesNotThrow(() -> sourceKnight.validateMovement(source, target, targetKnight));
	}

	@Test
	void validateDirectionException() {
		Position source = Position.of(1, 1);
		Piece knight = new Knight(Team.BLACK);
		Position target = Position.of(2, 2);
		Piece blank = new Blank();

		assertThatThrownBy(() -> knight.validateMovement(source, target, blank))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 기물은 그곳으로 이동할 수 없습니다.");
	}

	@Test
	void validateCatchAllyException() {
		Position source = Position.of(4, 4);
		Piece sourceKnight = new Knight(Team.BLACK);
		Position target = Position.of(2, 5);
		Piece targetKnight = new Knight(Team.BLACK);

		assertThatThrownBy(() -> sourceKnight.validateMovement(source, target, targetKnight))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("같은 팀의 기물을 잡을 수 없습니다.");
	}
}
