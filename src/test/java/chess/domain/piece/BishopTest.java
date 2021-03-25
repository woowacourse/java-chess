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

class BishopTest {

    @DisplayName("비숍이 이동가능한 전체 위치를 구한다. 상황 : 흰비숍-e4 흰피스-없음 검은피스-없음")
    @Test
    void generatePath() {
        Position current = Position.of("e4");
        Piece bishop = new Bishop(PieceColor.WHITE);
        Path path = bishop.generatePaths(current, new Board());
        assertThat(path.positions()).isEqualTo(bishopE4WithoutObstacles());
    }

    @DisplayName("비숍이 이동가능한 위치를 장애물을 고려하여 구한다. 상황 : 흰비숍-e4 흰피스-c2,g2 검은피스-b7,h7")
    @Test
    void generateObstacleConsideredPath() {
        Position current = Position.of("e4");
        Piece bishop = new Bishop(PieceColor.WHITE);

        Path path = bishop.generatePaths(current, BoardFactory.initializeBoard());
        assertThat(path.positions()).isEqualTo(bishopE4WithObstacles());
    }

    List<Position> bishopE4WithoutObstacles() {
        return Arrays.asList(
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

    List<Position> bishopE4WithObstacles() {
        return Arrays.asList(
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