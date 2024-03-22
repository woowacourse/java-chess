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
                Arguments.of(new Square(File.D, Rank.FOUR), new Square(File.H, Rank.EIGHT), true),
                Arguments.of(new Square(File.D, Rank.FOUR), new Square(File.A, Rank.ONE), true),
                Arguments.of(new Square(File.D, Rank.FOUR), new Square(File.A, Rank.SEVEN), true),
                Arguments.of(new Square(File.D, Rank.FOUR), new Square(File.G, Rank.ONE), true),
                Arguments.of(new Square(File.D, Rank.FOUR), new Square(File.G, Rank.TWO), false),
                Arguments.of(new Square(File.D, Rank.FOUR), new Square(File.G, Rank.THREE), false),
                Arguments.of(new Square(File.D, Rank.FOUR), new Square(File.G, Rank.FIVE), false));
    }

}
