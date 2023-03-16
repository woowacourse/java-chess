package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {
    @Test
    @DisplayName("대상 Position을 받아서 이동 가능 여부를 반환한다. - 대각")
    void isMovable_true() {
        // given
        final Queen queen = new Queen(new Position(File.A, Rank.THREE), Side.WHITE);
        final Position diagonalPosition = new Position(File.D, Rank.SIX);

        // when, then
        assertThat(queen.isMovable(diagonalPosition)).isTrue();
    }

    @Test
    @DisplayName("대상 Position을 받아서 이동 가능 여부를 반환한다. - 수평 & 수직")
    void isMovable_false() {
        // given
        final Queen queen = new Queen(new Position(File.A, Rank.THREE), Side.WHITE);
        final Position verticalPosition = new Position(File.A, Rank.SIX);
        final Position horizontalPosition = new Position(File.F, Rank.THREE);

        // when, then
        assertThat(queen.isMovable(verticalPosition)).isTrue();
        assertThat(queen.isMovable(horizontalPosition)).isTrue();
    }

}
