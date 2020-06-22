package chess.domain.position;

import chess.domain.board.Board;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InitialColumnTest {

    @ParameterizedTest
    @MethodSource({"getCasesForValueOfSucceed"})
    void valueOfSucceed(int column, InitialColumn expected) {
        InitialColumn initialColumn = InitialColumn.valueOf(column);
        assertThat(initialColumn).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForValueOfSucceed() {
        return Stream.of(
                Arguments.of(1, InitialColumn.ROOK),
                Arguments.of(8, InitialColumn.ROOK),
                Arguments.of(2, InitialColumn.KNIGHT),
                Arguments.of(7, InitialColumn.KNIGHT),
                Arguments.of(3, InitialColumn.BISHOP),
                Arguments.of(6, InitialColumn.BISHOP),
                Arguments.of(4, InitialColumn.QUEEN),
                Arguments.of(5, InitialColumn.KING)
        );
    }

    @Test
    void valueOfFail() {
        int inSufficientColumn = Board.LINE_START - 1;
        assertThatThrownBy(() -> InitialColumn.valueOf(inSufficientColumn))
                .isInstanceOf(IllegalArgumentException.class);

        int exceedColumn = Board.LINE_END + 1;
        assertThatThrownBy(() -> InitialColumn.valueOf(exceedColumn))
                .isInstanceOf(IllegalArgumentException.class);
    }
}