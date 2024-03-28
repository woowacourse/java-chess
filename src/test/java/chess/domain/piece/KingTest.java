package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.point.File;
import chess.domain.point.Point;
import chess.domain.point.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @Test
    @DisplayName("위로 한칸 움직일 수 있다")
    void isMovable1() {
        King king = King.from(Team.WHITE);

        boolean result = king.isMovable(
                Point.of(File.A, Rank.FIRST),
                Point.of(File.A, Rank.SECOND),
                Piece.empty());

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("아래로 한칸 움직일 수 있다")
    void isMovable2() {
        King king = King.from(Team.WHITE);

        boolean result = king.isMovable(
                Point.of(File.A, Rank.EIGHTH),
                Point.of(File.A, Rank.SEVENTH),
                Piece.empty());

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("왼쪽으로 한칸 움직일 수 있다")
    void isMovable3() {
        King king = King.from(Team.WHITE);

        boolean result = king.isMovable(
                Point.of(File.B, Rank.FIRST),
                Point.of(File.A, Rank.FIRST),
                Piece.empty());

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("오른쪽으로 한칸 움직일 수 있다")
    void isMovable4() {
        King king = King.from(Team.WHITE);

        boolean result = king.isMovable(
                Point.of(File.A, Rank.FIRST),
                Point.of(File.B, Rank.FIRST),
                Piece.empty());

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("오른쪽 대각선 위로 한칸 움직일 수 있다.")
    void isMovable5() {
        King king = King.from(Team.WHITE);
        boolean result = king.isMovable(
                Point.of(File.A, Rank.FIRST),
                Point.of(File.B, Rank.SECOND),
                Piece.empty());

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("오른쪽 대각선 아래로 한칸 움직일 수 있다.")
    void isMovable6() {
        King king = King.from(Team.WHITE);
        boolean result = king.isMovable(
                Point.of(File.C, Rank.FOURTH),
                Point.of(File.D, Rank.THIRD),
                Piece.empty());

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("왼쪽 대각선 위로 한칸 움직일 수 있다.")
    void isMovable7() {
        King king = King.from(Team.WHITE);
        boolean result = king.isMovable(
                Point.of(File.B, Rank.FIRST),
                Point.of(File.A, Rank.SECOND),
                Piece.empty());

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("왼쪽 대각선 아래로 한칸 움직일 수 있다.")
    void isMovable8() {
        King king = King.from(Team.WHITE);
        boolean result = king.isMovable(
                Point.of(File.C, Rank.THIRD),
                Point.of(File.B, Rank.SECOND),
                Piece.empty());

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("주위로만 움직일 수 있다")
    void invalidIsMovable() {
        King king = King.from(Team.WHITE);
        boolean result = king.isMovable(
                Point.of(File.C, Rank.THIRD),
                Point.of(File.C, Rank.FIFTH),
                Piece.empty());

        assertThat(result).isFalse();
    }
}
