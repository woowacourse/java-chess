package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.BoardFactory;
import chess.domain.board.Paths;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {


    @DisplayName("퀸이 이동가능한 전체 위치를 구한다. 상황 : 흰퀸-e4 흰피스-없음 검은피스-없음")
    @Test
    void generatePath() {
        Position current = Position.ofName("e4");
        Piece queen = new Queen(PieceColor.WHITE);
        Paths paths = new Paths();
        paths = paths.findAllPath(queen, current);

        assertThat(paths.pathsToPosition()).isEqualTo(queenE4WithoutObstacles());
    }

    @DisplayName("퀸이 이동가능한 위치를 장애물을 고려하여 구한다. 상황 : 흰퀸-e4 흰피스-c2,e2,g2 검은피스-b7,e7,h7")
    @Test
    void generateObstacleConsideredPath() {
        Position current = Position.ofName("e4");
        Piece queen = new Queen(PieceColor.WHITE);
        Paths paths = new Paths();
        paths = paths.findAllPath(queen, current);
        assertThat(paths.removeObstacles(queen, BoardFactory.initializeBoard()).positions())
                .isEqualTo(
                        queenE4WithObstacles());
    }

    List<Position> queenE4WithoutObstacles() {
        return Arrays.asList(
                Position.ofName("e5"),
                Position.ofName("e6"),
                Position.ofName("e7"),
                Position.ofName("e8"),
                Position.ofName("f4"),
                Position.ofName("g4"),
                Position.ofName("h4"),
                Position.ofName("e3"),
                Position.ofName("e2"),
                Position.ofName("e1"),
                Position.ofName("d4"),
                Position.ofName("c4"),
                Position.ofName("b4"),
                Position.ofName("a4"),
                Position.ofName("f5"),
                Position.ofName("g6"),
                Position.ofName("h7"),
                Position.ofName("f3"),
                Position.ofName("g2"),
                Position.ofName("h1"),
                Position.ofName("d3"),
                Position.ofName("c2"),
                Position.ofName("b1"),
                Position.ofName("d5"),
                Position.ofName("c6"),
                Position.ofName("b7"),
                Position.ofName("a8")
        );
    }

    List<Position> queenE4WithObstacles() {
        return Arrays.asList(
                Position.ofName("e5"),
                Position.ofName("e6"),
                Position.ofName("e7"),
                Position.ofName("f4"),
                Position.ofName("g4"),
                Position.ofName("h4"),
                Position.ofName("e3"),
                Position.ofName("d4"),
                Position.ofName("c4"),
                Position.ofName("b4"),
                Position.ofName("a4"),
                Position.ofName("f5"),
                Position.ofName("g6"),
                Position.ofName("h7"),
                Position.ofName("f3"),
                Position.ofName("d3"),
                Position.ofName("d5"),
                Position.ofName("c6"),
                Position.ofName("b7")
        );
    }
}