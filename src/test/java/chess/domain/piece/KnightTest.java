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

class KnightTest {
    private Piece knight;

    @BeforeEach
    void setUp() {
        knight = new Knight(Team.WHITE);
    }

    @Test
    void 위_위_왼쪽_대각선으로_갈수_있는지_테스트() {
        assertThat(testGetRoute(Square.of(3, 2), Square.of(2, 0)))
                .isEqualTo(testRoute(Direction.DOUBLE_UP_LEFT, Square.of(3, 2), Square.of(2, 0), MoveStrategy.BOTH));
    }

    @Test
    void 위_위_오른쪽_대각선으로_갈수_있는지_테스트() {
        assertThat(testGetRoute(Square.of(3, 2), Square.of(4, 0)))
                .isEqualTo(testRoute(Direction.DOUBLE_UP_RIGHT, Square.of(3, 2), Square.of(4, 0), MoveStrategy.BOTH));
    }

    @Test
    void 왼_왼_위쪽_대각선으로_갈수_있는지_테스트() {
        assertThat(testGetRoute(Square.of(3, 2), Square.of(1, 1)))
                .isEqualTo(testRoute(Direction.DOUBLE_LEFT_UP, Square.of(3, 2), Square.of(1, 1), MoveStrategy.BOTH));
    }

    @Test
    void 왼_왼_아래쪽_대각선으로_갈수_있는지_테스트() {
        assertThat(testGetRoute(Square.of(3, 2), Square.of(1, 3)))
                .isEqualTo(testRoute(Direction.DOUBLE_LEFT_DOWN, Square.of(3, 2), Square.of(1, 3), MoveStrategy.BOTH));
    }

    @Test
    void 아래_아래_왼쪽_대각선으로_갈수_있는지_테스트() {
        assertThat(testGetRoute(Square.of(3, 2), Square.of(2, 4)))
                .isEqualTo(testRoute(Direction.DOUBLE_DOWN_LEFT, Square.of(3, 2), Square.of(2, 4), MoveStrategy.BOTH));
    }

    @Test
    void 아래_아래_오른쪽_대각선으로_갈수_있는지_테스트() {
        assertThat(testGetRoute(Square.of(3, 2), Square.of(4, 4)))
                .isEqualTo(testRoute(Direction.DOUBLE_DOWN_RIGHT, Square.of(3, 2), Square.of(4, 4), MoveStrategy.BOTH));
    }

    @Test
    void 오른_오른_위_대각선으로_갈수_있는지_테스트() {
        assertThat(testGetRoute(Square.of(3, 2), Square.of(5, 1)))
                .isEqualTo(testRoute(Direction.DOUBLE_RIGHT_UP, Square.of(3, 2), Square.of(5, 1), MoveStrategy.BOTH));
    }

    @Test
    void 오른_오른_아래_대각선으로_갈수_있는지_테스트() {
        assertThat(testGetRoute(Square.of(3, 2), Square.of(5, 3)))
                .isEqualTo(testRoute(Direction.DOUBLE_RIGHT_DOWN, Square.of(3, 2), Square.of(5, 3), MoveStrategy.BOTH));
    }

    private Route testGetRoute(Square source, Square target) {
        return knight.getRoute(source, target);
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
    void 갈수_없는_경로_테스트() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> testGetRoute(Square.of(3, 2), Square.of(1, 0)))
                .withMessage("갈 수 없습니다.");
    }

    @Test
    void 폰_탐_반환_테스트() {
        assertThat(knight.getTeam()).isEqualTo(Team.WHITE);
    }

    @Test
    void 폰_타입_반환_테스트() {
        assertThat(knight.getType()).isEqualTo(Type.KNIGHT);
    }
}