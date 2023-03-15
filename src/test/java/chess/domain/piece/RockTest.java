package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.Camp;
import chess.domain.File;
import chess.domain.Move;
import chess.domain.Rank;
import chess.domain.Square;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RockTest {
    private static Stream<Arguments> possibleRockTestProvider() {
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
    @MethodSource("possibleRockTestProvider")
    void Should_Success_When_RockMove(final Square source, final Square target, final Move move) {
        final Rock rock = new Rock(Camp.WHITE);

        assertThat(rock.isMovable(source, target, move)).isTrue();
    }
}
