package domain.piece;

import domain.File;
import domain.Rank;
import domain.Square;
import domain.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    @DisplayName("나이트는 2칸,1칸을 조합한 정해진 8방향으로 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource(value = "squareArguments")
    void canMove(final Square source, final Square target, final boolean expected) {
        final Knight knight = new Knight(Team.BLACK);

        // when
        final boolean canMove = knight.canMove(source, target);

        // then
        assertThat(canMove).isEqualTo(expected);
    }

    static Stream<Arguments> squareArguments() {
        return Stream.of(
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.TWO, File.E), true),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.TWO, File.C), true),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.THREE, File.B), true),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FIVE, File.B), true),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.SIX, File.C), true),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.SIX, File.E), true),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FIVE, File.F), true),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.THREE, File.F), true),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FOUR, File.C), false),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.THREE, File.G), false),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FIVE, File.G), false));
    }
}
