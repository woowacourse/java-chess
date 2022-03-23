package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        boolean actual = position1.isOnDiagonal(position2);

        // then
        assertThat(actual).isTrue();
    }
}