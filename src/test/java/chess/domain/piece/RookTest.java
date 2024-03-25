package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.point.File;
import chess.domain.point.Point;
import chess.domain.point.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @Test
    @DisplayName("위로 움직일 수 있다")
    void isMovable1() {
        Rook rook = Rook.from(Team.WHITE);

        boolean result = rook.isMovable(
                Point.of(File.A, Rank.FIRST),
                Point.of(File.A, Rank.EIGHTH),
                Piece.empty()
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("아래로 움직일 수 있다")
    void isMovable2() {
        Rook rook = Rook.from(Team.WHITE);

        boolean result = rook.isMovable(
                Point.of(File.A, Rank.EIGHTH),
                Point.of(File.A, Rank.FIRST),
                Piece.empty()
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("왼쪽으로 움직일 수 있다")
    void isMovable3() {
        Rook rook = Rook.from(Team.WHITE);

        boolean result = rook.isMovable(
                Point.of(File.H, Rank.FIRST),
                Point.of(File.A, Rank.FIRST),
                Piece.empty()
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("오른쪽으로 움직일 수 있다")
    void isMovable4() {
        Rook rook = Rook.from(Team.WHITE);

        boolean result = rook.isMovable(
                Point.of(File.A, Rank.FIRST),
                Point.of(File.H, Rank.FIRST),
                Piece.empty()
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("대각선으로 움질수 없다")
    void invalidIsMovable() {
        Rook rook = Rook.from(Team.WHITE);

        boolean result = rook.isMovable(
                Point.of(File.A, Rank.FIRST),
                Point.of(File.B, Rank.SECOND),
                Piece.empty()
        );

        assertThat(result).isFalse();
    }
}
