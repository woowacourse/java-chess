package domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PositionsTest {

    @DisplayName("문자열을 받아 Position을 생성할 수 있다.")
    @Test
    void createTest() {
        //given
        final String position = "a1";

        //when

        //then
        assertDoesNotThrow(() -> Positions.from(position));
    }

    @DisplayName("체스판 범위를 벗어난 문자열을 받으면 빈 위치가 생성된다.")
    @ParameterizedTest
    @ValueSource(strings = {"Z1", "AB5", ""})
    void createFail(String position) {
        //given

        //when

        //then
        assertThat(Positions.from(position)).isEqualTo(new Position(File.NOTHING, Rank.NOTHING));
    }

    @DisplayName("위치를 여러 개를 받아서 만들 수 있다.")
    @Test
    void createPositions() {
        //given
        final List<String> positions = List.of("A3", "h2", "G5");
        final List<Position> expected = positions.stream()
                .map(Positions::from)
                .collect(Collectors.toList());

        //when

        //then
        assertThat(Positions.of(positions.toArray(String[]::new))).isEqualTo(expected);
    }

    @DisplayName("직선 사이의 위치들을 구할 수 있다")
    @Test
    void between() {
        //given
        final Position source = Positions.from("D4");
        final Position destination = Positions.from("D8");
        final List<Position> expected = Positions.of("D5", "D6", "D7");

        //when

        //then
        assertThat(Positions.between(source, destination)).containsExactlyElementsOf(expected);
    }
}
