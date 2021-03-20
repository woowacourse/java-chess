package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.BoardFactory;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    // todo : 여기 테스트들은 path에 있어도 될 것 같고?
    @DisplayName("흰비숍-e4 흰피스-없음 검은피스-없음")
    @Test
    void generatePath() {
        Position current = Position.ofName("e4");
        Piece bishop = new Bishop(PieceColor.WHITE);
        Paths paths = new Paths(bishop.findAllPath(current));
        assertThat(paths.pathsToPosition()).isEqualTo(bishopPossiblePositionsWhenE4());
    }

    @DisplayName("흰비숍-e4 흰피스-c2,g2 검은피스-b7,h7")
    @Test
    void generateObstacleConsideredPath() {
        Position current = Position.ofName("e4");
        Piece bishop = new Bishop(PieceColor.WHITE);
        Paths paths = new Paths(bishop.findAllPath(current));
        assertThat(paths.removeObstacles(BoardFactory.initializeBoard()).positions()).isEqualTo(bishopObstacleConsideredWhenE4());

    }

    List<Position> bishopPossiblePositionsWhenE4() {
        return Arrays.asList(
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

    List<Position> bishopObstacleConsideredWhenE4() {
        return Arrays.asList(
                Position.ofName("f5"),
                Position.ofName("g6"),
                Position.ofName("h7"),
                Position.ofName("f3"),
                Position.ofName("d3"),
                Position.ofName("d5"),
                Position.ofName("c6"),
                Position.ofName("h7")
        );
    }
}