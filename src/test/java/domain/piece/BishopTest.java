package domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {

	@Test
	void checkBlackTeamSymbol() {
		Piece bishop = new Bishop(Team.BLACK);
		assertThat(bishop.getSymbol()).isEqualTo("B");
	}

	@Test
	void checkWhiteTeamSymbol() {
		Piece bishop = new Bishop(Team.WHITE);
		assertThat(bishop.getSymbol()).isEqualTo("b");
	}

	@ParameterizedTest(name = "[{index}] - to {0}, {1}")
	@CsvSource(value = {"8, 8", "3, 5", "3, 3", "5, 3"})
	void validateMovement(int targetRow, int targetCol) {
		Position source = Position.of(4, 4);
		Piece sourceBishop = new Bishop(Team.BLACK);
		Position target = Position.of(targetRow, targetCol);
		Piece targetBishop = new Bishop(Team.WHITE);

		assertDoesNotThrow(() -> sourceBishop.validateMovement(source, target, targetBishop));
	}

	@Test
	void validateDirectionException() {
		Position source = Position.of(1, 1);
		Piece bishop = new Bishop(Team.BLACK);
		Position target = Position.of(1, 2);
		Piece blank = new Blank();

		assertThatThrownBy(() -> bishop.validateMovement(source, target, blank))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 기물은 그곳으로 이동할 수 없습니다.");
	}

	@Test
	void validateCatchAllyException() {
		Position source = Position.of(4, 4);
		Piece sourceBishop = new Bishop(Team.BLACK);
		Position target = Position.of(3, 5);
		Piece targetBishop = new Bishop(Team.BLACK);

		assertThatThrownBy(() -> sourceBishop.validateMovement(source, target, targetBishop))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("같은 팀의 기물을 잡을 수 없습니다.");
	}
}
