package domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.board.Position;
import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.Test;

class BlankTest {

	@Test
	void validateMovement() {
		Piece blank = new Blank();
		Position source = Position.of(4, 4);
		Position target = Position.of(4, 5);

		assertThatThrownBy(() -> blank.validateMovement(source, target, new Blank()))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 클래스는 이동이 불가능 합니다.");
	}

	@Test
	void isBlank() {
		Piece blank = new Blank();

		assertThat(blank.isBlank()).isTrue();
	}
}
