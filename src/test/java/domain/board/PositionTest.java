package domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {
    @DisplayName("문자열을 받아 Position을 생성할 수 있다.")
    @Test
    void createTest() {
        //given
        final String position = "A1";

        //when

        //then
        assertDoesNotThrow(() -> Position.from(position));
    }

    @DisplayName("체스판 범위를 벗어난 문자열을 받으면 예외가 발생한다.")
    @Test
    void createFail() {
        //given
        final String position = "Z1";

        //when

        //then
        assertThrowsExactly(IllegalArgumentException.class,
                () -> Position.from(position));
    }

    @DisplayName("위치를 여러 개를 받아서 만들 수 있다.")
    @Test
    void createPositions() {
        //given
        final List<String> positions = List.of("A3", "H2", "G5");
        final List<Position> expected = positions.stream()
                .map(Position::from)
                .collect(Collectors.toList());

        //when

        //then
        assertThat(Position.of(positions.toArray(String[]::new))).isEqualTo(expected);
    }
}

