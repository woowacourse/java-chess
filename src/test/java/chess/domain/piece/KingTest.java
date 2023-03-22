package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Collections;
import java.util.List;

import chess.domain.piece.King;
import chess.domain.piece.Side;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {
    @Test
    @DisplayName("대상 Position을 받아서 이동 가능 여부를 반환한다. - 대각")
    void isMovable_true() {
        // given
        final King king = new King(new Position(File.A, Rank.THREE), Side.WHITE);
        final Position diagonalPosition = new Position(File.B, Rank.FOUR);

        // when, then
        assertThat(king.isMovable(diagonalPosition)).isTrue();
    }

    @Test
    @DisplayName("대상 Position을 받아서 이동 가능 여부를 반환한다. - 수평 & 수직")
    void isMovable_false() {
        // given
        final King king = new King(new Position(File.A, Rank.THREE), Side.WHITE);
        final Position verticalPosition = new Position(File.A, Rank.FOUR);
        final Position horizontalPosition = new Position(File.B, Rank.THREE);

        // when, then
        assertThat(king.isMovable(verticalPosition)).isTrue();
        assertThat(king.isMovable(horizontalPosition)).isTrue();
    }

    @Test
    @DisplayName("킹은 2칸 이상 떨어져 있는 Position으로 이동할 수 없다.")
    void isMovable_distanceIsGreaterThanOne_false() {
        // given
        final King king = new King(new Position(File.A, Rank.THREE), Side.WHITE);
        final Position diagonalPosition = new Position(File.C, Rank.FIVE);

        // when, then
        assertThat(king.isMovable(diagonalPosition)).isFalse();
    }

    @Test
    @DisplayName("킹은 한 번에 두 칸 이상 이동할 수 없기 때문에 중간 경로가 존재하지 않는다.")
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
