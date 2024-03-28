package chess.model.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SideTest {

    @ParameterizedTest
    @MethodSource("provideOppositeSides")
    @DisplayName("반대편 진영을 구한다.")
    void getOppositeSide(Side side, Side expectedOppositeSide) {
        // when
        Side actualOppositeSide = side.getOppositeSide();

        // then
        assertThat(actualOppositeSide).isEqualTo(expectedOppositeSide);
    }

    private static Stream<Arguments> provideOppositeSides() {
        return Stream.of(
                Arguments.of(Side.BLACK, Side.WHITE),
                Arguments.of(Side.WHITE, Side.BLACK),
                Arguments.of(Side.NONE, Side.NONE)
        );
    }
}
