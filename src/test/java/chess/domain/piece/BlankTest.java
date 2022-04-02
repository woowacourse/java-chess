package chess.domain.piece;

import static chess.domain.board.File.D;
import static chess.domain.board.File.E;
import static chess.domain.board.Rank.FOUR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.board.Position;
import org.junit.jupiter.api.Test;

class BlankTest {

	@Test
	void validateMovement() {
		Piece blank = new Blank();
		Position source = Position.of(FOUR, D);
		Position target = Position.of(FOUR, E);

		assertThatThrownBy(() -> blank.validateMovement(source, target, new Blank()))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 위치에 기물이 없어 움직일 수 없습니다.");
	}

	@Test
	void isBlank() {
		Piece blank = new Blank();

		assertThat(blank.isBlank()).isTrue();
	}
}
