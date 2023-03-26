package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.type.Knight;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("나이트는 ")
class KnightTest {

    @ParameterizedTest
    @MethodSource("isMovableTrueCase")
    @DisplayName("어떤 방향으로든 두 칸 전진 후 좌우로 한 칸 이동할 수 있다.")
    void isMovable_true(Position movablePosition) {
        // given
        final Knight knight = new Knight(new Position(File.D, Rank.FOUR), Side.WHITE);

        // when, then
        assertThat(knight.isMovable(movablePosition)).isTrue();
    }

    static Stream<Position> isMovableTrueCase() {
        return Stream.of(
                new Position(File.C, Rank.SIX),
                new Position(File.E, Rank.SIX),
                new Position(File.F, Rank.THREE),
                new Position(File.F, Rank.FIVE),
                new Position(File.C, Rank.TWO),
                new Position(File.E, Rank.TWO),
                new Position(File.B, Rank.THREE),
                new Position(File.B, Rank.FIVE)
        );
    }

    @Test
    @DisplayName("두 칸 전진 후 좌우 한 칸 이동하는 것 외의 방법으로는 이동할 수 없다.")
    void isMovable_false() {
        // given
        final Knight knight = new Knight(new Position(File.E, Rank.ONE), Side.WHITE);
        final Position position = new Position(File.F, Rank.TWO);

        // when, then
        assertThat(knight.isMovable(position)).isFalse();
    }

    @Test
    @DisplayName("나이트의 이동 경로를 반환한다.")
    void getPaths_empty() {
        // given
        final Knight knight = new Knight(new Position(File.E, Rank.ONE), Side.WHITE);
        final Position targetPosition = new Position(File.F, Rank.THREE);
        List<Position> expectedPaths = List.of(
                new Position(File.E, Rank.TWO),
                new Position(File.E, Rank.THREE)
        );

        // when
        List<Position> paths = knight.getPaths(targetPosition);

        // then
        assertThat(paths).isEqualTo(expectedPaths);
    }
}
