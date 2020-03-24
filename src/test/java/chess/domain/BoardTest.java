package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {
    @Test
    @DisplayName("보드 생성 실패")
    void constructFail() {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < 63; i++) {
            points.add(new Point("a", "b", new Blank(PieceType.BLANK)));
        }
        assertThatThrownBy(() -> {
            new Board(points);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage("옳바르지 않은 갯수입니다.");
    }

    @Test
    @DisplayName("보드 생성 성공")
    void construct() {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < 64; i++) {
            points.add(new Point("a", "b", new Blank(PieceType.BLANK)));
        }
        Board board = new Board(points);
        assertThat(board).isNotNull();
        assertThat(board.size()).isEqualTo(64);
    }
}
