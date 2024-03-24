package chess.domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.File;
import chess.domain.piece.Position;
import chess.domain.piece.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NightTest {

    @DisplayName("나이트를 수직으로 2칸, 수평으로 1칸 이동한다.")
    @Test
    void canMoveVertical2Horizon1() {
        // given
        final Night night = new Night(Color.BLACK);

        // when
        final boolean canMoveRight = night.canMoveTo(new Position(File.D, Rank.FIVE), new Position(File.E, Rank.SEVEN));
        final boolean canMoveLeft = night.canMoveTo(new Position(File.D, Rank.FIVE), new Position(File.C, Rank.SEVEN));

        // then
        assertThat(canMoveRight).isTrue();
        assertThat(canMoveLeft).isTrue();
    }

    @DisplayName("나이트를 수직으로 1칸, 수평으로 2칸 이동한다.")
    @Test
    void canMoveVertical1Horizon2() {
        // given
        final Night night = new Night(Color.BLACK);

        // when
        final boolean canMoveRight = night.canMoveTo(new Position(File.D, Rank.FIVE), new Position(File.F, Rank.SIX));
        final boolean canMoveLeft = night.canMoveTo(new Position(File.D, Rank.FIVE), new Position(File.B, Rank.SIX));

        // then
        assertThat(canMoveRight).isTrue();
        assertThat(canMoveLeft).isTrue();
    }

    @DisplayName("나이트는 이동전략 이외로는 이동할 수 없다.")
    @Test
    void canNotMove() {
        // given
        final Night night = new Night(Color.BLACK);

        // when
        final boolean canMove = night.canMoveTo( new Position(File.D, Rank.FIVE), new Position(File.F, Rank.ONE));

        // then
        assertThat(canMove).isFalse();
    }
}
