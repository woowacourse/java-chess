package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.point.File;
import chess.domain.point.Point;
import chess.domain.point.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @Test
    @DisplayName("오른쪽 대각선 위로 움직일 수 있다.")
    void isMovable1() {
        Bishop bishop = Bishop.from(Team.WHITE);
        boolean result = bishop.isMovable(
                Point.of(File.A, Rank.FIRST),
                Point.of(File.B, Rank.SECOND),
                Piece.empty());

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("오른쪽 대각선 아래로 움직일 수 있다.")
    void isMovable2() {
        Bishop bishop = Bishop.from(Team.WHITE);
        boolean result = bishop.isMovable(
                Point.of(File.C, Rank.FOURTH),
                Point.of(File.E, Rank.SECOND),
                Piece.empty());

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("왼쪽 대각선 위로 움직일 수 있다.")
    void isMovable3() {
        Bishop bishop = Bishop.from(Team.WHITE);
        boolean result = bishop.isMovable(
                Point.of(File.H, Rank.FIRST),
                Point.of(File.A, Rank.EIGHTH),
                Piece.empty());

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("왼쪽 대각선 아래로 움직일 수 있다.")
    void isMovable4() {
        Bishop bishop = Bishop.from(Team.WHITE);
        boolean result = bishop.isMovable(
                Point.of(File.H, Rank.EIGHTH),
                Point.of(File.B, Rank.SECOND),
                Piece.empty());

        assertThat(result).isTrue();
    }
}
