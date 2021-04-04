package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.feature.Color;
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

    @DisplayName("blank를 이동하려고 하면 에러를 반환하는지")
    @Test
    void move() {
        assertThatThrownBy(() -> blank.move(chessBoard, direction, targetPosition))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage(BLANK_MOVE_ERROR);
    }

    @DisplayName("blank에게 이동 가능 여부를 물어보려고 하면 에러를 반환하는지")
    @Test
    void isMovable() {
        assertThatThrownBy(() -> blank.isMovable(chessBoard, direction, targetPosition))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage(BLANK_MOVE_ERROR);
    }

    @DisplayName("blank에게 갈 수 있는 방향을 물어보면 에러를 반환하는지")
    @Test
    void directions() {
        assertThatThrownBy(() -> blank.directions())
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage(BLANK_MOVE_ERROR);
    }
}
