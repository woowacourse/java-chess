package domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionsTest {

    @DisplayName("직선 사이의 위치들을 구할 수 있다")
    @Test
    void between() {
        //given
        final Position source = Positions.from("D4");
        final Position destination = Positions.from("D8");
        final List<Position> expected = Positions.of("D5", "D6", "D7");

        //when

        //then
        Positions.between(source, destination).stream().map(Position::getName).forEach(System.out::println);
        assertThat(Positions.between(source, destination)).containsExactlyElementsOf(expected);
    }
}
