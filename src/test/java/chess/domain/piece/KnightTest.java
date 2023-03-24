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

class KnightTest {
    private static Stream<Arguments> possibleKnightTestProvider() {
        return Stream.of(
                Arguments.of(
                        new Square(File.C, Rank.THREE),
                        new Square(File.D, Rank.FIVE),
                        Direction.KNIGHT_UP_RIGHT
                ),
                Arguments.of(
                        new Square(File.C, Rank.THREE),
                        new Square(File.E, Rank.FOUR),
                        Direction.KNIGHT_RIGHT_UP
                ),
                Arguments.of(
                        new Square(File.C, Rank.THREE),
                        new Square(File.E, Rank.TWO),
                        Direction.KNIGHT_RIGHT_DOWN
                ),
                Arguments.of(
                        new Square(File.C, Rank.THREE),
                        new Square(File.D, Rank.ONE),
                        Direction.KNIGHT_DOWN_RIGHT
                ),
                Arguments.of(
                        new Square(File.C, Rank.THREE),
                        new Square(File.B, Rank.ONE),
                        Direction.KNIGHT_DOWN_LEFT
                ),
                Arguments.of(
                        new Square(File.C, Rank.THREE),
                        new Square(File.A, Rank.TWO),
                        Direction.KNIGHT_LEFT_DOWN
                ),
                Arguments.of(
                        new Square(File.C, Rank.THREE),
                        new Square(File.A, Rank.FOUR),
                        Direction.KNIGHT_LEFT_UP
                ),
                Arguments.of(
                        new Square(File.C, Rank.THREE),
                        new Square(File.B, Rank.FIVE),
                        Direction.KNIGHT_UP_LEFT
                )
        );
    }

    @DisplayName("사방 중 한 방향으로 한 칸 그리고 그 방향의 양 대각선 방향 중 한 방향으로 움직일 수 있다.")
    @ParameterizedTest(name = "{displayName} [{index}]")
    @MethodSource("possibleKnightTestProvider")
    void Should_Success_When_KnightMove(final Square source, final Square target, final Direction direction) {
        final Knight knight = new Knight(Team.WHITE);

        assertThat(knight.isMovable(source, target, direction)).isTrue();
    }

}
