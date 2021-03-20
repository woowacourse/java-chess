package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.BoardFactory;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    // todo : 여기 테스트들은 path에 있어도 될 것 같고?
    @DisplayName("흰나이트-e4 흰피스-없음 검은피스-없음")
    @Test
    void generatePath() {
        Position current = Position.ofName("e4");
        Piece knight = new Knight(PieceColor.WHITE);
        Paths paths = new Paths(knight.findAllPath(current));

        assertThat(paths.pathsToPosition()).isEqualTo(knightE4WithoutObstacles());
    }

    @DisplayName("흰나이트-e4 ")
    @Test
    void generateObstacleConsideredPath() {
        Position current = Position.ofName("e4");
        Piece bishop = new Bishop(PieceColor.WHITE);
        Paths paths = new Paths(bishop.findAllPath(current));
        assertThat(paths.removeObstacles(BoardFactory.initializeBoard()).positions()).isEqualTo(
                knightE4WithObstacles());

    }

    List<Position> knightE4WithoutObstacles() {
        return Arrays.asList(
                Position.ofName("d6"),
                Position.ofName("f6"),
                Position.ofName("g5"),
                Position.ofName("g3"),
                Position.ofName("d2"),
                Position.ofName("f2"),
                Position.ofName("c5"),
                Position.ofName("c3")
                );
    }

    List<Position> knightE4WithObstacles() {
        return Arrays.asList(
                Position.ofName("d6"),
                Position.ofName("f6"),
                Position.ofName("g5"),
                Position.ofName("g3"),
                Position.ofName("c5"),
                Position.ofName("c3")
        );
    }
}