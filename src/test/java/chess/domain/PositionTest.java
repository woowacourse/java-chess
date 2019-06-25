package chess.domain;

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
        position = new Position(2, 2);
    }

    @Test
    public void Position_잘_생성하는지_확인한다() {
        Position position = new Position(8, 8);
        assertThat(position).isExactlyInstanceOf(Position.class);
    }

    @Test
    public void 체스판을_벗어난_값이_입력됐을때_예외를_잘_던지는지_확인한다() {
        assertThatThrownBy(() -> {
            new Position(0, 0);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void canMoveBackAndForth_Test() {
        assertThat(position.canMoveBackAndForth(new Position(2, 3))).isTrue();
        assertThat(position.canMoveBackAndForth(new Position(3, 2))).isFalse();
    }

    @Test
    public void canMoveSideToSide_Test() {
        assertThat(position.canMoveSideToSide(new Position(3, 2))).isTrue();
        assertThat(position.canMoveSideToSide(new Position(2, 3))).isFalse();
    }

    @Test
    public void canMovePositiveDiagonally_Test() {
        assertThat(position.canMovePositiveDiagonally(new Position(3, 3))).isTrue();
        assertThat(position.canMovePositiveDiagonally(new Position(2, 3))).isFalse();
    }

    @Test
    public void canMoveNegativeDiagonally_Test() {
        assertThat(position.canMoveNegativeDiagonally(new Position(1, 3))).isTrue();
        assertThat(position.canMoveNegativeDiagonally(new Position(2, 3))).isFalse();
    }

    @Test
    public void getDistanceSquare_Test() {
        assertThat(position.getDistanceSquare(new Position(3, 3))).isEqualTo(2);
    }

    @Test
    public void isInStartingPosition_Test() {
        assertThat(position.isInStartingPosition()).isTrue();
    }

    @Test
    public void getRoutePosition_Test() {
        Position target = new Position(5, 5);
        List<Position> route = new ArrayList<>();
        route.add(new Position(3, 3));
        route.add(new Position(4, 4));
        assertThat(position.getRoutePosition(target)).isEqualTo(route);
    }

    @Test
    public void getRoutePosition_Test2() {
        Position source = new Position(5, 2);
        Position target = new Position(2, 5);
        List<Position> route = new ArrayList<>();
        route.add(new Position(3, 4));
        route.add(new Position(4, 3));
        assertThat(source.getRoutePosition(target)).isEqualTo(route);
    }

    @Test
    public void getRoutePosition_Test3() {
        Position target = new Position(5, 2);
        List<Position> route = new ArrayList<>();
        route.add(new Position(3, 2));
        route.add(new Position(4, 2));
        assertThat(position.getRoutePosition(target)).isEqualTo(route);
    }

    @Test
    public void getRoutePosition_Test4() {
        Position target = new Position(2, 5);
        List<Position> route = new ArrayList<>();
        route.add(new Position(2, 3));
        route.add(new Position(2, 4));
        assertThat(position.getRoutePosition(target)).isEqualTo(route);
    }

    @Test
    public void getSameColumnSamePawnCount_Test() {
        List<Position> positions = new ArrayList<>();
        positions.add(new Position(1,1));
        positions.add(new Position(2,1));
        positions.add(new Position(3,1));
        positions.add(new Position(3,1));
        positions.add(new Position(4,1));
        positions.add(new Position(4,1));
        positions.add(new Position(4,1));
        positions.add(new Position(5,1));

        assertThat(Position.getDuplicatedItemsCount(positions)).isEqualTo(5);
    }
}