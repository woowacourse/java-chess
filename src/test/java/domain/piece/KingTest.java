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

	@Test
	void validateMovement() {
		Position source = Position.of(1, 1);
		Piece sourceKing = new King(Team.BLACK);
		Position target = Position.of(2, 2);
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
}
