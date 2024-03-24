package domain.piece;

import domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {
    @DisplayName("킹은 상하좌우 대각선으로 한칸 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource(value = "canMoveArguments")
    void canMove(final Square target, final boolean expected) {
        // given
        final King king = new King(Team.BLACK);
        final Square source = new Square(File.D, Rank.FOUR);

        // when
        final boolean canMove = king.canMove(source, target, new ChessBoard().getPieces());

        // then
        assertThat(canMove).isEqualTo(expected);
    }

    static Stream<Arguments> canMoveArguments() {
        return Stream.of(
                Arguments.of(new Square(File.C, Rank.FOUR), true),
                Arguments.of(new Square(File.E, Rank.FOUR), true),
                Arguments.of(new Square(File.D, Rank.THREE), true),
                Arguments.of(new Square(File.D, Rank.FIVE), true),
                Arguments.of(new Square(File.C, Rank.THREE), true),
                Arguments.of(new Square(File.E, Rank.THREE), true),
                Arguments.of(new Square(File.C, Rank.FIVE), true),
                Arguments.of(new Square(File.E, Rank.FIVE), true)
        );
    }


    @DisplayName("킹은 두칸 이상은 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource(value = "canNotArguments")
    void canNotMove(final Square target, final boolean expected) {
        // given
        final King king = new King(Team.BLACK);
        final Square source = new Square(File.D, Rank.FOUR);

        // when
        final boolean canMove = king.canMove(source, target, new ChessBoard().getPieces());

        // then
        assertThat(canMove).isEqualTo(expected);
    }

    static Stream<Arguments> canNotArguments() {
        return Stream.of(
                Arguments.of(new Square(File.B, Rank.FIVE), false), // -2, 1
                Arguments.of(new Square(File.G, Rank.FIVE), false), // 2, 1
                Arguments.of(new Square(File.D, Rank.SIX), false) // 0, 2
        );
    }
}
