package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {
    @Test
    @DisplayName("대상 Position을 받아서 이동 가능 여부를 반환한다. - 대각")
    void isMovable_true() {
        // given
        final Queen queen = new Queen(new Position(File.D, Rank.ONE), Side.WHITE);
        final Position diagonalPosition = new Position(File.D, Rank.SIX);

        // when, then
        assertThat(queen.isMovable(diagonalPosition)).isTrue();
    }

    @Test
    @DisplayName("대상 Position을 받아서 이동 가능 여부를 반환한다. - 수평 & 수직")
    void isMovable_false() {
        // given
        final Queen queen = new Queen(new Position(File.D, Rank.ONE), Side.WHITE);
        final Position verticalPosition = new Position(File.D, Rank.SIX);
        final Position horizontalPosition = new Position(File.F, Rank.ONE);

        // when, then
        assertThat(queen.isMovable(verticalPosition)).isTrue();
        assertThat(queen.isMovable(horizontalPosition)).isTrue();
    }

    @Test
    @DisplayName("대상 Position까지 가는 경로들을 반환한다.")
    void getPaths() {
        // given
        final Queen queen = new Queen(new Position(File.D, Rank.ONE), Side.WHITE);
        final Position targetPosition = new Position(File.H, Rank.FIVE);
        List<Position> expectedPaths = List.of(
                new Position(File.E, Rank.TWO),
                new Position(File.F, Rank.THREE),
                new Position(File.G, Rank.FOUR)
        );

        // when
        List<Position> paths = queen.getPaths(targetPosition);

        // then
        assertThat(paths).isEqualTo(expectedPaths);
    }
}
