package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Direction;
import chess.domain.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.Blank.BLANK_MOVE_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BlankTest {
	private Blank blank;
	private ChessBoard chessBoard;
	private Direction direction;
	private Position targetPosition;

	@BeforeEach
	void setUp() {
		blank = new Blank(Position.of("b4"));
		chessBoard = new ChessBoard();
		direction = Direction.EAST;
		targetPosition = Position.of("b6");
	}

	@DisplayName("blank를 정확한 필드를 가진 상태로 생성 하는지")
	@Test
	void create() {
		assertThat(blank).isEqualTo(new Blank(Position.of("b4")));
		assertThat(blank.isSameColor(Color.NO_COLOR)).isTrue();
	}

	@Test
	void move() {
		assertThatThrownBy(() -> blank.move(chessBoard, direction, targetPosition))
				.isInstanceOf(UnsupportedOperationException.class)
				.hasMessage(BLANK_MOVE_ERROR);
	}

	@Test
	void isMovable() {
		assertThatThrownBy(() -> blank.isMovable(chessBoard, direction, targetPosition))
				.isInstanceOf(UnsupportedOperationException.class)
				.hasMessage(BLANK_MOVE_ERROR);
	}

	@Test
	void directions() {
		assertThatThrownBy(() -> blank.directions())
				.isInstanceOf(UnsupportedOperationException.class)
				.hasMessage(BLANK_MOVE_ERROR);
	}
}
