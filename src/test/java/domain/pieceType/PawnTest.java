package domain.pieceType;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Color;
import domain.File;
import domain.Rank;
import domain.Square;
import domain.piece.Pawn;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PawnTest {

    @DisplayName("폰")
    @ParameterizedTest
    @MethodSource(value = "squareArguments")
    void canMove(final Color color, final Square source, final Square target) {
        final Pawn pawn = new Pawn(color);

        final List<Square> path = pawn.calculatePath(source, target);

        assertThat(path).isEqualTo(List.of(target));
    }

    static Stream<Arguments> squareArguments() {
        return Stream.of(
                Arguments.of(Color.WHITE, new Square(Rank.TWO, File.D), new Square(Rank.THREE, File.D)),
                Arguments.of(Color.WHITE, new Square(Rank.TWO, File.D), new Square(Rank.FOUR, File.D)),
                Arguments.of(Color.WHITE, new Square(Rank.TWO, File.D), new Square(Rank.THREE, File.C)),
                Arguments.of(Color.WHITE, new Square(Rank.TWO, File.D), new Square(Rank.THREE, File.E)),
                Arguments.of(Color.BLACK, new Square(Rank.SEVEN, File.D), new Square(Rank.SIX, File.D)),
                Arguments.of(Color.BLACK, new Square(Rank.SEVEN, File.D), new Square(Rank.FIVE, File.D)),
                Arguments.of(Color.BLACK, new Square(Rank.SEVEN, File.D), new Square(Rank.SIX, File.C)),
                Arguments.of(Color.BLACK, new Square(Rank.SEVEN, File.D), new Square(Rank.SIX, File.E))
        );
    }
}
