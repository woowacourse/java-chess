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
    void canMove(final Square target) {
        // given
        final King king = new King(Team.BLACK);
        final Square source = new Square(File.D, Rank.FOUR);

        // when
        final boolean canMove = king.canMove(source, target, new ChessBoard().getPieces());

        // then
        assertThat(canMove).isTrue();
    }

    static Stream<Arguments> canMoveArguments() {
        return Stream.of(
                Arguments.of(new Square(File.C, Rank.FOUR)),
                Arguments.of(new Square(File.E, Rank.FOUR)),
                Arguments.of(new Square(File.D, Rank.THREE)),
                Arguments.of(new Square(File.D, Rank.FIVE)),
                Arguments.of(new Square(File.C, Rank.THREE)),
                Arguments.of(new Square(File.E, Rank.THREE)),
                Arguments.of(new Square(File.C, Rank.FIVE)),
                Arguments.of(new Square(File.E, Rank.FIVE))
        );
    }


    @DisplayName("킹은 두칸 이상은 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource(value = "canNotArguments")
    void canNotMove(final Square target) {
        // given
        final King king = new King(Team.BLACK);
        final Square source = new Square(File.D, Rank.FOUR);

        // when
        final boolean canMove = king.canMove(source, target, new ChessBoard().getPieces());

        // then
        assertThat(canMove).isFalse();
    }

    static Stream<Arguments> canNotArguments() {
        return Stream.of(
                Arguments.of(new Square(File.B, Rank.FIVE)), // -2, 1
                Arguments.of(new Square(File.G, Rank.FIVE)), // 2, 1
                Arguments.of(new Square(File.D, Rank.SIX)) // 0, 2
        );
    }
}
