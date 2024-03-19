package domain.pieceType;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Color;
import domain.File;
import domain.Rank;
import domain.Square;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KingTest {

    @DisplayName("킹은 상하좌우 대각선으로 한칸 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource(value = "squareArguments")
    void canMove(final Square source, final Square target) {
        final King king = new King(Color.BLACK);

        // when
        final boolean canMove = king.canMove(source, target);

        // then
        assertThat(canMove).isTrue();
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
