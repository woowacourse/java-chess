package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
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
        final Bishop bishop = new Bishop(new Position(File.A, Rank.ONE), Side.WHITE);
        final Position verticalPosition = new Position(File.A, Rank.THREE);
        final Position horizontalPosition = new Position(File.E, Rank.ONE);

        // when, then
        assertThat(bishop.isMovable(verticalPosition)).isFalse();
        assertThat(bishop.isMovable(horizontalPosition)).isFalse();
    }
}
