package chess.domain.board;

import chess.domain.piece.Bishop;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {
    @DisplayName("보드가 초기화 되었을 때 체스말이 없는 곳은 null 값을 가진다.")
    @Test
    void nullPositionTest() {
        Board board = new Board();
        assertThat(board.findPieceFromPosition(Position.of(Horizontal.C, Vertical.SIX))).isNull();
    }

    @DisplayName("보드가 생성되고 체스말의 위치가 올바른지 확인한다.")
    @Test
    void boardCreateTest() {
        Board board = new Board();
        assertThat(board.findPieceFromPosition(Position.of(Horizontal.C, Vertical.EIGHT))).isInstanceOf(Bishop.class);
    }
}
