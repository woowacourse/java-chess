package chess.domain.piece;

import static chess.domain.board.File.A;
import static chess.domain.board.File.B;
import static chess.domain.board.File.D;
import static chess.domain.board.File.E;
import static chess.domain.board.Rank.FOUR;
import static chess.domain.board.Rank.ONE;
import static chess.domain.board.Rank.TWO;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class KnightTest {

	@ParameterizedTest
	@CsvSource(value = {"TWO, E", "TWO, C", "SIX, E", "SIX, C", "FIVE, F", "THREE, F", "FIVE, B", "THREE, B"})
	void validateMovement(Rank rank, File file) {
		Position source = Position.of(FOUR, D);
		Piece sourceKnight = new Knight(Team.BLACK);
		Position target = Position.of(rank, file);
		Piece targetKnight = new Knight(Team.WHITE);

		assertDoesNotThrow(() -> sourceKnight.validateMovement(source, target, targetKnight));
	}

	@Test
	void validateDirectionException() {
		Position source = Position.of(ONE, A);
		Piece knight = new Knight(Team.BLACK);
		Position target = Position.of(TWO, B);
		Piece blank = new Blank();

		assertThatThrownBy(() -> knight.validateMovement(source, target, blank))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 기물이 움직일 수 있는 위치가 아닙니다.");
	}

	@Test
	void validateCatchAllyException() {
		Position source = Position.of(FOUR, D);
		Piece sourceKnight = new Knight(Team.BLACK);
		Position target = Position.of(TWO, E);
		Piece targetKnight = new Knight(Team.BLACK);

		assertThatThrownBy(() -> sourceKnight.validateMovement(source, target, targetKnight))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("같은 팀의 기물을 잡을 수 없습니다.");
	}
}
