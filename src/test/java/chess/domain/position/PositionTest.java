package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.direction.DiagonalDirection;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionTest {

    @DisplayName("from 메소드에 X좌표와 Y좌표를 전달하여 객체를 생성할 수 있다.")
    @Test
    void from_withAxis() {
        // given
        XAxis aXAxis = XAxis.A;
        YAxis oneYAxis = YAxis.ONE;
        Position position = Position.from(aXAxis, oneYAxis);

        // when & then
        assertThat(position).isNotNull();
    }

    @DisplayName("from 메소드에 문자열 형태로 X좌표와 Y좌표를 전달하여 객체를 생성할 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"a1", "b2", "h8"})
    void from_withStringAxis(String input) {
        // given
        Position actual = Position.from(input);

        // when & then
        assertThat(actual).isNotNull();
    }

    @DisplayName("좌표 객체는 캐싱되어 한번만 생성된다.")
    @Test
    void cache() {
        // given
        Position position = Position.from(XAxis.A, YAxis.ONE);
        Position samePosition = Position.from(XAxis.A, YAxis.ONE);

        // when & then
        assertThat(position).isSameAs(samePosition);
    }

    @DisplayName("isSameXAxis 는 현재 위치가 다른 위치와 X좌표가 같다면 true 를 반환한다.")
    @Test
    void isSameXAxis_returnsTrueIfBothXAxisSame() {
        // given
        Position position1 = Position.from(XAxis.A, YAxis.ONE);
        Position position2 = Position.from(XAxis.A, YAxis.TWO);

        // when
        boolean actual = position1.isSameXAxis(position2);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("isSameYAxis 는 현재 위치가 다른 위치와 Y좌표가 같다면 true 를 반환한다.")
    @Test
    void isSameYAxis_returnsTrueIfBothYAxisSame() {
        // given
        Position position1 = Position.from(XAxis.A, YAxis.ONE);
        Position position2 = Position.from(XAxis.B, YAxis.ONE);

        // when
        boolean actual = position1.isSameYAxis(position2);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("isSameYAxis 는 현재 위치가 다른 위치와 동일 대각선상에 존재하면 true를 반환한다.")
    @Test
    void isOnDiagonal_returnsTrueIfOnDiagonal() {
        // given
        Position position1 = Position.from(XAxis.A, YAxis.ONE);
        Position position2 = Position.from(XAxis.H, YAxis.EIGHT);

        // when
        boolean actual = DiagonalDirection.isOnDiagonal(position1, position2);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("isUpperThan 은 현재 위치가 다른 위치보다 위쪽에 있다면 true 를 반환한다.")
    @Test
    void isUpperThan_returnsTrue() {
        // given
        Position position1 = Position.from(XAxis.A, YAxis.THREE);
        Position position2 = Position.from(XAxis.A, YAxis.ONE);

        // when
        boolean actual = position1.isUpperThan(position2);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("isUpperThan 은 현재 위치가 다른 위치보다 아래쪽에 있다면 false 를 반환한다.")
    @Test
    void isUpperThan_returnsFalse() {
        // given
        Position position1 = Position.from(XAxis.A, YAxis.ONE);
        Position position2 = Position.from(XAxis.A, YAxis.TWO);

        // when
        boolean actual = position1.isUpperThan(position2);

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("isLowerThan 은 현재 위치가 다른 위치보다 아래쪽에 있다면 true 를 반환한다.")
    @Test
    void isLowerThan_returnsTrue() {
        // given
        Position position1 = Position.from(XAxis.A, YAxis.ONE);
        Position position2 = Position.from(XAxis.A, YAxis.THREE);

        // when
        boolean actual = position1.isLowerThan(position2);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("isLowerThan 은 현재 위치가 다른 위치보다 위쪽에 있다면 false 를 반환한다.")
    @Test
    void isLowerThan_returnsFalse() {
        // given
        Position position1 = Position.from(XAxis.A, YAxis.TWO);
        Position position2 = Position.from(XAxis.A, YAxis.ONE);

        // when
        boolean actual = position1.isLowerThan(position2);

        // then
        assertThat(actual).isFalse();
    }



    @DisplayName("isInVerticalRangeAndSameXAxis는 현재 위치와 다른 위치가 주어진 세로 거리 안에 있고, 같은 X좌표를 갖는다면 true 를 반환한다.")
    @Test
    void isInVerticalRangeAndSameXAxis_returnsTrue() {
        // given
        Position position1 = Position.from(XAxis.A, YAxis.TWO);
        Position position2 = Position.from(XAxis.A, YAxis.ONE);

        // when
        boolean actual = position1.isInVerticalRangeAndSameXAxis(position2, 2);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("isInVerticalRangeAndSameXAxis는 현재 위치와 다른 위치가 주어진 세로 거리 안에 있지만, 다른 X좌표를 갖는다면 false 를 반환한다.")
    @Test
    void isInVerticalRangeAndSameXAxis_returnsFalse() {
        // given
        Position position1 = Position.from(XAxis.A, YAxis.TWO);
        Position position2 = Position.from(XAxis.C, YAxis.ONE);

        // when
        boolean actual = position1.isInVerticalRangeAndSameXAxis(position2, 2);

        // then
        assertThat(actual).isFalse();
    }
}
