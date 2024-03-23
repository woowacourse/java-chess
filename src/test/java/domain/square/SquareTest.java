package domain.square;

import domain.ChessVector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SquareTest {

    @DisplayName("source에서 target으로 이동하는 방향과 크기를 의미하는 벡터를 구한다.")
    @ParameterizedTest
    @MethodSource(value = "squareVectorArguments")
    void calculateVector(final Square target, final ChessVector expected) {
        // given
        final Square source = new Square(File.D, Rank.FOUR);

        // when
        final ChessVector chessVector = target.calculateVector(source);

        // then
        assertThat(chessVector).isEqualTo(expected);
    }

    static Stream<Arguments> squareVectorArguments() {
        return Stream.of(
                Arguments.of(new Square(File.D, Rank.EIGHT), new ChessVector(0, 4)),
                Arguments.of(new Square(File.A, Rank.FOUR), new ChessVector(-3, 0)),
                Arguments.of(new Square(File.G, Rank.SEVEN), new ChessVector(3, 3)),
                Arguments.of(new Square(File.G, Rank.ONE), new ChessVector(3, -3)),
                Arguments.of(new Square(File.E, Rank.SIX), new ChessVector(1, 2)));
    }

    @DisplayName("ChessVector만큼 움직인 Square를 구한다.")
    @ParameterizedTest
    @MethodSource(value = "nextSquareArguments")
    void next(final ChessVector unitVector, final Square expected) {
        final Square source = new Square(File.D, Rank.FOUR);

        final Square nextSquare = source.next(unitVector);

        assertThat(nextSquare).isEqualTo(expected);
    }

    static Stream<Arguments> nextSquareArguments() {
        return Stream.of(
                Arguments.of(new ChessVector(1, 0), new Square(File.E, Rank.FOUR)),
                Arguments.of(new ChessVector(0, -1), new Square(File.D, Rank.THREE)),
                Arguments.of(new ChessVector(1, -1), new Square(File.E, Rank.THREE)),
                Arguments.of(new ChessVector(1, 2), new Square(File.E, Rank.SIX)),
                Arguments.of(new ChessVector(-2, 1), new Square(File.B, Rank.FIVE)));
    }

}
