package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class KingTest {
    @Test
    @DisplayName("(1,0)방향으로 이동할때 사이의 경로를 반환한다")
    void makePathTest() {
        King king = new King(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(4, 3);
        List<ChessBoardPosition> path = king.getPath(source, target);
        assertThat(path.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("(-1,0)방향으로 이동할때 사이의 경로를 반환한다")
    void makePathTest2() {
        King king = new King(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(2, 3);
        List<ChessBoardPosition> path = king.getPath(source, target);
        assertThat(path.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("(0,1)방향으로 이동할때 사이의 경로를 반환한다")
    void makePathTest3() {
        King king = new King(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(3, 4);
        List<ChessBoardPosition> path = king.getPath(source, target);
        assertThat(path.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("(0,-1)방향으로 이동할때 사이의 경로를 반환한다")
    void makePathTest4() {
        King king = new King(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(2, 3);
        List<ChessBoardPosition> path = king.getPath(source, target);
        assertThat(path.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("(1,1)방향으로 이동할때 사이의 경로를 반환한다")
    void makePathTest5() {
        King king = new King(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(4, 4);
        List<ChessBoardPosition> path = king.getPath(source, target);
        assertThat(path.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("(-1,1)방향으로 이동할때 사이의 경로를 반환한다")
    void makePathTest6() {
        King king = new King(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(2, 4);
        List<ChessBoardPosition> path = king.getPath(source, target);
        assertThat(path.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("(1,-1)방향으로 이동할때 사이의 경로를 반환한다")
    void makePathTest7() {
        King king = new King(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(4, 2);
        List<ChessBoardPosition> path = king.getPath(source, target);
        assertThat(path.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("(-1,-1)방향으로 이동할때 사이의 경로를 반환한다")
    void makePathTest8() {
        King king = new King(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(2, 2);
        List<ChessBoardPosition> path = king.getPath(source, target);
        assertThat(path.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("갈 수 없는 방향으로 이동하라 하면 예외를 발생한다")
    void makePathTest9() {
        King king = new King(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(7, 2);
        assertThatThrownBy(() -> king.getPath(source, target)).isInstanceOf(IllegalArgumentException.class);
    }
}
