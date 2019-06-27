package chess.domain;

import chess.domain.position.Position;
import chess.domain.position.Positions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionTest {
    private Position position;

    @BeforeEach
    public void setUp() {
        position = Positions.matchWith(2, 2);
    }

    @Test
    public void Position을_잘_생성하는지_확인한다() {
        Position position = Positions.matchWith(8, 8);
        assertThat(position).isExactlyInstanceOf(Position.class);
    }

    @Test
    public void 체스판을_벗어난_값이_입력됐을때_예외를_잘_던지는지_확인한다() {
        assertThatThrownBy(() -> {
            Positions.matchWith(0, 0);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void canMoveBackAndForth_Test() {
        assertThat(position.canMoveBackAndForth(Positions.matchWith(2, 3))).isTrue();
        assertThat(position.canMoveBackAndForth(Positions.matchWith(3, 2))).isFalse();
    }

    @Test
    public void canMoveSideToSide_Test() {
        assertThat(position.canMoveSideToSide(Positions.matchWith(3, 2))).isTrue();
        assertThat(position.canMoveSideToSide(Positions.matchWith(2, 3))).isFalse();
    }

    @Test
    public void canMovePositiveDiagonally_Test() {
        assertThat(position.canMovePositiveDiagonally(Positions.matchWith(3, 3))).isTrue();
        assertThat(position.canMovePositiveDiagonally(Positions.matchWith(2, 3))).isFalse();
    }

    @Test
    public void canMoveNegativeDiagonally_Test() {
        assertThat(position.canMoveNegativeDiagonally(Positions.matchWith(1, 3))).isTrue();
        assertThat(position.canMoveNegativeDiagonally(Positions.matchWith(2, 3))).isFalse();
    }

    @Test
    public void getDistanceSquare_Test() {
        assertThat(position.getDistanceSquare(Positions.matchWith(3, 3))).isEqualTo(2);
    }

    @Test
    public void isInStartingPosition_Test() {
        assertThat(position.isInStartingPosition()).isTrue();
    }

    @Test
    public void backAndForthRoute_Test() {
        Position target = Positions.matchWith(5, 5);
        List<Position> route = new ArrayList<>();
        route.add(Positions.matchWith(3, 3));
        route.add(Positions.matchWith(4, 4));
        assertThat(position.getRoutePosition(target)).isEqualTo(route);
    }

    @Test
    public void sideToSideRoute_Test() {
        Position source = Positions.matchWith(5, 2);
        Position target = Positions.matchWith(2, 5);
        List<Position> route = new ArrayList<>();
        route.add(Positions.matchWith(3, 4));
        route.add(Positions.matchWith(4, 3));
        assertThat(source.getRoutePosition(target)).isEqualTo(route);
    }

    @Test
    public void positiveDiagonallyRoute_Test() {
        Position target = Positions.matchWith(5, 2);
        List<Position> route = new ArrayList<>();
        route.add(Positions.matchWith(3, 2));
        route.add(Positions.matchWith(4, 2));
        assertThat(position.getRoutePosition(target)).isEqualTo(route);
    }

    @Test
    public void negativeDiagonallyRoute_Test() {
        Position target = Positions.matchWith(2, 5);
        List<Position> route = new ArrayList<>();
        route.add(Positions.matchWith(2, 3));
        route.add(Positions.matchWith(2, 4));
        assertThat(position.getRoutePosition(target)).isEqualTo(route);
    }

    @Test
    public void getSameColumnSamePawnCount_Test() {
        List<Position> positions = new ArrayList<>();
        positions.add(Positions.matchWith(1, 1));
        positions.add(Positions.matchWith(2, 1));
        positions.add(Positions.matchWith(3, 1));
        positions.add(Positions.matchWith(3, 1));
        positions.add(Positions.matchWith(4, 1));
        positions.add(Positions.matchWith(4, 1));
        positions.add(Positions.matchWith(4, 1));
        positions.add(Positions.matchWith(5, 1));

        assertThat(Position.getDuplicatedItemsCount(positions)).isEqualTo(5);
    }
}