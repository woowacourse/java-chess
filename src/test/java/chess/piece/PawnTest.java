package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Collections;
import java.util.List;

import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Side;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @Test
    @DisplayName("대상 Position을 받아서 이동 가능 여부를 반환한다. - White : U, UR, UL")
    void isMovable_white_true() {
        // given
        final Pawn pawn = new Pawn(new Position(File.B, Rank.TWO), Side.WHITE);
        final Position upPosition = new Position(File.B, Rank.THREE);
        final Position upRightPosition = new Position(File.C, Rank.THREE);
        final Position upLeftPosition = new Position(File.A, Rank.THREE);

        // when, then
        assertThat(pawn.isMovable(upPosition)).isTrue();
        assertThat(pawn.isMovable(upRightPosition)).isTrue();
        assertThat(pawn.isMovable(upLeftPosition)).isTrue();
    }

    @Test
    @DisplayName("흰색 진영은 시작 위치인 경우 두 칸 위로 이동 가능하다.")
    void isMovable_whiteStartPosition_twoUp() {
        // given
        final Pawn pawn = new Pawn(new Position(File.B, Rank.TWO), Side.WHITE);
        final Position upPosition = new Position(File.B, Rank.FOUR);

        // when, then
        assertThat(pawn.isMovable(upPosition)).isTrue();
    }

    @Test
    @DisplayName("대상 Position을 받아서 이동 가능 여부를 반환한다. - white : false")
    void isMovable_white_false() {
        // given
        final Pawn pawn = new Pawn(new Position(File.B, Rank.TWO), Side.WHITE);
        final Position falsePosition = new Position(File.D, Rank.FIVE);

        // when, then
        assertThat(pawn.isMovable(falsePosition)).isFalse();
    }

    @Test
    @DisplayName("대상 Position을 받아서 이동 가능 여부를 반환한다. - black : D, DR, DL")
    void isMovable_black_true() {
        // given
        final Pawn pawn = new Pawn(new Position(File.B, Rank.SEVEN), Side.BLACK);
        final Position downPosition = new Position(File.B, Rank.SIX);
        final Position downRightPosition = new Position(File.C, Rank.SIX);
        final Position downLeftPosition = new Position(File.A, Rank.SIX);

        // when, then
        assertThat(pawn.isMovable(downPosition)).isTrue();
        assertThat(pawn.isMovable(downRightPosition)).isTrue();
        assertThat(pawn.isMovable(downLeftPosition)).isTrue();
    }

    @Test
    @DisplayName("검은색 진영은 시작 위치인 경우 두 칸 아래로 이동 가능하다.")
    void isMovable_blackStartPosition_twoDown() {
        // given
        final Pawn pawn = new Pawn(new Position(File.B, Rank.SEVEN), Side.BLACK);
        final Position downPosition = new Position(File.B, Rank.FIVE);

        // when, then
        assertThat(pawn.isMovable(downPosition)).isTrue();
    }

    @Test
    @DisplayName("대상 Position을 받아서 이동 가능 여부를 반환한다. - black : false")
    void isMovable_black_false() {
        // given
        final Pawn pawn = new Pawn(new Position(File.B, Rank.SEVEN), Side.BLACK);
        final Position falsePosition = new Position(File.D, Rank.FIVE);

        // when, then
        assertThat(pawn.isMovable(falsePosition)).isFalse();
    }

    @Test
    @DisplayName("시작 위치에서 두 칸 이동했을 경우 이동 경로를 반환한다.")
    void getPaths() {
        // given
        final King king = new King(new Position(File.A, Rank.TWO), Side.WHITE);
        final Position targetPosition = new Position(File.A, Rank.FOUR);
        List<Position> expectedPaths = List.of(new Position(File.A, Rank.THREE));

        // when
        List<Position> paths = king.getPaths(targetPosition);

        // then
        assertThat(paths).isEqualTo(expectedPaths);
    }

    @Test
    @DisplayName("한 칸만 이동했을 경우 빈 경로를 반환한다.")
    void getPaths_empty() {
        // given
        final King king = new King(new Position(File.A, Rank.TWO), Side.WHITE);
        final Position targetPosition = new Position(File.A, Rank.THREE);
        List<Position> expectedPaths = Collections.emptyList();

        // when
        List<Position> paths = king.getPaths(targetPosition);

        // then
        assertThat(paths).isEqualTo(expectedPaths);
    }
}
