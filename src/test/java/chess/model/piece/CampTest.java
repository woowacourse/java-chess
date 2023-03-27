package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CampTest {

    @ParameterizedTest(name = "isSameCamp.BLACK은 {0}이 주어지면 {1}을 반환한다")
    @DisplayName("isSameCamp() 테스트")
    @MethodSource("provideIsSameCampByBlackArguments")
    void isSameCamp_givenOtherCamp_thenReturnIsSameCamp(final Camp camp, final boolean expected) {
        // when
        final boolean actual = Camp.BLACK.isSameCamp(camp);

        // then
        assertThat(actual).isSameAs(expected);
    }

    private static Stream<Arguments> provideIsSameCampByBlackArguments() {
        return Stream.of(
                Arguments.of(Camp.BLACK, true), Arguments.of(Camp.WHITE, false), Arguments.of(Camp.EMPTY, false)
        );
    }

    @ParameterizedTest(name = "{0}이 주어지면 {1}을 반환한다")
    @DisplayName("isEmpty() 테스트")
    @MethodSource("provideIsEmptyArguments")
    void isEmpty_whenCall_thenReturnIsEmpty(final Camp camp, final boolean expected) {
        // when
        final boolean actual = camp.isEmpty();

        // then
        assertThat(actual).isSameAs(expected);
    }

    private static Stream<Arguments> provideIsEmptyArguments() {
        return Stream.of(
                Arguments.of(Camp.BLACK, false), Arguments.of(Camp.WHITE, false), Arguments.of(Camp.EMPTY, true)
        );
    }
}
