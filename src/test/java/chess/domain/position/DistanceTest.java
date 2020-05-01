package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DistanceTest {

    @ParameterizedTest
    @DisplayName("#calculate() : return Distance as to Position from, to")
    @MethodSource({"getCasesForCalculate"})
    void calculate(Position from, Position to, Distance expected) {
        Distance actual = Distance.calculate(from, to);
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForCalculate() {
        return Stream.of(
                Arguments.of(Position.of(1,1), Position.of(1,2), Distance.of(1)),
                Arguments.of(Position.of(1,1), Position.of(4,5), Distance.of(5)),
                Arguments.of(Position.of(4,4), Position.of(5,7), Distance.of(Math.sqrt(10)))
        );
    }
}