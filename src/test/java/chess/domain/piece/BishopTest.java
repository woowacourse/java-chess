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

class BishopTest {
    private static Stream<Arguments> possibleBishopTestProvider() {
        return Stream.of(
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.H, Rank.SEVEN),
                        Direction.RIGHT_UP
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.C, Rank.ONE),
                        Direction.RIGHT_DOWN
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.A, Rank.ONE),
                        Direction.LEFT_DOWN
                ),
                Arguments.of(
                        new Square(File.B, Rank.TWO),
                        new Square(File.A, Rank.THREE),
                        Direction.LEFT_UP
                )
        );
    }

    @DisplayName("대각선으로 거리 제한 없이 움직일 수 있다.")
    @ParameterizedTest(name = "{displayName} [{index}]")
    @MethodSource("possibleBishopTestProvider")
    void Should_Success_When_BishopMove(final Square source, final Square target, final Direction direction) {
        final Bishop bishop = new Bishop(Team.WHITE);

        assertThat(bishop.isMovable(source, target, direction)).isTrue();
    }
}
