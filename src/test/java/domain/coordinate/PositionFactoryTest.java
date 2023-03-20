package domain.coordinate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionFactoryTest {

    @Test
    @DisplayName("좌표를 입력하면 해당하는 포지션 객체를 반환한다.")
    void givenCoordinate_thenReturnPosition() {
        final String coordinate = "b5";

        Position position = PositionFactory.createPosition(coordinate);

        assertThat(position).isEqualTo(Position.of(1, 3));
    }

    @ParameterizedTest
    @ValueSource(strings = {"z1", "a9"})
    @DisplayName("범위 바깥의 입력을 하면 예외를 발생시킨다.")
    void givenOutOfRangeCoordinate_thenFail(String input) {
        assertThatThrownBy(() -> PositionFactory.createPosition(input))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("범위 내의 값만 입력해주세요.");
    }

    @Test
    @DisplayName("숫자를 입력하면 해당 행의 Position 리스트를 반환한다.")
    void findRowTest() {
        //given
        final List<Position> row = PositionFactory.findRow(1);

        //when
        final Set<Integer> collect = row.stream()
                .map(Position::getY)
                .collect(Collectors.toSet());

        //then
        assertThat(collect).hasSize(1);
    }

    @Test
    @DisplayName("숫자를 입력하면 해당 열의 Position 리스트를 반환한다.")
    void findColumnTest() {
        //given
        final List<Position> row = PositionFactory.findColumn(1);

        //when
        final Set<Integer> collect = row.stream()
                .map(Position::getX)
                .collect(Collectors.toSet());

        //then
        assertThat(collect).hasSize(1);
    }

}
