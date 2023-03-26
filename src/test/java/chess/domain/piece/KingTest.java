package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.type.King;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("킹은 ")
class KingTest {
    @ParameterizedTest
    @MethodSource("isMovableTrueCase")
    @DisplayName("어느 방향으로든 한 칸씩 이동할 수 있다.")
    void isMovable_true(Position movablePosition) {
        // given
        final King king = new King(new Position(File.D, Rank.FOUR), Side.WHITE);

        // when, then
        assertThat(king.isMovable(movablePosition)).isTrue();
    }

    static Stream<Position> isMovableTrueCase() {
        return Stream.of(
                new Position(File.D, Rank.FIVE),
                new Position(File.E, Rank.FIVE),
                new Position(File.E, Rank.FOUR),
                new Position(File.E, Rank.THREE),
                new Position(File.D, Rank.THREE),
                new Position(File.C, Rank.THREE),
                new Position(File.C, Rank.FOUR),
                new Position(File.C, Rank.FIVE)
        );
    }

    @Test
    @DisplayName("2칸 이상 떨어져 있는 Position으로 이동할 수 없다.")
    void isMovable_distanceIsGreaterThanOne_false() {
        // given
        final King king = new King(new Position(File.A, Rank.THREE), Side.WHITE);
        final Position diagonalPosition = new Position(File.C, Rank.FIVE);

        // when, then
        assertThat(king.isMovable(diagonalPosition)).isFalse();
    }

    @Test
    @DisplayName("한 번에 두 칸 이상 이동할 수 없기 때문에 중간 경로가 존재하지 않는다.")
    void getPaths() {
        // given
        final King king = new King(new Position(File.A, Rank.THREE), Side.WHITE);
        final Position targetPosition = new Position(File.B, Rank.FOUR);
        List<Position> expectedPaths = Collections.emptyList();

        // when
        List<Position> paths = king.getPaths(targetPosition);

        // then
        assertThat(paths).isEqualTo(expectedPaths);
    }
}
