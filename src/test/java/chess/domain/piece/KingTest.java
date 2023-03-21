package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.File;
import chess.domain.board.Move;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KingTest {
    private static Stream<Arguments> successKingTestProvider() {
        return Stream.of(
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.B, Rank.THREE),
                        Move.UP
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.C, Rank.THREE),
                        Move.UP_RIGHT
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.C, Rank.TWO),
                        Move.RIGHT
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.C, Rank.ONE),
                        Move.DOWN_RIGHT
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.B, Rank.ONE),
                        Move.DOWN
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.A, Rank.ONE),
                        Move.DOWN_LEFT
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.A, Rank.TWO),
                        Move.LEFT
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.A, Rank.THREE),
                        Move.UP_LEFT
                )
        );
    }

    @DisplayName("팔방으로 한 칸씩 움직일 수 있다.")
    @ParameterizedTest(name = "{displayName} [{index}]")
    @MethodSource("successKingTestProvider")
    void Should_Success_When_KingMove(final Square source, final Square target, final Move move) {
        final King king = new King(Camp.WHITE);

        assertThat(king.isMovable(source, target, move, false)).isTrue();
    }

    private static Stream<Arguments> failKingTestProvider() {
        return Stream.of(
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.B, Rank.FOUR),
                        Move.UP
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.D, Rank.FOUR),
                        Move.UP_RIGHT
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.D, Rank.TWO),
                        Move.RIGHT
                ),
                Arguments.of(
                        new Square(File.C, Rank.THREE),
                        new Square(File.E, Rank.ONE),
                        Move.DOWN_RIGHT
                ),
                Arguments.of(
                        new Square(File.C, Rank.THREE),
                        new Square(File.C, Rank.ONE),
                        Move.DOWN
                ),
                Arguments.of(
                        new Square(File.C, Rank.THREE),
                        new Square(File.A, Rank.ONE),
                        Move.DOWN_LEFT
                ),
                Arguments.of(
                        new Square(File.C, Rank.THREE),
                        new Square(File.A, Rank.THREE),
                        Move.LEFT
                ),
                Arguments.of(
                        new Square(File.C, Rank.THREE),
                        new Square(File.A, Rank.FIVE),
                        Move.UP_LEFT
                )
        );
    }

    @DisplayName("사방과 대각선으로 한 칸 초과해서 움직일 수 없다.")
    @ParameterizedTest(name = "{displayName} [{index}]")
    @MethodSource("failKingTestProvider")
    void Should_Fail_When_KingMove(final Square source, final Square target, final Move move) {
        final King king = new King(Camp.WHITE);

        assertThat(king.isMovable(source, target, move, false)).isFalse();
    }

    @DisplayName("")
    @Test()
    void Should_Fail_When_KingMove() {
        final King king = new King(Camp.WHITE);
        final Square source = new Square(File.A, Rank.ONE);
        final Square target = new Square(File.A, Rank.THREE);
        final Move move = Move.UP_UP;

        assertThat(king.isMovable(source, target, move, false)).isFalse();
    }
}
