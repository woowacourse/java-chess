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

class BishopTest {

    @DisplayName("비숍은 대각선으로 여러칸 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource(value = "squareArguments")
    void canMove(final Square source, final Square target, final boolean expected) {
        final Bishop bishop = new Bishop(Team.BLACK);

        // when
        final boolean canMove = bishop.canMove(source, target);

        // then
        assertThat(canMove).isEqualTo(expected);
    }

    static Stream<Arguments> squareArguments() {
        return Stream.of(
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.EIGHT, File.H), true),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.ONE, File.A), true),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.SEVEN, File.A), true),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.ONE, File.G), true),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.TWO, File.G), false),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.THREE, File.G), false),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FIVE, File.G), false));
    }

}
