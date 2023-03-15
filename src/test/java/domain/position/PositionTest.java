package domain.position;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.assertThat;

import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("Position은 ")
class PositionTest {

    @Test
    @DisplayName("Position은 캐싱된다.")
    void getCachedPositionTest() {
        // given
        Position position1 = Position.of(2, 2);
        Position position2 = Position.of(2, 2);

        // expect
        assertThat(position1).isSameAs(position2);
    }

    @Test
    @DisplayName("캐싱된 Position의 개수는 64개이다.")
    void checkNumberOfPositionTest() {
        // given
        List<Integer> rows = List.of(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> columns = List.of(1, 2, 3, 4, 5, 6, 7, 8);

        List<Position> expectedPositions = rows.stream()
                .flatMap(row -> columns.stream()
                        .map(column -> Position.of(row, column)))
                .collect(toList());

        // when
        List<Position> setPosition = Position.getAllPosition();

        // then
        assertThat(setPosition)
                .containsAll(expectedPositions)
                .hasSize(64);
    }
}
