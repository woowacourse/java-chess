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
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class BishopTest {

    private Piece bishop;

    @BeforeEach
    void setUp() {
        bishop = new Bishop(Team.WHITE);
    }

    @Test
    void 위_왼쪽_대각선으로_갈수_있는지_테스트() {
        Square source = Square.of(3, 2);
        Square target = Square.of(1, 0);
        assertThat(testGetRoute(source, target))
                .isEqualTo(testRoute(Direction.UP_LEFT, source, target, MoveStrategy.BOTH));
    }

    @Test
    void 위_오른쪽_대각선으로_갈수_있는지_테스트() {
        Square source = Square.of(3, 2);
        Square target = Square.of(5, 0);
        assertThat(testGetRoute(Square.of(3, 2), Square.of(5, 0)))
                .isEqualTo(testRoute(Direction.UP_RIGHT, Square.of(3, 2), Square.of(5, 0), MoveStrategy.BOTH));
    }

    @Test
    void 아래_왼쪽_대각선으로_갈수_있는지_테스트() {
        Square source = Square.of(3, 2);
        Square target = Square.of(0, 5);
        assertThat(testGetRoute(source, target))
                .isEqualTo(testRoute(Direction.DOWN_LEFT, source, target, MoveStrategy.BOTH));
    }

    @Test
    void 아래_오른쪽_대각선으로_갈수_있는지_테스트() {
        Square source = Square.of(3, 2);
        Square target = Square.of(8, 7);
        assertThat(testGetRoute(source, target))
                .isEqualTo(testRoute(Direction.DOWN_RIGHT, source, target, MoveStrategy.BOTH));
    }

    @Test
    void 갈수_없는_경로_테스트() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> testGetRoute(Square.of(3, 2), Square.of(1, 1)))
                .withMessage("갈 수 없습니다.");
    }

    private Route testGetRoute(Square source, Square target) {
        return bishop.getRoute(source, target);
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
        assertThat(bishop.getTeam()).isEqualTo(Team.WHITE);
    }

    @Test
    void 폰_타입_반환_테스트() {
        assertThat(bishop.getType()).isEqualTo(Type.BISHOP);
    }
}