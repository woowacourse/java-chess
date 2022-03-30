package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Position;
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

	@ParameterizedTest
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
				.hasMessageContaining("해당 기물이 움직일 수 있는 위치가 아닙니다.");
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
