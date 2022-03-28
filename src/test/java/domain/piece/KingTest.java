package domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class KingTest {

	@Test
	void checkBlackTeamSymbol() {
		Piece king = new King(Team.BLACK);
		assertThat(king.getSymbol()).isEqualTo("K");
	}

	@Test
	void checkWhiteTeamSymbol() {
		Piece king = new King(Team.WHITE);
		assertThat(king.getSymbol()).isEqualTo("k");
	}

	@ParameterizedTest(name = "[{index}] - to {0}, {1}")
	@CsvSource(value = {"5, 5", "4, 5", "3, 5", "3, 4", "3, 3", "4, 3", "5, 3", "5, 4"})
	void validateMovement(int targetRow, int targetCol) {
		Position source = Position.of(4, 4);
		Piece sourceKing = new King(Team.BLACK);
		Position target = Position.of(targetRow, targetCol);
		Piece targetKing = new King(Team.WHITE);

		assertDoesNotThrow(() -> sourceKing.validateMovement(source, target, targetKing));
	}

	@Test
	void validateDirectionException() {
		Position source = Position.of(1, 1);
		Piece king = new Bishop(Team.BLACK);
		Position target = Position.of(7, 5);
		Piece blank = new Blank();

		assertThatThrownBy(() -> king.validateMovement(source, target, blank))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 기물은 그곳으로 이동할 수 없습니다.");
	}

	@Test
	void validateCatchAllyException() {
		Position source = Position.of(4, 4);
		Piece sourceKing = new King(Team.BLACK);
		Position target = Position.of(3, 5);
		Piece targetKing = new King(Team.BLACK);

		assertThatThrownBy(() -> sourceKing.validateMovement(source, target, targetKing))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("같은 팀의 기물을 잡을 수 없습니다.");
	}
}
