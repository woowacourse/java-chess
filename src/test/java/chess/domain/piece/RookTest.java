package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Paths;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @DisplayName("룩이 이동가능한 전체 위치를 구한다. 상황 : 흰룩-e4 흰피스-없음 검은피스-없음")
    @Test
    void generatePath() {
        Position current = Position.ofName("e4");
        Piece rook = new Rook(PieceColor.WHITE);
        Paths paths = new Paths();
        paths = paths.findAllPath(rook, current);
        assertThat(paths.pathsToPosition()).isEqualTo(rookE4WithoutObstacles());
    }

    @DisplayName("룩이 이동가능한 위치를 장애물을 고려하여 구한다. 상황 : 흰룩-e4 흰피스-e2 검은피스-e7")
    @Test
    void generateObstacleConsideredPath() {
        Position current = Position.ofName("e4");
        Piece rook = new Rook(PieceColor.WHITE);
        Paths paths = new Paths();
        paths = paths.findAllPath(rook, current);
        Board board = BoardFactory.initializeBoard();
        assertThat(paths.removeObstacles(rook, board).positions()).isEqualTo(
            rookE4WithObstacles());
    }

    List<Position> rookE4WithoutObstacles() {
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
            Position.ofName("a4")
        );
    }

    List<Position> rookE4WithObstacles() {
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
            Position.ofName("a4")
        );
    }
}