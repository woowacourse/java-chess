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

	@Test
	void validateMovement() {
		Position source = Position.of(1, 1);
		Piece sourceKnight = new Knight(Team.BLACK);
		Position target = Position.of(3, 2);
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
}
