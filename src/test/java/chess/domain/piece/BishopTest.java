package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.BoardFactory;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    // todo : 여기 테스트들은 path에 있어도 될 것 같고?
    @DisplayName("비숍이 이동할 수 있는 모든 위치를 구한다 : 장애물 고려하지 않음")
    @Test
    void generatePath() {
        Position current = Position.of("e4");
        Piece bishop = new Bishop(PieceColor.WHITE);
        Paths paths = new Paths(bishop.findAllPath(current));
        assertThat(paths.pathsToPosition()).isEqualTo(bishopPossiblePositionsWhenE4());
    }

    @DisplayName("비숍이 이동할 수 있는 모든 위치를 구한다 : 장애물 위치 고려함")
    @Test
    void generateObstacleConsideredPath() {
        Position current = Position.of("e4");
        Piece bishop = new Bishop(PieceColor.WHITE);
        Paths paths = new Paths(bishop.findAllPath(current));
        assertThat(paths.removeObstacles(BoardFactory.initializeBoard()).positions()).isEqualTo(bishopObstacleConsideredWhenE4());

    }

    List<Position> bishopPossiblePositionsWhenE4() {
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

    List<Position> bishopObstacleConsideredWhenE4() {
        return Arrays.asList(
                Position.of("f5"),
                Position.of("g6"),
                Position.of("f3"),
                Position.of("d3"),
                Position.of("d5"),
                Position.of("c6")
        );
    }
}