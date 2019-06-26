package chess.model;

import chess.model.coordinate.Point;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoardTest {
    private ChessBoard board;

    @BeforeEach
    void setUp() {
        this.board = new ChessBoard();
    }

    @Test
    void isEmpty_테스트() {
        assertThat(board.isEmpty(new Point(3, 3))).isTrue();
    }

    @Test
    void isEmpty_false_테스트() {
        assertThat(board.isEmpty(new Point(1, 1))).isFalse();
    }

    @Test
    void isSameColor_테스트() {
        assertThat(board.isSameColor(new Point(1, 1), ChessPieceColor.WHITE)).isTrue();
    }

    @Test
    void isSameColor_fail_테스트() {
        assertThat(board.isSameColor(new Point(1, 1), ChessPieceColor.BLACK)).isFalse();
    }

    @Test
    void canMove_테스트() {
        assertThat(board.canMove(new Point(1, 2), new Point(1, 4))).isTrue();
    }

    @Test
    void canMove_fail_테스트() {
        assertThat(board.canMove(new Point(1, 2), new Point(2, 3))).isFalse();
    }

    @Test
    void move_테스트() {
        board.move(new Point(1, 2), new Point(1, 3));
        assertThat(board.isEmpty(new Point(1, 2))).isTrue();
        assertThat(board.isEmpty(new Point(1, 3))).isFalse();
    }

    @Test
    void isSmaeType_테스트() {
        assertThat(board.isSameType(new Point(1, 1), ChessPieceType.ROOK)).isTrue();
    }

    @Test
    void isSameType_fail_테스트() {
        assertThat(board.isSameType(new Point(1, 1), ChessPieceType.BISHOP)).isFalse();
    }

    @Test
    void 점수_계산_테스트() {
        assertThat(board.getScore(ChessPieceColor.WHITE)).isEqualTo(38.0, Offset.offset(0.01));
        assertThat(board.getScore(ChessPieceColor.BLACK)).isEqualTo(38.0, Offset.offset(0.01));
    }
}
