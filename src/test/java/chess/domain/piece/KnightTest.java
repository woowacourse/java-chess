package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnightTest {
    @Test
    @DisplayName("(2,1)방향으로 이동할때 사이의 경로를 반환한다")
    void makePathTest() {
        Knight knight = new Knight(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(5, 4);
        List<ChessBoardPosition> path = knight.getPath(source, target);
        assertThat(path.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("(2,-1)방향으로 이동할때 사이의 경로를 반환한다")
    void makePathTest2() {
        Knight knight = new Knight(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(5, 2);
        List<ChessBoardPosition> path = knight.getPath(source, target);
        assertThat(path.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("(-2,1)방향으로 이동할때 사이의 경로를 반환한다")
    void makePathTest3() {
        Knight knight = new Knight(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(1, 4);
        List<ChessBoardPosition> path = knight.getPath(source, target);
        assertThat(path.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("(-2,-1)방향으로 이동할때 사이의 경로를 반환한다")
    void makePathTest4() {
        Knight knight = new Knight(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(1, 2);
        List<ChessBoardPosition> path = knight.getPath(source, target);
        assertThat(path.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("(1,2)방향으로 이동할때 사이의 경로를 반환한다")
    void makePathTest5() {
        Knight knight = new Knight(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(4, 5);
        List<ChessBoardPosition> path = knight.getPath(source, target);
        assertThat(path.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("(1,-2)방향으로 이동할때 사이의 경로를 반환한다")
    void makePathTest6() {
        Knight knight = new Knight(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(4, 1);
        List<ChessBoardPosition> path = knight.getPath(source, target);
        assertThat(path.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("(-1,-2)방향으로 이동할때 사이의 경로를 반환한다")
    void makePathTest7() {
        Knight knight = new Knight(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(2, 1);
        List<ChessBoardPosition> path = knight.getPath(source, target);
        assertThat(path.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("(-1,2)방향으로 이동할때 사이의 경로를 반환한다")
    void makePathTest8() {
        Knight knight = new Knight(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(2, 5);
        List<ChessBoardPosition> path = knight.getPath(source, target);
        assertThat(path.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("갈 수 없는 방향으로 이동하라 하면 예외를 발생한다")
    void makePathTest9() {
        Knight knight = new Knight(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(7, 4);
        assertThatThrownBy(() -> knight.getPath(source, target)).isInstanceOf(IllegalArgumentException.class);
    }
}
