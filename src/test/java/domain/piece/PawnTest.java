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

class PawnTest {

    @DisplayName("폰은 이동시 기본 앞으로 1칸, 초기 위치에서는 2칸까지 가능하다.")
    @ParameterizedTest
    @MethodSource(value = "squareArgumentsMove")
    void canMove(final Team color, final Square source, final Square target, final boolean result) {
        final Pawn pawn = new Pawn(color);

        final boolean canMove = pawn.canMove(source, target);

        assertThat(canMove).isEqualTo(result);
    }

    static Stream<Arguments> squareArgumentsMove() {
        return Stream.of(
                Arguments.of(Team.WHITE, new Square(File.D, Rank.TWO), new Square(File.D, Rank.THREE), true),
                Arguments.of(Team.WHITE, new Square(File.D, Rank.TWO), new Square(File.D, Rank.FOUR), true),
                Arguments.of(Team.WHITE, new Square(File.D, Rank.TWO), new Square(File.C, Rank.THREE), false),
                Arguments.of(Team.WHITE, new Square(File.D, Rank.TWO), new Square(File.E, Rank.THREE), false),
                Arguments.of(Team.WHITE, new Square(File.D, Rank.THREE), new Square(File.D, Rank.FIVE), false),
                Arguments.of(Team.BLACK, new Square(File.D, Rank.SEVEN), new Square(File.D, Rank.SIX), true),
                Arguments.of(Team.BLACK, new Square(File.D, Rank.SEVEN), new Square(File.D, Rank.FIVE), true),
                Arguments.of(Team.BLACK, new Square(File.D, Rank.SIX), new Square(File.D, Rank.FOUR), false),
                Arguments.of(Team.BLACK, new Square(File.D, Rank.SEVEN), new Square(File.C, Rank.SIX), false),
                Arguments.of(Team.BLACK, new Square(File.D, Rank.SEVEN), new Square(File.E, Rank.SIX), false)
        );
    }

    @DisplayName("폰은 공격시 대각선 앞으로만 공격가능하다.")
    @ParameterizedTest
    @MethodSource(value = "squareArgumentsAttack")
    void canAttack(final Team color, final Square source, final Square target, final boolean result) {
        final Pawn pawn = new Pawn(color);

        final boolean canMove = pawn.canAttack(source, target);

        assertThat(canMove).isEqualTo(result);
    }

    static Stream<Arguments> squareArgumentsAttack() {
        return Stream.of(
                Arguments.of(Team.WHITE, new Square(File.D, Rank.TWO), new Square(File.D, Rank.THREE), false),
                Arguments.of(Team.WHITE, new Square(File.D, Rank.TWO), new Square(File.D, Rank.FOUR), false),
                Arguments.of(Team.WHITE, new Square(File.D, Rank.THREE), new Square(File.E, Rank.FIVE), false),
                Arguments.of(Team.WHITE, new Square(File.D, Rank.TWO), new Square(File.C, Rank.THREE), true),
                Arguments.of(Team.WHITE, new Square(File.D, Rank.TWO), new Square(File.E, Rank.THREE), true),
                Arguments.of(Team.BLACK, new Square(File.D, Rank.SEVEN), new Square(File.D, Rank.SIX), false),
                Arguments.of(Team.BLACK, new Square(File.D, Rank.SEVEN), new Square(File.D, Rank.FIVE), false),
                Arguments.of(Team.BLACK, new Square(File.D, Rank.SIX), new Square(File.D, Rank.FOUR), false),
                Arguments.of(Team.BLACK, new Square(File.D, Rank.SEVEN), new Square(File.C, Rank.SIX), true),
                Arguments.of(Team.BLACK, new Square(File.D, Rank.SEVEN), new Square(File.E, Rank.SIX), true)
        );
    }
}
