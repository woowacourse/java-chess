package domain.pieceType;

import domain.Color;
import domain.File;
import domain.Rank;
import domain.Square;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PawnTest {

    @DisplayName("폰은 블랙일 때 아래로 한칸 ")
    @ParameterizedTest
    @MethodSource(value = "squareArguments")
    void canMove(final Square source, final Square target) {
        final Pawn pawn = new Pawn(Color.BLACK);

        // when
//        final boolean canMove = pawn.calculatePath(source, target);
//
//        // then
//        assertThat(canMove).isTrue();
    }

    static Stream<Arguments> squareArguments() {
        return Stream.of(
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FOUR, File.C)),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FOUR, File.E)),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.THREE, File.D)),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FIVE, File.D)),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.THREE, File.C)),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.THREE, File.E)),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FIVE, File.C)),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FIVE, File.E))
        );
    }
}
