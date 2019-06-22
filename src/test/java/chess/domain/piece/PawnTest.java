package chess.domain.piece;

import chess.domain.direction.Route;
import chess.domain.direction.core.Direction;
import chess.domain.direction.core.MoveStrategy;
import chess.domain.direction.core.Square;
import chess.domain.piece.core.Piece;
import chess.domain.piece.core.Team;
import chess.domain.piece.core.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PawnTest {

    private Piece pawn;

    @BeforeEach
    void setUp() {
        pawn = new Pawn(Team.WHITE);
    }

    @Test
    void 위로_한칸_갈_수_있는지_테스트() {
        assertThat(testGetRoute(Square.of(1, 7), Square.of(1, 6)))
                .isEqualTo(testRoute(Direction.UP, Square.of(1, 7), Square.of(1, 6), MoveStrategy.ONLY_EMPTY));
    }

    @Test
    void 두칸_앞으로_갈수_있는지_테스트() {
        assertThat(testGetRoute(Square.of(1, 7), Square.of(1, 5)))
                .isEqualTo(testRoute(Direction.UP, Square.of(1, 7), Square.of(1, 5), MoveStrategy.ONLY_EMPTY));
    }

    @Test
    void 왼쪽_대각선_갈수_있는지_테스트() {
        assertThat(testGetRoute(Square.of(1, 7), Square.of(0, 6)))
                .isEqualTo(testRoute(Direction.UP_LEFT, Square.of(1, 7), Square.of(0, 6), MoveStrategy.ONLY_ENEMY));
    }

    @Test
    void 오른쪽_대각선_갈수_있는지_테스트() {
        assertThat(testGetRoute(Square.of(1, 7), Square.of(2, 6)))
                .isEqualTo(testRoute(Direction.UP_RIGHT, Square.of(1, 7), Square.of(2, 6), MoveStrategy.ONLY_ENEMY));
    }

    @Test
    void 갈수_없는_경우_테스트() {
        assertThrows(IllegalArgumentException.class, () -> pawn.getRoute(Square.of(1, 7), Square.of(1, 8)));
    }

    private Route testGetRoute(Square source, Square target) {
        return pawn.getRoute(source, target);
    }

    private Route testRoute(Direction direction, Square source, Square target, MoveStrategy moveStrategy) {
        List<Square> route = new ArrayList<>();
        Square currentSquare = source;
        route.add(currentSquare);
        while (currentSquare != target) {
            currentSquare = direction.move(currentSquare);
            route.add(currentSquare);
        }
        return new Route(route, moveStrategy);
    }

    @Test
    void 폰_탐_반환_테스트() {
        assertThat(pawn.getTeam()).isEqualTo(Team.WHITE);
    }

    @Test
    void 폰_타입_반환_테스트() {
        assertThat(pawn.getType()).isEqualTo(Type.PAWN);
    }
}
