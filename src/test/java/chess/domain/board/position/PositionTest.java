package chess.domain.board.position;

import chess.domain.direction.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest(name = "다음 방향으로 갈 때 보드를 벗어나지 않는 유효한 위치인지 판단한다.")
    @CsvSource({"UP, false", "DOWN, true"})
    void isValidPositionTest(String input, boolean isValidPosition) {
        Position source = Position.of("a8");

        Direction direction = Direction.valueOf(input);

        assertThat(source.isValidPosition(direction)).isEqualTo(isValidPosition);
    }

    @ParameterizedTest(name = "다음 방향으로 갈 때 보드를 벗어났는지 판단한다.")
    @CsvSource({"UP, true", "DOWN, false"})
    void isEscapePositionTest(String input, boolean isEscapePosition) {
        //given
        Position position = Position.of("a8");

        //when
        Direction direction = Direction.valueOf(input);

        //then
        assertThat(position.isEscapePosition(direction)).isEqualTo(isEscapePosition);
    }

    @ParameterizedTest(name = "대상 위치가 현재 위체에서부터 일직선인지 판단한다.")
    @CsvSource({"a2, true", "b2, false"})
    void isStraightTest(String input, boolean isStraight) {
        //given
        Position source = Position.of("a1");
        Position target = Position.of(input);

        assertThat(source.isStraight(target)).isEqualTo(isStraight);
    }

    @Test
    @DisplayName("대상 위치가 현재 위체에서부터 대각선인지 판단한다.")
    void isDiagonalTest() {
        Position source = Position.of("a1");
        Position target1 = Position.of("b2");
        Position target2 = Position.of("a2");

        boolean isTrue = source.isDiagonal(target1);
        boolean isFalse = source.isDiagonal(target2);

        assertThat(isTrue).isTrue();
        assertThat(isFalse).isFalse();
    }

    @ParameterizedTest(name = "대상 위치가 나이트가 갈 수 있는 위치인지 테스트한다.")
    @CsvSource({"c3, true", "c7, true", "b4, true", "b6, true", "e3, true", "e7, true", "f4, true", "f6, true", "a1, false"})
    void isKnightDirectionTest(String target, boolean isMovable) {
        //given
        Position source = Position.of("d5");

        //then
        assertThat(source.isKnightDirection(Position.of(target))).isEqualTo(isMovable);
    }

    @ParameterizedTest(name = "getDistance 메서드를 사용해서 Position 사이의 거리 구할 수 있다.")
    @ValueSource(strings = {"d4", "e6", "c3"})
    void getDistanceTest(String target) {
        //given
        Position source = Position.of("d5");
        int distance = source.getDistance(Position.of(target));

        //then
        assertThat(distance).isEqualTo(1);
    }

    @ParameterizedTest(name = "next 메서드의 인자 방향에 맞는 다음 위치를 반환한다.")
    @CsvSource({"UP, d5", "DOWN_LEFT, c3", "KNIGHT_UP_RIGHT, e6"})
    void nextTest(String direction, String target) {
        //given
        Position source = Position.of("d4");

        //when
        Position nextPosition1 = source.next(Direction.valueOf(direction));

        //then
        assertThat(nextPosition1).isEqualTo(Position.of(target));
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
    @DisplayName("위치의 가로(x좌표) 값(int 타입) 가져온다")
    void getHorizontalIndex() {
        //given
        Position position = Position.of("a2");

        //when
        int horizontalIndex = position.getHorizontalIndex();

        //then
        assertThat(horizontalIndex).isEqualTo(1);
    }

    @Test
    @DisplayName("위치의 세로(y좌표) 값(int 타입) 가져온다.")
    void getVerticalIndex() {
        //given
        Position position = Position.of("a2");

        //when
        int verticalIndex = position.getVerticalIndex();

        //then
        assertThat(verticalIndex).isEqualTo(2);
    }
}