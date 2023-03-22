package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.Direction;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KingTest {
    private static Stream<Arguments> kingTestProvider() {
        return Stream.of(
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.B, Rank.THREE),
                        Direction.UP
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.C, Rank.THREE),
                        Direction.RIGHT_UP
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.C, Rank.TWO),
                        Direction.RIGHT
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.C, Rank.ONE),
                        Direction.RIGHT_DOWN
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.B, Rank.ONE),
                        Direction.DOWN
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.A, Rank.ONE),
                        Direction.LEFT_DOWN
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.A, Rank.TWO),
                        Direction.LEFT
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.A, Rank.THREE),
                        Direction.LEFT_UP
                )
        );
    }

    @DisplayName("팔방으로 한 칸씩 움직일 수 있다.")
    @ParameterizedTest(name = "{displayName} [{index}]")
    @MethodSource("kingTestProvider")
    void Should_Success_When_KingMove(final Square source, final Square target, final Direction direction) {
        final King king = new King(Team.WHITE);

        assertThat(king.isMovable(source, target, direction)).isTrue();
    }

    @DisplayName("사방과 대각선으로 한 칸 초과해서 움직일 수 없다.")
    @Test()
    void Should_Fail_When_KingMove() {
        final King king = new King(Team.WHITE);
        final Square source = new Square(File.A, Rank.ONE);
        final Square target = new Square(File.A, Rank.THREE);
        final Direction direction = Direction.UP;

        assertThat(king.isMovable(source, target, direction)).isFalse();
    }
}
