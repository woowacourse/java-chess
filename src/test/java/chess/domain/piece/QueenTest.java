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

class QueenTest {

    @DisplayName("퀸이 이동가능한 전체 위치를 구한다. 상황 : 흰퀸-e4 흰피스-없음 검은피스-없음")
    @Test
    void generatePath() {
        Position current = Position.of("e4");
        Piece queen = new Queen(PieceColor.WHITE);
        Path path = queen.generatePaths(current, new Board());

        assertThat(path.positions()).isEqualTo(queenE4WithoutObstacles());
    }

    @DisplayName("퀸이 이동가능한 위치를 장애물을 고려하여 구한다. 상황 : 흰퀸-e4 흰피스-c2,e2,g2 검은피스-b7,e7,h7")
    @Test
    void generateObstacleConsideredPath() {
        Position current = Position.of("e4");
        Piece queen = new Queen(PieceColor.WHITE);
        Path path = queen.generatePaths(current, BoardFactory.initializeBoard());

        assertThat(path.positions())
            .isEqualTo(
                queenE4WithObstacles());
    }

    List<Position> queenE4WithoutObstacles() {
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
            Position.of("a4"),
            Position.of("f5"),
            Position.of("g6"),
            Position.of("h7"),
            Position.of("f3"),
            Position.of("g2"),
            Position.of("h1"),
            Position.of("d3"),
            Position.of("c2"),
            Position.of("b1"),
            Position.of("d5"),
            Position.of("c6"),
            Position.of("b7"),
            Position.of("a8")
        );
    }

    List<Position> queenE4WithObstacles() {
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
            Position.of("a4"),
            Position.of("f5"),
            Position.of("g6"),
            Position.of("h7"),
            Position.of("f3"),
            Position.of("d3"),
            Position.of("d5"),
            Position.of("c6"),
            Position.of("b7")
        );
    }
}