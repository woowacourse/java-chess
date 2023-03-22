package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.List;

import chess.domain.piece.Bishop;
import chess.domain.piece.Side;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @Test
    @DisplayName("대상 Position을 받아서 이동 가능 여부를 반환한다. - 대각")
    void isMovable_true() {
        // given
        final Bishop bishop = new Bishop(new Position(File.A, Rank.THREE), Side.WHITE);
        final Position diagonalPosition = new Position(File.C, Rank.FIVE);

        // when, then
        assertThat(bishop.isMovable(diagonalPosition)).isTrue();
    }

    @Test
    @DisplayName("대상 Position을 받아서 이동 가능 여부를 반환한다. - 수평 & 수직")
    void isMovable_false() {
        // given
        final Bishop bishop = new Bishop(new Position(File.A, Rank.THREE), Side.WHITE);
        final Position verticalPosition = new Position(File.A, Rank.FIVE);
        final Position horizontalPosition = new Position(File.E, Rank.THREE);

        // when, then
        assertThat(bishop.isMovable(verticalPosition)).isFalse();
        assertThat(bishop.isMovable(horizontalPosition)).isFalse();
    }

    @Test
    @DisplayName("대상 Position까지 가는 경로들을 반환한다.")
    void getPaths() {
        // given
        final Bishop bishop = new Bishop(new Position(File.A, Rank.THREE), Side.WHITE);
        final Position targetPosition = new Position(File.E, Rank.SEVEN);
        List<Position> expectedPaths = List.of(
                new Position(File.B, Rank.FOUR),
                new Position(File.C, Rank.FIVE),
                new Position(File.D, Rank.SIX)
        );

        // when
        List<Position> paths = bishop.getPaths(targetPosition);

        // then
        assertThat(paths).isEqualTo(expectedPaths);
    }
}
