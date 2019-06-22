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

class KingTest {
    private Piece king;

    @BeforeEach
    void setUp() {
        king = new King(Team.WHITE);
    }

    @Test
    void 위로_갈수_있는지_테스트() {
        assertThat(testGetRoute(Square.of(3, 2), Square.of(3, 1)))
                .isEqualTo(testRoute(Direction.UP, Square.of(3, 2), Square.of(3, 1), MoveStrategy.BOTH));
    }

    @Test
    void 위_왼쪽_대각선으로_갈수_있는지_테스트() {
        assertThat(testGetRoute(Square.of(3, 2), Square.of(2, 1)))
                .isEqualTo(testRoute(Direction.UP_LEFT, Square.of(3, 2), Square.of(2, 1), MoveStrategy.BOTH));
    }

    @Test
    void 위_오른쪽_대각선으로_갈수_있는지_테스트() {
        assertThat(testGetRoute(Square.of(3, 2), Square.of(4, 1)))
                .isEqualTo(testRoute(Direction.UP_RIGHT, Square.of(3, 2), Square.of(4, 1), MoveStrategy.BOTH));
    }

    @Test
    void 오른쪽으로_갈수_있는지_테스트() {
        assertThat(testGetRoute(Square.of(3, 2), Square.of(4, 2)))
                .isEqualTo(testRoute(Direction.RIGHT, Square.of(3, 2), Square.of(4, 2), MoveStrategy.BOTH));
    }

    @Test
    void 왼쪽으로_갈수_있는지_테스트() {
        assertThat(testGetRoute(Square.of(3, 2), Square.of(2, 2)))
                .isEqualTo(testRoute(Direction.LEFT, Square.of(3, 2), Square.of(2, 2), MoveStrategy.BOTH));
    }

    @Test
    void 아래로_갈수_있는지_테스트() {
        assertThat(testGetRoute(Square.of(3, 2), Square.of(3, 3)))
                .isEqualTo(testRoute(Direction.DOWN, Square.of(3, 2), Square.of(3, 3), MoveStrategy.BOTH));
    }

    @Test
    void 아래_왼쪽_대각선으로_갈수_있는지_테스트() {
        assertThat(testGetRoute(Square.of(3, 2), Square.of(2, 3)))
                .isEqualTo(testRoute(Direction.DOWN_LEFT, Square.of(3, 2), Square.of(2, 3), MoveStrategy.BOTH));
    }

    @Test
    void 아래_오른쪽_대각선으로_갈수_있는지_테스트() {
        assertThat(testGetRoute(Square.of(3, 2), Square.of(4, 3)))
                .isEqualTo(testRoute(Direction.DOWN_RIGHT, Square.of(3, 2), Square.of(4, 3), MoveStrategy.BOTH));
    }

    @Test
    void 갈수_없는_경로_테스트() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> testGetRoute(Square.of(3, 2), Square.of(1, 1)))
                .withMessage("갈 수 없습니다.");
    }

    private Route testGetRoute(Square source, Square target) {
        return king.getRoute(source, target);
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
        assertThat(king.getTeam()).isEqualTo(Team.WHITE);
    }

    @Test
    void 폰_타입_반환_테스트() {
        assertThat(king.getType()).isEqualTo(Type.KING);
    }
}