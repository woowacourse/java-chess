package domain.pieceType;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Color;
import domain.File;
import domain.Rank;
import domain.Square;
import domain.piece.Bishop;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BishopTest {

    @DisplayName("비숍은 대각선으로 여러칸 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource(value = "squareArguments")
    void canMove(final Square source, final Square target, final List<Square> expected) {
        final Bishop bishop = new Bishop(Color.BLACK);

        // when
        final List<Square> path = bishop.calculatePath(source, target);

        // then
        assertThat(path).isEqualTo(expected);
    }

    static Stream<Arguments> squareArguments() {
        return Stream.of(
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.EIGHT, File.H),
                        List.of(new Square(Rank.FIVE, File.E), new Square(Rank.SIX, File.F),
                                new Square(Rank.SEVEN, File.G), new Square(Rank.EIGHT, File.H))),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.ONE, File.A),
                        List.of(new Square(Rank.THREE, File.C), new Square(Rank.TWO, File.B),
                                new Square(Rank.ONE, File.A))),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.SEVEN, File.A),
                        List.of(new Square(Rank.FIVE, File.C), new Square(Rank.SIX, File.B),
                                new Square(Rank.SEVEN, File.A))),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.ONE, File.G),
                        List.of(new Square(Rank.THREE, File.E), new Square(Rank.TWO, File.F),
                                new Square(Rank.ONE, File.G))),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.TWO, File.G), List.of()),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.THREE, File.G), List.of()),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FIVE, File.G), List.of()));
    }

}
