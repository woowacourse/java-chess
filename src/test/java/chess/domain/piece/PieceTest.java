package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PieceTest {

	private Position position;
	private Piece piece;

	@BeforeEach
	void setUp() {
		position = new Position("a4");
		piece = TestPieceFactory.createKing(position, Color.WHITE);
	}

	@DisplayName("move piece 원래 position으로 이동하려하면 예외처리")
	@Test
	void move_when_same_position_throw_exception() {
		assertThatThrownBy(() -> piece.move(position))
				.isInstanceOf(UnsupportedOperationException.class)
				.hasMessage("같은 위치로 이동할 수 없습니다.");
	}

	@DisplayName("getResource WHITE 일 경우 소문자로 반환")
	@Test
	void getResource_when_white_return_lower_case() {
		assertThat(piece.getResource()).isEqualTo("k");
	}

	@DisplayName("getResource BLACK 일 경우 대문자로 반환")
	@Test
	void getResource_when_black_return_upper_case() {
		Piece piece = TestPieceFactory.createKing(new Position("a4"), Color.BLACK);
		assertThat(piece.getResource()).isEqualTo("K");
	}
}