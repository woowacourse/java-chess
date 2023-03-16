package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @Test
    @DisplayName("대상 Position을 받아서 이동 가능 여부를 반환한다. - 수퍙 & 수직")
    void isMovable_true() {
        // given
        final Rook rook = new Rook(new Position(File.A, Rank.ONE), Side.WHITE);
        final Position horizontalPosition = new Position(File.H, Rank.ONE);
        final Position verticalPosition = new Position(File.A, Rank.EIGHT);

        // when, then
        assertThat(rook.isMovable(horizontalPosition)).isTrue();
        assertThat(rook.isMovable(verticalPosition)).isTrue();
    }

    @Test
    @DisplayName("대상 Position을 받아서 이동 가능 여부를 반환한다. - 대각")
    void isMovable_false() {
        // given
        final Rook rook = new Rook(new Position(File.A, Rank.ONE), Side.WHITE);
        final Position diagonalPosition = new Position(File.C, Rank.THREE);

        // when, then
        assertThat(rook.isMovable(diagonalPosition)).isFalse();
    }

}
