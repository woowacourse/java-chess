package domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import domain.position.Position;
import domain.position.Positions;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PositionTest {
    @DisplayName("문자열을 받아 Position을 생성할 수 있다.")
    @Test
    void createTest() {
        //given
        final String position = "A1";

        //when

        //then
        assertDoesNotThrow(() -> Positions.from(position));
    }

    @DisplayName("체스판 범위를 벗어난 문자열을 받으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"Z1", "AB5", ""})
    void createFail(String position) {
        //given

        //when

        //then
        assertThrowsExactly(IllegalArgumentException.class,
                () -> Positions.from(position));
    }

    @DisplayName("위치를 여러 개를 받아서 만들 수 있다.")
    @Test
    void createPositions() {
        //given
        final List<String> positions = List.of("A3", "H2", "G5");
        final List<Position> expected = positions.stream()
                .map(Positions::from)
                .collect(Collectors.toList());

        //when

        //then
        assertThat(Positions.of(positions.toArray(String[]::new))).isEqualTo(expected);
    }
}

