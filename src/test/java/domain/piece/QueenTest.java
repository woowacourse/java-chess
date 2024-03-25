package domain.piece;

import domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {
    @DisplayName("퀸은 상하좌우 대각선으로 여러칸 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource(value = "canMoveArguments")
    void canMove(final Square target) {
        final Queen queen = new Queen(Team.BLACK);
        final Square source = new Square(File.D, Rank.FOUR);
        // when
        final boolean canMove = queen.canMove(source, target, new ChessBoard().getPieces());

        // then
        assertThat(canMove).isTrue();
    }

    static Stream<Arguments> canMoveArguments() {
        return Stream.of(
                Arguments.of(new Square(File.A, Rank.FOUR)),
                Arguments.of(new Square(File.H, Rank.FOUR)),
                Arguments.of(new Square(File.D, Rank.ONE)),
                Arguments.of(new Square(File.D, Rank.EIGHT)),
                Arguments.of(new Square(File.H, Rank.EIGHT)),
                Arguments.of(new Square(File.A, Rank.ONE)),
                Arguments.of(new Square(File.A, Rank.SEVEN)),
                Arguments.of(new Square(File.G, Rank.ONE)));
    }

    @DisplayName("퀸은 상하좌우 대각선을 제외하고 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource(value = "canNotMoveArguments")
    void canNotMove(final Square target) {
        final Queen queen = new Queen(Team.BLACK);
        final Square source = new Square(File.D, Rank.FOUR);
        // when
        final boolean canMove = queen.canMove(source, target, new ChessBoard().getPieces());

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
