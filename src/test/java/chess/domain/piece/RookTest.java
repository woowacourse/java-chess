package chess.domain.piece;

import static chess.domain.board.File.A;
import static chess.domain.board.File.B;
import static chess.domain.board.File.D;
import static chess.domain.board.Rank.FIVE;
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

public class RookTest {

	@ParameterizedTest
	@CsvSource(value = {"EIGHT, D", "FOUR, E", "THREE, D", "FOUR, C"})
	void validateMovement(Rank rank, File file) {
		Position source = Position.of(FOUR, D);
		Piece sourceRook = new Rook(Team.BLACK);
		Position target = Position.of(rank, file);
		Piece targetRook = new Rook(Team.WHITE);

		assertDoesNotThrow(() -> sourceRook.validateMovement(source, target, targetRook));
	}

	@Test
	void validateDirectionException() {
		Position source = Position.of(ONE, A);
		Piece rook = new Rook(Team.BLACK);
		Position target = Position.of(TWO, B);
		Piece blank = new Blank();

		assertThatThrownBy(() -> rook.validateMovement(source, target, blank))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 기물이 움직일 수 있는 위치가 아닙니다.");
	}

	@Test
	void validateCatchAllyException() {
		Position source = Position.of(FOUR, D);
		Piece sourceRook = new Rook(Team.BLACK);
		Position target = Position.of(FIVE, D);
		Piece targetRook = new Rook(Team.BLACK);

		assertThatThrownBy(() -> sourceRook.validateMovement(source, target, targetRook))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("같은 팀의 기물을 잡을 수 없습니다.");
	}
}
