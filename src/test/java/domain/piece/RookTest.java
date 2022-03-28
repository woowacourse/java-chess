package domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Position;
import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RookTest {

	@Test
	void checkBlackTeamSymbol() {
		Piece rook = new Rook(Team.BLACK);
		assertThat(rook.getSymbol()).isEqualTo("R");
	}

	@Test
	void checkWhiteTeamSymbol() {
		Piece rook = new Rook(Team.WHITE);
		assertThat(rook.getSymbol()).isEqualTo("r");
	}

	@ParameterizedTest(name = "[{index}] - to {0}, {1}")
	@CsvSource(value = {"8, 4", "4, 5", "3, 4", "4, 3"})
	void validateMovement(int targetRow, int targetCol) {
		Position source = Position.of(4, 4);
		Piece sourceRook = new Rook(Team.BLACK);
		Position target = Position.of(targetRow, targetCol);
		Piece targetRook = new Rook(Team.WHITE);

		assertDoesNotThrow(() -> sourceRook.validateMovement(source, target, targetRook));
	}

	@Test
	void validateDirectionException() {
		Position source = Position.of(1, 1);
		Piece rook = new Rook(Team.BLACK);
		Position target = Position.of(2, 2);
		Piece blank = new Blank();

		assertThatThrownBy(() -> rook.validateMovement(source, target, blank))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 기물은 그곳으로 이동할 수 없습니다.");
	}

	@Test
	void validateCatchAllyException() {
		Position source = Position.of(4, 4);
		Piece sourceRook = new Rook(Team.BLACK);
		Position target = Position.of(5, 4);
		Piece targetRook = new Rook(Team.BLACK);

		assertThatThrownBy(() -> sourceRook.validateMovement(source, target, targetRook))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("같은 팀의 기물을 잡을 수 없습니다.");
	}
}
