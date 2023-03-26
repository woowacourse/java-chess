package chess;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SquareTest {

    @ParameterizedTest(name = "file과 rank에 해당하는 Sqaure를 생성할 수 있다.")
    @MethodSource("fileAndRankProvider")
    void createSquareSuccessTest(File file, Rank rank) {
        assertDoesNotThrow(() -> Square.getInstanceOf(file, rank));
    }

    static Stream<Arguments> fileAndRankProvider() {
        return Stream.of(
                Arguments.arguments(File.A, Rank.ONE),
                Arguments.arguments(File.B, Rank.ONE),
                Arguments.arguments(File.C, Rank.ONE),
                Arguments.arguments(File.D, Rank.ONE),
                Arguments.arguments(File.E, Rank.ONE),
                Arguments.arguments(File.F, Rank.ONE),
                Arguments.arguments(File.G, Rank.ONE)
        );
    }

    @DisplayName("특정 file에 대한 모든 Square를 가져올 수 있다.")
    @Test
    void canGetSquaresAtFile() {
        assertThat(Square.getSquaresAt(File.A))
                .containsExactly(
                        Square.getInstanceOf(File.A, Rank.EIGHT),
                        Square.getInstanceOf(File.A, Rank.SEVEN),
                        Square.getInstanceOf(File.A, Rank.SIX),
                        Square.getInstanceOf(File.A, Rank.FIVE),
                        Square.getInstanceOf(File.A, Rank.FOUR),
                        Square.getInstanceOf(File.A, Rank.THREE),
                        Square.getInstanceOf(File.A, Rank.TWO),
                        Square.getInstanceOf(File.A, Rank.ONE)
                );
    }
}
