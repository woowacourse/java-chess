package chess;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class SquareTest {

    @ParameterizedTest(name = "{0}과 {1}에 해당하는 Sqaure를 생성할 수 있다.")
    @MethodSource("fileAndRankProvider")
    void createSquareSuccessTest(String f, String r, File file, Rank rank) {
        Assertions.assertDoesNotThrow(() -> new Square(file, rank));
    }

    // 코드 리팩토링 예정
    static Stream<Arguments> fileAndRankProvider() {
        return Stream.of(
                Arguments.arguments("a", "1", File.A, Rank.ONE),
                Arguments.arguments("b", "1", File.B, Rank.ONE),
                Arguments.arguments("c", "1", File.C, Rank.ONE),
                Arguments.arguments("d", "1", File.D, Rank.ONE),
                Arguments.arguments("e", "1", File.E, Rank.ONE),
                Arguments.arguments("f", "1", File.F, Rank.ONE),
                Arguments.arguments("g", "1", File.G, Rank.ONE)
        );
    }

}
