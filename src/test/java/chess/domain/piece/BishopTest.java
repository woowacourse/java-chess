package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {
    @Test
    @DisplayName("(1, 1) 방향으로 이동할때 경로 반환")
    void makePathTest() {
        Bishop bishop = new Bishop(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(5, 5);
        List<ChessBoardPosition> path = bishop.getPath(source, target);
        assertThat(path.get(0)).isEqualTo(ChessBoardPosition.of(4, 4));
    }

    @Test
    @DisplayName("(1, -1) 방향으로 이동할때 경로 반환")
    void makePathTest2() {
        Bishop bishop = new Bishop(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(5, 1);
        List<ChessBoardPosition> path = bishop.getPath(source, target);
        assertThat(path.get(0)).isEqualTo(ChessBoardPosition.of(4, 2));
    }

    @Test
    @DisplayName("(-1, 1) 방향으로 이동할때 경로 반환")
    void makePathTest3() {
        Bishop bishop = new Bishop(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(1, 5);
        List<ChessBoardPosition> path = bishop.getPath(source, target);
        assertThat(path.get(0)).isEqualTo(ChessBoardPosition.of(2, 4));
    }

    @Test
    @DisplayName("(-1, -1) 방향으로 이동할때 경로 반환")
    void makePathTest4() {
        Bishop bishop = new Bishop(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(1, 1);
        List<ChessBoardPosition> path = bishop.getPath(source, target);
        assertThat(path.get(0)).isEqualTo(ChessBoardPosition.of(2, 2));
    }

    @Test
    @DisplayName("갈 수 없는 방향으로 이동할때 예외 발생")
    void makePathTest5() {
        Bishop bishop = new Bishop(Team.BLACK);
        ChessBoardPosition source = ChessBoardPosition.of(3, 3);
        ChessBoardPosition target = ChessBoardPosition.of(3, 5);
        assertThatThrownBy(() -> bishop.getPath(source, target)).isInstanceOf(IllegalArgumentException.class);
    }
}
