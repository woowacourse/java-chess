package domain.pieceType;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Color;
import domain.File;
import domain.Rank;
import domain.Square;
import domain.piece.Knight;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KnightTest {

    @DisplayName("비숍은 대각선으로 여러칸 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource(value = "squareArguments")
    void canMove(final Square source, final Square target, final List<Square> expected) {
        final Knight knight = new Knight(Color.BLACK);

        // when
        final List<Square> path = knight.calculatePath(source, target);

        // then
        assertThat(path).isEqualTo(expected);
    }

    static Stream<Arguments> squareArguments() {
        return Stream.of(
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.TWO, File.E),
                        List.of(new Square(Rank.TWO, File.E))),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.TWO, File.C),
                        List.of(new Square(Rank.TWO, File.C))),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.THREE, File.B),
                        List.of(new Square(Rank.THREE, File.B))),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FIVE, File.B),
                        List.of(new Square(Rank.FIVE, File.B))),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.SIX, File.C),
                        List.of(new Square(Rank.SIX, File.C))),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.SIX, File.E),
                        List.of(new Square(Rank.SIX, File.E))),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FIVE, File.F),
                        List.of(new Square(Rank.FIVE, File.F))),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.THREE, File.F),
                        List.of(new Square(Rank.THREE, File.F))),

                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FOUR, File.C), List.of()),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.THREE, File.G), List.of()),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FIVE, File.G), List.of()));
    }
}
