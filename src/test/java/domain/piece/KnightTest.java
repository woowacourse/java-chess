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

class KnightTest {

    @DisplayName("나이트는 8방으로 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource(value = "squareArguments")
    void canMove(final Square target, final boolean expected) {
        // given
        final Knight knight = new Knight(Team.BLACK);
        final Square source = new Square(File.D, Rank.FOUR);

        // when
        final boolean canMove = knight.canMove(source, target);

        // then
        assertThat(canMove).isEqualTo(expected);
    }

    static Stream<Arguments> squareArguments() {
        return Stream.of(
                Arguments.of(new Square(File.E, Rank.TWO), true),
                Arguments.of(new Square(File.C, Rank.TWO), true),
                Arguments.of(new Square(File.B, Rank.THREE), true),
                Arguments.of(new Square(File.B, Rank.FIVE), true),
                Arguments.of(new Square(File.C, Rank.SIX), true),
                Arguments.of(new Square(File.E, Rank.SIX), true),
                Arguments.of(new Square(File.F, Rank.FIVE), true),
                Arguments.of(new Square(File.F, Rank.THREE), true),
                Arguments.of(new Square(File.C, Rank.FOUR), false),
                Arguments.of(new Square(File.G, Rank.THREE), false),
                Arguments.of(new Square(File.G, Rank.FIVE), false));
    }
}
