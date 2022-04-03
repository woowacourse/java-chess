package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PawnTest {
    List<ChessBoardPosition> blackTeamInitialPosition;
    List<ChessBoardPosition> whiteTeamInitialPosition;

    @BeforeEach
    void setUp() {
         blackTeamInitialPosition = List.of(
                ChessBoardPosition.of(1, 7),
                ChessBoardPosition.of(2, 7),
                ChessBoardPosition.of(3, 7),
                ChessBoardPosition.of(4, 7),
                ChessBoardPosition.of(5, 7),
                ChessBoardPosition.of(6, 7),
                ChessBoardPosition.of(7, 7),
                ChessBoardPosition.of(8, 7)
        );
        whiteTeamInitialPosition = List.of(
                ChessBoardPosition.of(1, 2),
                ChessBoardPosition.of(2, 2),
                ChessBoardPosition.of(3, 2),
                ChessBoardPosition.of(4, 2),
                ChessBoardPosition.of(5, 2),
                ChessBoardPosition.of(6, 2),
                ChessBoardPosition.of(7, 2),
                ChessBoardPosition.of(8, 2)
        );
    }
    @Test
    @DisplayName("블랙팀 pawn 초기위치에서 (0,-2)방향으로 이동할때 사이의 경로를 반환한다")
    void makePathTest() {
        Pawn pawn = new Pawn(Team.BLACK,
                List.of(ChessBoardPosition.ofDirection(0, -1)), Collections.emptyList(),
                List.of(ChessBoardPosition.ofDirection(1, -1),
                        ChessBoardPosition.ofDirection(-1, -1)),
                blackTeamInitialPosition);
        ChessBoardPosition source = ChessBoardPosition.of(1, 7);
        ChessBoardPosition target = ChessBoardPosition.of(1, 5);
        List<ChessBoardPosition> path = pawn.getPath(source, target);
        assertThat(path.get(0)).isEqualTo(ChessBoardPosition.of(1, 6));
    }

    @Test
    @DisplayName("블랙팀 pawn 초기위치에서 (0,-1)방향으로 이동할때 사이의 경로를 반환한다")
    void makePathTest2() {
        Pawn pawn = new Pawn(Team.BLACK,
                List.of(ChessBoardPosition.ofDirection(0, -1)), Collections.emptyList(),
                List.of(ChessBoardPosition.ofDirection(1, -1),
                        ChessBoardPosition.ofDirection(-1, -1)),
                blackTeamInitialPosition);
        ChessBoardPosition source = ChessBoardPosition.of(1, 7);
        ChessBoardPosition target = ChessBoardPosition.of(1, 6);
        List<ChessBoardPosition> path = pawn.getPath(source, target);
        assertThat(path.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("블랙팀 pawn 초기위치 아닌 곳에서 (0,-1)방향으로 이동할때 사이의 경로를 반환한다")
    void makePathTest3() {
        Pawn pawn = new Pawn(Team.BLACK,
                List.of(ChessBoardPosition.ofDirection(0, -1)), Collections.emptyList(),
                List.of(ChessBoardPosition.ofDirection(1, -1),
                        ChessBoardPosition.ofDirection(-1, -1)),
                blackTeamInitialPosition);
        ChessBoardPosition source = ChessBoardPosition.of(1, 6);
        ChessBoardPosition target = ChessBoardPosition.of(1, 5);
        List<ChessBoardPosition> path = pawn.getPath(source, target);
        assertThat(path.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("블랙팀 pawn 초기위치 아닌 곳에서 (0,-2)방향으로 이동하려하면 예외를 발생한다")
    void makePathTest4() {
        Pawn pawn = new Pawn(Team.BLACK,
                List.of(ChessBoardPosition.ofDirection(0, -1)), Collections.emptyList(),
                List.of(ChessBoardPosition.ofDirection(1, -1),
                        ChessBoardPosition.ofDirection(-1, -1)),
                blackTeamInitialPosition);
        ChessBoardPosition source = ChessBoardPosition.of(1, 6);
        ChessBoardPosition target = ChessBoardPosition.of(1, 4);
        assertThatThrownBy(() -> pawn.getPath(source, target)).isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    @DisplayName("화이트팀 pawn 초기위치에서 (0,2)방향으로 이동할때 사이의 경로를 반환한다")
    void makePathTest5() {
        Pawn pawn = new Pawn(Team.WHITE, Collections.emptyList(),
                List.of(ChessBoardPosition.ofDirection(0, 1)),
                List.of(ChessBoardPosition.ofDirection(1, 1),
                        ChessBoardPosition.ofDirection(-1, 1)),
                whiteTeamInitialPosition);
        ChessBoardPosition source = ChessBoardPosition.of(1, 2);
        ChessBoardPosition target = ChessBoardPosition.of(1, 4);
        List<ChessBoardPosition> path = pawn.getPath(source, target);
        assertThat(path.get(0)).isEqualTo(ChessBoardPosition.of(1, 3));
    }

    @Test
    @DisplayName("화이트팀 pawn 초기위치에서 (0,1)방향으로 이동할때 사이의 경로를 반환한다")
    void makePathTest6() {
        Pawn pawn = new Pawn(Team.WHITE, Collections.emptyList(),
                List.of(ChessBoardPosition.ofDirection(0, 1)),
                List.of(ChessBoardPosition.ofDirection(1, 1),
                        ChessBoardPosition.ofDirection(-1, 1)),
                whiteTeamInitialPosition);
        ChessBoardPosition source = ChessBoardPosition.of(1, 2);
        ChessBoardPosition target = ChessBoardPosition.of(1, 3);
        List<ChessBoardPosition> path = pawn.getPath(source, target);
        assertThat(path.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("화이트팀 pawn 초기위치 아닌 곳에서 (0,1)방향으로 이동할때 사이의 경로를 반환한다")
    void makePathTest7() {
        Pawn pawn = new Pawn(Team.WHITE, Collections.emptyList(),
                List.of(ChessBoardPosition.ofDirection(0, 1)),
                List.of(ChessBoardPosition.ofDirection(1, 1),
                        ChessBoardPosition.ofDirection(-1, 1)),
                whiteTeamInitialPosition);
        ChessBoardPosition source = ChessBoardPosition.of(1, 3);
        ChessBoardPosition target = ChessBoardPosition.of(1, 4);
        List<ChessBoardPosition> path = pawn.getPath(source, target);
        assertThat(path.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("화이트팀 pawn 초기위치 아닌 곳에서 (0,2)방향으로 이동하려하면 예외를 발생한다")
    void makePathTest8() {
        Pawn pawn = new Pawn(Team.WHITE, Collections.emptyList(),
                List.of(ChessBoardPosition.ofDirection(0, 1)),
                List.of(ChessBoardPosition.ofDirection(1, 1),
                        ChessBoardPosition.ofDirection(-1, 1)),
                whiteTeamInitialPosition);
        ChessBoardPosition source = ChessBoardPosition.of(1, 3);
        ChessBoardPosition target = ChessBoardPosition.of(1, 5);
        assertThatThrownBy(() -> pawn.getPath(source, target)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이동 불가능 한곳이면 예외를 발생한다")
    void makePathTest9() {
        Pawn pawn = new Pawn(Team.BLACK,
                List.of(ChessBoardPosition.ofDirection(0, -1)), Collections.emptyList(),
                List.of(ChessBoardPosition.ofDirection(1, -1),
                        ChessBoardPosition.ofDirection(-1, -1)),
                blackTeamInitialPosition);
        ChessBoardPosition source = ChessBoardPosition.of(1, 7);
        ChessBoardPosition target = ChessBoardPosition.of(1, 4);
        assertThatThrownBy(() -> pawn.getPath(source, target)).isInstanceOf(IllegalArgumentException.class);
    }
}
