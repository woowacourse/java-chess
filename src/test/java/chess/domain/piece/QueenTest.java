package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.Direction;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class QueenTest {
    private static Stream<Arguments> possibleQueenTestProvider() {
        return Stream.of(
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.B, Rank.SEVEN),
                        Direction.UP
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.H, Rank.SEVEN),
                        Direction.RIGHT_UP
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.H, Rank.TWO),
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

    @DisplayName("팔방으로 거리 제한 없이 움직일 수 있다.")
    @ParameterizedTest(name = "{displayName} [{index}]")
    @MethodSource("possibleQueenTestProvider")
    void Should_Success_When_QueenMove(final Square source, final Square target, final Direction direction) {
        final Queen queen = new Queen(Team.WHITE);

        assertThat(queen.isMovable(source, target, direction)).isTrue();
    }
}
