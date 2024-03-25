package domain.piece;

import domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    @DisplayName("나이트는 2칸,1칸을 조합한 정해진 8방향으로 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource(value = "canMoveArguments")
    void canMove(final Square target) {
        // given
        final Knight knight = new Knight(Team.BLACK);
        final Square source = new Square(File.D, Rank.FOUR);

        // when
        final boolean canMove = knight.canMove(source, target, new ChessBoard().getPieces());

        // then
        assertThat(canMove).isTrue();
    }

    static Stream<Arguments> canMoveArguments() {
        return Stream.of(
                Arguments.of(new Square(File.E, Rank.TWO)),
                Arguments.of(new Square(File.C, Rank.TWO)),
                Arguments.of(new Square(File.B, Rank.THREE)),
                Arguments.of(new Square(File.B, Rank.FIVE)),
                Arguments.of(new Square(File.C, Rank.SIX)),
                Arguments.of(new Square(File.E, Rank.SIX)),
                Arguments.of(new Square(File.F, Rank.FIVE)),
                Arguments.of(new Square(File.F, Rank.THREE)));
    }

    @DisplayName("나이트는 2칸,1칸을 조합한 정해진 8방향으로 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource(value = "canNotMoveArguments")
    void canNotMove(final Square target) {
        // given
        final Knight knight = new Knight(Team.BLACK);
        final Square source = new Square(File.D, Rank.FOUR);

        // when
        final boolean canMove = knight.canMove(source, target, new ChessBoard().getPieces());

        // then
        assertThat(canMove).isFalse();
    }

    static Stream<Arguments> canNotMoveArguments() {
        return Stream.of(
                Arguments.of(new Square(File.C, Rank.FOUR)),
                Arguments.of(new Square(File.G, Rank.THREE)),
                Arguments.of(new Square(File.G, Rank.FIVE)));
    }
}
