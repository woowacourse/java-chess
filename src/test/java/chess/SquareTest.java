package chess;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

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

}
