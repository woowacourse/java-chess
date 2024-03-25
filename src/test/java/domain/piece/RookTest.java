package domain.piece;

import domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {
    @DisplayName("룩은 상하좌우로 여러칸 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource(value = "canMoveArguments")
    void canMove(final Square target) {
        final Rook rook = new Rook(Team.BLACK);
        final Square source = new Square(File.D, Rank.FOUR);
        // when
        final boolean canMove = rook.canMove(source, target, new ChessBoard().getPieces());

        // then
        assertThat(canMove).isTrue();
    }

    static Stream<Arguments> canMoveArguments() {
        return Stream.of(
                Arguments.of(new Square(File.A, Rank.FOUR)),
                Arguments.of(new Square(File.H, Rank.FOUR)),
                Arguments.of(new Square(File.D, Rank.ONE)),
                Arguments.of(new Square(File.D, Rank.EIGHT)));
    }

    @DisplayName("룩은 대각선 뱡향으로 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource(value = "canNotMoveArguments")
    void canNotMove(final Square target) {
        final Rook rook = new Rook(Team.BLACK);
        final Square source = new Square(File.D, Rank.FOUR);
        // when
        final boolean canMove = rook.canMove(source, target, new ChessBoard().getPieces());

        // then
        assertThat(canMove).isFalse();
    }

    static Stream<Arguments> canNotMoveArguments() {
        return Stream.of(
                Arguments.of(new Square(File.G, Rank.TWO)),
                Arguments.of(new Square(File.G, Rank.THREE)),
                Arguments.of(new Square(File.G, Rank.FIVE)));
    }
}
