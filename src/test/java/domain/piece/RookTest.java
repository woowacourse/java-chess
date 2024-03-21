package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Camp;
import domain.File;
import domain.Rank;
import domain.Square;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RookTest {

    @DisplayName("룩은 상하좌우로 여러칸 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource(value = "squareArguments")
    void canMove(final Square source, final Square target, final boolean expected) {
        final Rook rook = new Rook(Camp.BLACK);

        // when
        final boolean canMove = rook.canMove(source, target);

        // then
        assertThat(canMove).isEqualTo(expected);
    }

    static Stream<Arguments> squareArguments() {
        return Stream.of(Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FOUR, File.A), true),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FOUR, File.H), true),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.ONE, File.D), true),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.EIGHT, File.D), true),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.TWO, File.G), false),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.THREE, File.G), false),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FIVE, File.G), false));
    }

//    static Stream<Arguments> squareArguments() {
//        return Stream.of(
//                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FOUR, File.A),
//                        List.of(new Square(Rank.FOUR, File.C), new Square(Rank.FOUR, File.B),
//                                new Square(Rank.FOUR, File.A))),
//                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FOUR, File.H),
//                        List.of(new Square(Rank.FOUR, File.E), new Square(Rank.FOUR, File.F),
//                                new Square(Rank.FOUR, File.G), new Square(Rank.FOUR, File.H))),
//                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.ONE, File.D),
//                        List.of(new Square(Rank.THREE, File.D), new Square(Rank.TWO, File.D),
//                                new Square(Rank.ONE, File.D))),
//                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.EIGHT, File.D),
//                        List.of(new Square(Rank.FIVE, File.D), new Square(Rank.SIX, File.D),
//                                new Square(Rank.SEVEN, File.D), new Square(Rank.EIGHT, File.D))),
//                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.TWO, File.G), List.of()),
//                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.THREE, File.G), List.of()),
//                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FIVE, File.G), List.of()));
//    }
}
