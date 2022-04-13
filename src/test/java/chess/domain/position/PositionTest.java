package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

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
        Position position = Position.of(aXAxis, oneYAxis);

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
        Position position = Position.of(XAxis.A, YAxis.ONE);
        Position samePosition = Position.of(XAxis.A, YAxis.ONE);

        // when & then
        assertThat(position).isSameAs(samePosition);
    }

    @DisplayName("isSameXAxis 는 현재 위치가 다른 위치와 X좌표가 같다면 true 를 반환한다.")
    @Test
    void isSameXAxis_returnsTrueIfBothXAxisSame() {
        // given
        Position position1 = Position.of(XAxis.A, YAxis.ONE);
        Position position2 = Position.of(XAxis.A, YAxis.TWO);

        // when
        boolean actual = position1.hasSameXAxisAs(position2);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("isSameYAxis 는 현재 위치가 다른 위치와 Y좌표가 같다면 true 를 반환한다.")
    @Test
    void isSameYAxis_returnsTrueIfBothYAxisSame() {
        // given
        Position position1 = Position.of(XAxis.A, YAxis.ONE);
        Position position2 = Position.of(XAxis.B, YAxis.ONE);

        // when
        boolean actual = position1.hasSameYAxisAs(position2);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("isInVerticalRange는 현재 위치와 다른 위치가 주어진 세로 거리 안에 있는지 체크한다.")
    @Test
    void isInVerticalRange_returnsTrue() {
        // given
        Position position1 = Position.of(XAxis.A, YAxis.TWO);
        Position position2 = Position.of(XAxis.C, YAxis.ONE);

        // when
        boolean actual = position1.isVerticalDistanceShorterThan(position2, 2);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("getPositionsSameDirectionDiagonalBetween 은 대각방향의 두 위치 사이의 위치 리스트를 반환한다.")
    @Test
    void getPositionsSameDirectionDiagonalBetween() {
        // given
        Position position1 = Position.of(XAxis.A, YAxis.ONE);
        Position position2 = Position.of(XAxis.D, YAxis.FOUR);

        // when
        List<Position> positions = position1.getPositionsSameDirectionDiagonalBetween(position2);

        // then
        assertThat(positions).containsAll(List.of(
                Position.of(XAxis.B, YAxis.TWO),
                Position.of(XAxis.C, YAxis.THREE)
        ));
    }

    @DisplayName("정적메서드 getDegree는 두 Position을 전달받아 각도를 반환한다.")
    @ParameterizedTest(name = "({0}, {1}), ({2}, {3}) => {4}도")
    @CsvSource(value = {
            "A,ONE,A,TWO,0.0",
            "A,ONE,B,TWO,45.0",
            "A,ONE,B,ONE,90.0",
            "A,TWO,B,ONE,135.0",
            "A,TWO,A,ONE,180.0",
            "B,TWO,A,ONE,-135.0",
            "B,ONE,A,ONE,-90",
            "B,ONE,A,TWO,-45.0"
    })
    void getDegree_returnsDegreeWithTwoPositions(String fromXAxis, String fromYAxis, String toXAxis, String toYAxis,
                                                 double expected) {
        // given
        Position position1 = Position.of(XAxis.valueOf(fromXAxis), YAxis.valueOf(fromYAxis));
        Position position2 = Position.of(XAxis.valueOf(toXAxis), YAxis.valueOf(toYAxis));

        // when
        double actual = Position.calculateDegree(position1, position2);

        // then
        assertThat(actual).isEqualTo(expected);

    }
}
