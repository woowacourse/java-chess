package chess.domain.piece;

import static chess.domain.board.File.A;
import static chess.domain.board.File.D;
import static chess.domain.board.File.E;
import static chess.domain.board.Rank.FOUR;
import static chess.domain.board.Rank.ONE;
import static chess.domain.board.Rank.SEVEN;
import static chess.domain.board.Rank.THREE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class KingTest {

	@ParameterizedTest
	@CsvSource(value = {"FIVE, E", "FOUR, E", "THREE, E", "THREE, D", "THREE, C", "FOUR, C", "FIVE, C", "FIVE, D"})
	void validateMovement(Rank rank, File file) {
		Position source = Position.of(FOUR, D);
		Piece sourceKing = new King(Team.BLACK);
		Position target = Position.of(rank, file);
		Piece targetKing = new King(Team.WHITE);

		assertDoesNotThrow(() -> sourceKing.validateMovement(source, target, targetKing));
	}

	@Test
	void validateDirectionException() {
		Position source = Position.of(ONE, A);
		Piece king = new Bishop(Team.BLACK);
		Position target = Position.of(SEVEN, E);
		Piece blank = new Blank();

		assertThatThrownBy(() -> king.validateMovement(source, target, blank))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 기물이 움직일 수 있는 위치가 아닙니다.");
	}

	@Test
	void validateCatchAllyException() {
		Position source = Position.of(FOUR, D);
		Piece sourceKing = new King(Team.BLACK);
		Position target = Position.of(THREE, E);
		Piece targetKing = new King(Team.BLACK);

		assertThatThrownBy(() -> sourceKing.validateMovement(source, target, targetKing))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("같은 팀의 기물을 잡을 수 없습니다.");
	}
}
