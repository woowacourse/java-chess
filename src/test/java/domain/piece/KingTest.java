package domain.piece;

import domain.Camp;
import domain.File;
import domain.Rank;
import domain.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    @DisplayName("킹은 상하좌우 대각선으로 한칸 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource(value = "squareArguments")
    void canMove(final Square source, final Square target, final boolean expected) {
        final King king = new King(Camp.BLACK);

        // when
        final boolean canMove = king.canMove(source, target);

        // then
        assertThat(canMove).isEqualTo(expected);
    }

    static Stream<Arguments> squareArguments() {
        return Stream.of(
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FOUR, File.C), true),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FOUR, File.E), true),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.THREE, File.D), true),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FIVE, File.D), true),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.THREE, File.C), true),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.THREE, File.E), true),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FIVE, File.C), true),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FIVE, File.E), true),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FIVE, File.B), false),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FIVE, File.G), false),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.EIGHT, File.D), false)
        );
    }
}
