package domain.piece;

import domain.Team;
import domain.square.File;
import domain.square.Rank;
import domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {

    @DisplayName("퀸은 상하좌우 대각선으로 여러칸 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource(value = "squareArguments")
    void canMove(final Square target, final boolean expected) {
        // given
        final Queen queen = new Queen(Team.BLACK);
        final Square source = new Square(File.D, Rank.FOUR);

        // when
        final boolean canMove = queen.canMove(source, target);

        // then
        assertThat(canMove).isEqualTo(expected);
    }

    static Stream<Arguments> squareArguments() {
        return Stream.of(
                Arguments.of(new Square(File.A, Rank.FOUR), true),
                Arguments.of(new Square(File.H, Rank.FOUR), true),
                Arguments.of(new Square(File.D, Rank.ONE), true),
                Arguments.of(new Square(File.D, Rank.EIGHT), true),
                Arguments.of(new Square(File.H, Rank.EIGHT), true),
                Arguments.of(new Square(File.A, Rank.ONE), true),
                Arguments.of(new Square(File.A, Rank.SEVEN), true),
                Arguments.of(new Square(File.G, Rank.ONE), true),
                Arguments.of(new Square(File.G, Rank.TWO), false),
                Arguments.of(new Square(File.G, Rank.THREE), false),
                Arguments.of(new Square(File.G, Rank.FIVE), false));
    }
}
