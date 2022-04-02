package chess.domain.piece;

import static chess.domain.board.File.A;
import static chess.domain.board.File.B;
import static chess.domain.board.File.D;
import static chess.domain.board.Rank.FIVE;
import static chess.domain.board.Rank.FOUR;
import static chess.domain.board.Rank.ONE;
import static chess.domain.board.Rank.THREE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class QueenTest {

	@ParameterizedTest
	@CsvSource(value = {"EIGHT, H", "THREE, E", "THREE, C", "FIVE, C", "EIGHT, D", "THREE, D", "FOUR, E", "FOUR, C"})
	void validateMovement(Rank rank, File file) {
		Position source = Position.of(FOUR, D);
		Piece sourceQueen = new Queen(Team.BLACK);
		Position target = Position.of(rank, file);
		Piece targetQueen = new Queen(Team.WHITE);

		assertDoesNotThrow(() -> sourceQueen.validateMovement(source, target, targetQueen));
	}

	@Test
	void validateDirectionException() {
		Position source = Position.of(ONE, A);
		Piece queen = new Queen(Team.BLACK);
		Position target = Position.of(THREE, B);
		Piece blank = new Blank();

		assertThatThrownBy(() -> queen.validateMovement(source, target, blank))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 기물이 움직일 수 있는 위치가 아닙니다.");
	}

	@Test
	void validateCatchAllyException() {
		Position source = Position.of(FOUR, D);
		Piece sourceQueen = new Queen(Team.BLACK);
		Position target = Position.of(FIVE, D);
		Piece targetQueen = new Queen(Team.BLACK);

		assertThatThrownBy(() -> sourceQueen.validateMovement(source, target, targetQueen))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("같은 팀의 기물을 잡을 수 없습니다.");
	}
}
