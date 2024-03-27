package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.point.File;
import chess.domain.point.Point;
import chess.domain.point.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    @DisplayName("폰은 팀의 진행 방향으로 한 칸 이동할 수 있다.")
    void isMovable1() {
        Pawn pawn = Pawn.from(Team.WHITE);

        boolean result = pawn.isMovable(
                Point.of(File.A, Rank.THIRD),
                Point.of(File.A, Rank.FOURTH),
                Piece.empty());

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("처음 폰이 움직일 땐 위로 두 칸 이동할 수 있다.")
    void isMovable2() {
        Pawn pawn = Pawn.from(Team.WHITE);

        boolean result = pawn.isMovable(
                Point.of(File.A, Rank.SECOND),
                Point.of(File.A, Rank.FOURTH),
                Piece.empty());

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("폰은 팀 진행 방향 대각선에 상대 기물이 있으면 한 칸 이동할 수 있다.")
    void isMovable3() {
        Pawn pawn = Pawn.from(Team.BLACK);

        boolean result = pawn.isMovable(
                Point.of(File.A, Rank.THIRD),
                Point.of(File.B, Rank.SECOND),
                Piece.bishopFrom(Team.WHITE));

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("하얀색 폰은 아래로 갈 수 없다.")
    void invalidIsMovable1() {
        Pawn pawn = Pawn.from(Team.WHITE);

        boolean result = pawn.isMovable(
                Point.of(File.A, Rank.SECOND),
                Point.of(File.A, Rank.FIRST),
                Piece.empty());

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("초기 위치가 아닌 폰은 두 칸 움직일 수 없다")
    void invalidIsMovable2() {
        Pawn pawn = Pawn.from(Team.WHITE);

        boolean result = pawn.isMovable(
                Point.of(File.A, Rank.THIRD),
                Point.of(File.A, Rank.FIFTH),
                Piece.empty());

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("폰은 팀 진행 방향 대각선이 비어있으면 이동할 수 없다.")
    void invalidIsMovable3() {
        Pawn pawn = Pawn.from(Team.BLACK);

        boolean result = pawn.isMovable(
                Point.of(File.A, Rank.THIRD),
                Point.of(File.B, Rank.SECOND),
                Piece.empty());

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("폰은 팀 진행 방향 대각선에 우리팀 기물이 있으면 이동할 수 없다.")
    void invalidIsMovable4() {
        Pawn pawn = Pawn.from(Team.BLACK);

        boolean result = pawn.isMovable(
                Point.of(File.A, Rank.THIRD),
                Point.of(File.B, Rank.SECOND),
                Piece.pawnFrom(Team.BLACK));

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("폰은 팀 진행 방향에 기물이 있으면 이동할 수 없다.")
    void invalidIsMovable5() {
        Pawn pawn = Pawn.from(Team.BLACK);

        boolean result = pawn.isMovable(
                Point.of(File.A, Rank.THIRD),
                Point.of(File.A, Rank.SECOND),
                Piece.bishopFrom(Team.WHITE));

        assertThat(result).isFalse();
    }
}
