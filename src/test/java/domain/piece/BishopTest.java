package domain.piece;

import domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {
    @DisplayName("비숍은 대각선으로 여러칸 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource(value = "canMoveArguments")
    void canMove(final Square target) {
        // given
        final Bishop bishop = new Bishop(Team.BLACK);
        final Square source = new Square(File.D, Rank.FOUR);

        // when
        final boolean canMove = bishop.canMove(source, target, new ChessBoard().getPieces());

        // then
        assertThat(canMove).isTrue();
    }

    static Stream<Arguments> canMoveArguments() {
        return Stream.of(
                Arguments.of(new Square(File.H, Rank.EIGHT)),
                Arguments.of(new Square(File.A, Rank.ONE)),
                Arguments.of(new Square(File.A, Rank.SEVEN)),
                Arguments.of(new Square(File.G, Rank.ONE)));
    }

    @DisplayName("비숍은 상하좌우로 이동할 수 없다.")
    @ParameterizedTest
    @MethodSource("canNotMoveArguments")
    void canNotMove(final Square target) {
        // given
        final Bishop bishop = new Bishop(Team.BLACK);
        final Square source = new Square(File.D, Rank.FOUR);

        // when
        final boolean canMove = bishop.canMove(source, target, new ChessBoard().getPieces());

        // then
        assertThat(canMove).isFalse();
    }

    static Stream<Arguments> canNotMoveArguments() {
        return Stream.of(
                Arguments.of(new Square(File.D, Rank.EIGHT)),
                Arguments.of(new Square(File.D, Rank.ONE)),
                Arguments.of(new Square(File.G, Rank.FOUR)),
                Arguments.of(new Square(File.A, Rank.FOUR)));
    }
}
