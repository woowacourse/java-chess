package chess.domain.board.position;

import chess.domain.direction.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @Test
    @DisplayName("세로열 가로열로 위치 객체를 만들 수 있다.")
    void createPositionByHorizontalAndVerticalTest() {
        Position position = Position.of(Horizontal.H, Vertical.ONE);

        assertThat(position).isInstanceOf(Position.class);
        assertThat(position).isEqualTo(Position.of("h1"));
    }

    @Test
    @DisplayName("문자열로 위치 객체를 만들 수 있다.")
    void createPositionByStringTest() {
        Position position = Position.of("a1");

        assertThat(position).isInstanceOf(Position.class);
        assertThat(position).isEqualTo(Position.of(Horizontal.A, Vertical.ONE));
    }

    @Test
    @DisplayName("다음 방향으로 갈 때 보드를 벗어나지 않는 유효한 위치인지 판단한다.")
    void isValidPositionTest() {
        Position position = Position.of("a8");

        boolean isFalse = position.isValidPosition(Direction.UP);
        boolean isTrue = position.isValidPosition(Direction.DOWN);

        assertThat(isFalse).isFalse();
        assertThat(isTrue).isTrue();
    }

    @Test
    @DisplayName("다음 방향으로 갈 때 보드를 벗어났는지 판단한다.")
    void isEscapePositionTest() {
        Position position = Position.of("a8");

        boolean isTrue = position.isEscapePosition(Direction.UP);
        boolean isFalse = position.isEscapePosition(Direction.DOWN);

        assertThat(isTrue).isTrue();
        assertThat(isFalse).isFalse();
    }

    @Test
    @DisplayName("두 위치가 일직선인지 판단한다.")
    void isStraightTest() {
        Position source = Position.of("a1");
        Position target1 = Position.of("a2");
        Position target2 = Position.of("b2");

        boolean isTrue = source.isStraight(target1);
        boolean isFalse = source.isStraight(target2);

        assertThat(isTrue).isTrue();
        assertThat(isFalse).isFalse();
    }

    @Test
    @DisplayName("두 위치가 대각선인지 판단한다.")
    void isDiagonalTest() {
        Position source = Position.of("a1");
        Position target1 = Position.of("b2");
        Position target2 = Position.of("a2");

        boolean isTrue = source.isDiagonal(target1);
        boolean isFalse = source.isDiagonal(target2);

        assertThat(isTrue).isTrue();
        assertThat(isFalse).isFalse();
    }

    @Test
    @DisplayName("두 위치가 나이트가 갈 수 있는 위치인지 테스트한다.")
    void isKnightDirectionTest() {
        //given
        Position source = Position.of("d5");
        Position target1 = Position.of("a1");

        //when
        boolean isFalse = source.isKnightDirection(target1);

        //then
        List<Position> targets = Arrays.asList(
                Position.of("c3"),
                Position.of("c7"),
                Position.of("b4"),
                Position.of("b6"),
                Position.of("e3"),
                Position.of("e7"),
                Position.of("f4"),
                Position.of("f6")
        );

        for (Position target : targets) {
            assertThat(source.isKnightDirection(target)).isTrue();
        }

        assertThat(isFalse).isFalse();
    }

    @Test
    @DisplayName("거리 구할 수 있다.")
    void getDistanceTest() {
        //given
        Position source = Position.of("d5");
        int distance1 = source.getDistance(Position.of("d4")); // 1
        int distance2 = source.getDistance(Position.of("e6")); // 1
        int knightDistance = source.getDistance(Position.of("c3")); // 1

        //then
        assertThat(distance1).isEqualTo(1);
        assertThat(distance2).isEqualTo(1);
        assertThat(knightDistance).isEqualTo(1);
    }

    @Test
    @DisplayName("방향에 따른 다음 위치를 반환한다.")
    void nextTest() {
        //given
        Direction straight = Direction.UP;
        Direction diagonal = Direction.DOWN_LEFT;
        Direction knight = Direction.KNIGHT_UP_RIGHT;
        Position source = Position.of("d4");

        //when
        Position nextPosition1 = source.next(straight);
        Position nextPosition2 = source.next(diagonal);
        Position nextPosition3 = source.next(knight);

        //then
        assertThat(nextPosition1).isEqualTo(Position.of("d5"));
        assertThat(nextPosition2).isEqualTo(Position.of("c3"));
        assertThat(nextPosition3).isEqualTo(Position.of("e6"));
    }

    @Test
    @DisplayName("위치의 가로(x좌표) 객체 가져온다.")
    void getHorizontalTest() {
        //given
        Position position = Position.of("a2");

        //when
        Horizontal horizontal = position.getHorizontal();

        //then
        assertThat(horizontal).isEqualTo(Horizontal.A);
    }

    @Test
    @DisplayName("위치의 세로(y좌표) 객체 가져온다.")
    void getVertical() {
        //given
        Position position = Position.of("a2");

        //when
        Vertical vertical = position.getVertical();

        //then
        assertThat(vertical).isEqualTo(Vertical.TWO);
    }

    @Test
    @DisplayName("위치의 가로(x좌표) 값 가져온다")
    void getHorizontalIndex() {
        //given
        Position position = Position.of("a2");

        //when
        int horizontalIndex = position.getHorizontalIndex();

        //then
        assertThat(horizontalIndex).isEqualTo(1);
    }

    @Test
    void getVerticalIndex() {
        //given
        Position position = Position.of("a2");

        //when
        int verticalIndex = position.getVerticalIndex();

        //then
        assertThat(verticalIndex).isEqualTo(2);
    }
}