package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.File;
import chess.domain.board.Move;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RookTest {
    private static Stream<Arguments> possibleRookTestProvider() {
        return Stream.of(
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.B, Rank.SEVEN),
                        Move.UP
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.H, Rank.TWO),
                        Move.RIGHT
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.B, Rank.ONE),
                        Move.DOWN
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.A, Rank.TWO),
                        Move.LEFT
                )
        );
    }

    @DisplayName("사방으로 거리 제한 없이 움직일 수 있다.")
    @ParameterizedTest(name = "{displayName} [{index}]")
    @MethodSource("possibleRookTestProvider")
    void Should_Success_When_RookMove(final Square source, final Square target, final Move move) {
        final Rook rook = new Rook(Camp.WHITE, source);

        assertThat(rook.isMovable(new Pawn(Camp.BLACK, target), false)).isTrue();
    }
}
