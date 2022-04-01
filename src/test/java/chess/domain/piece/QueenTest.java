package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueenTest {
    @Test
    @DisplayName("(1, 0) 방향으로 이동할때 경로 반환")
    void makePathTest() {
        Queen queen = new Queen(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(5, 3);
        List<ChessBoardPosition> path = queen.getPath(source, target);
        assertThat(path.get(0)).isEqualTo(ChessBoardPosition.of(4, 3));
    }

    @Test
    @DisplayName("(-1, 0) 방향으로 이동할때 경로 반환")
    void makePathTest2() {
        Queen queen = new Queen(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(1, 3);
        List<ChessBoardPosition> path = queen.getPath(source, target);
        assertThat(path.get(0)).isEqualTo(ChessBoardPosition.of(2, 3));
    }

    @Test
    @DisplayName("(0, 1) 방향으로 이동할때 경로 반환")
    void makePathTest3() {
        Queen queen = new Queen(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(3, 5);
        List<ChessBoardPosition> path = queen.getPath(source, target);
        assertThat(path.get(0)).isEqualTo(ChessBoardPosition.of(3, 4));
    }

    @Test
    @DisplayName("(0, -1) 방향으로 이동할때 경로 반환")
    void makePathTest4() {
        Queen queen = new Queen(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(3, 1);
        List<ChessBoardPosition> path = queen.getPath(source, target);
        assertThat(path.get(0)).isEqualTo(ChessBoardPosition.of(3, 2));
    }

    @Test
    @DisplayName("(1, 1) 방향으로 이동할때 경로 반환")
    void makePatQueen() {
        Queen queen = new Queen(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(5, 5);
        List<ChessBoardPosition> path = queen.getPath(source, target);
        assertThat(path.get(0)).isEqualTo(ChessBoardPosition.of(4, 4));
    }

    @Test
    @DisplayName("(1, -1) 방향으로 이동할때 경로 반환")
    void makePathTest6() {
        Queen queen = new Queen(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(5, 1);
        List<ChessBoardPosition> path = queen.getPath(source, target);
        assertThat(path.get(0)).isEqualTo(ChessBoardPosition.of(4, 2));
    }

    @Test
    @DisplayName("(-1, 1) 방향으로 이동할때 경로 반환")
    void makePathTest7() {
        Queen queen = new Queen(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(1, 5);
        List<ChessBoardPosition> path = queen.getPath(source, target);
        assertThat(path.get(0)).isEqualTo(ChessBoardPosition.of(2, 4));
    }

    @Test
    @DisplayName("(-1, -1) 방향으로 이동할때 경로 반환")
    void makePathTest8() {
        Queen queen = new Queen(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(1, 1);
        List<ChessBoardPosition> path = queen.getPath(source, target);
        assertThat(path.get(0)).isEqualTo(ChessBoardPosition.of(2, 2));
    }

    @Test
    @DisplayName("못 가는 방향으로 이동할때 경로 반환")
    void makePathTest9() {
        Queen queen = new Queen(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(5, 4);
        assertThatThrownBy(() -> queen.getPath(source, target)).isInstanceOf(IllegalArgumentException.class);
    }
}
