package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Path;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @DisplayName("룩이 이동가능한 전체 위치를 구한다. 상황 : 흰룩-e4 흰피스-없음 검은피스-없음")
    @Test
    void generatePath() {
        Position current = Position.of("e4");
        Piece rook = new Rook(PieceColor.WHITE);
        Path path = rook.generatePaths(current, new Board());

        assertThat(path.positions()).isEqualTo(rookE4WithoutObstacles());
    }

    @DisplayName("룩이 이동가능한 위치를 장애물을 고려하여 구한다. 상황 : 흰룩-e4 흰피스-e2 검은피스-e7")
    @Test
    void generateObstacleConsideredPath() {
        Position current = Position.of("e4");
        Piece rook = new Rook(PieceColor.WHITE);
        Path path = rook.generatePaths(current, BoardFactory.initializeBoard());

        assertThat(path.positions()).isEqualTo(
            rookE4WithObstacles());
    }

    List<Position> rookE4WithoutObstacles() {
        return Arrays.asList(
            Position.of("e5"),
            Position.of("e6"),
            Position.of("e7"),
            Position.of("e8"),
            Position.of("f4"),
            Position.of("g4"),
            Position.of("h4"),
            Position.of("e3"),
            Position.of("e2"),
            Position.of("e1"),
            Position.of("d4"),
            Position.of("c4"),
            Position.of("b4"),
            Position.of("a4")
        );
    }

    List<Position> rookE4WithObstacles() {
        return Arrays.asList(
            Position.of("e5"),
            Position.of("e6"),
            Position.of("e7"),
            Position.of("f4"),
            Position.of("g4"),
            Position.of("h4"),
            Position.of("e3"),
            Position.of("d4"),
            Position.of("c4"),
            Position.of("b4"),
            Position.of("a4")
        );
    }
}