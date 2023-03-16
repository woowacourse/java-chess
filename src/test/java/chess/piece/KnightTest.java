package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    @Test
    @DisplayName("대상 Position을 받아서 이동 가능 여부를 반환한다. - true")
    void isMovable_true() {
        // given
        final Knight knight = new Knight(new Position(File.E, Rank.ONE), Side.WHITE);
        final Position position = new Position(File.F, Rank.THREE);

        // when, then
        assertThat(knight.isMovable(position)).isTrue();
    }

    @Test
    @DisplayName("대상 Position을 받아서 이동 가능 여부를 반환한다. - false")
    void isMovable_false() {
        // given
        final Knight knight = new Knight(new Position(File.E, Rank.ONE), Side.WHITE);
        final Position position = new Position(File.F, Rank.TWO);

        // when, then
        assertThat(knight.isMovable(position)).isFalse();
    }

}
