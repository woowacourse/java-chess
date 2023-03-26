package chess.domain.piece;

import chess.domain.piece.normal.Rook;
import chess.domain.piece.property.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static chess.domain.piece.PawnTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RookTest {

    private static final Position B1 = new Position(File.B, Rank.ONE);
    private static final Position C4 = new Position(File.C, Rank.FOUR);
    private static final Position D4 = new Position(File.D, Rank.FOUR);
    private static final Position B8 = new Position(File.B, Rank.EIGHT);
    private static final Position C7 = new Position(File.C, Rank.SEVEN);

    @DisplayName("같은 File일 때 이동 가능한 경로를 계산한다")
    @Test
    void computePath_sameFile() {
        Rook rook = new Rook(Color.BLACK);
        Set<Position> positions = rook.computePath(B5, B1);

        assertThat(positions).containsExactlyInAnyOrder(B4, B3, B2, B1);
    }

    @DisplayName("같은 Rank일 때 이동 가능한 경로를 계산한다")
    @Test
    void computePath_sameRank() {
        Rook rook = new Rook(Color.BLACK);
        Set<Position> positions = rook.computePath(A4, E4);

        assertThat(positions).containsExactlyInAnyOrder(E4, D4, C4, B4);
    }

    @DisplayName("source와 target의 File이 같지않고 Rank가 같지 않으면 예외를 발생한다")
    @Test
    void computPath_throw() {
        var rook = new Rook(Color.BLACK);

        assertThatThrownBy(() -> rook.computePath(A4, B7))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("룩은 빈 경로면 움직일 수 있다.")
    @Test
    void canMove_emptyPath_true() {
        var rook = new Rook(Color.BLACK);
        final var source = C4;
        final var target = C7;
        boolean actual = rook.canMove(Map.of(C5, true, C6, true, C7, false), source, target);

        assertThat(actual).isTrue();
    }

    @DisplayName("룩은 타겟 위치에 기물이 있어도 움직일 수 있다.")
    @Test
    void canMove_emptyPathAndTargetExist_true() {
        var rook = new Rook(Color.BLACK);
        final var source = C4;
        final var target = C7;
        boolean actual = rook.canMove(Map.of(C5, true, C6, true, C7, false), source, target);

        assertThat(actual).isTrue();
    }

    @DisplayName("룩은 경로에 기물이 있으면 움직일 수 없다.")
    @Test
    void canMove_notEmptyPath_false() {
        var rook = new Rook(Color.BLACK);
        final var source = C4;
        final var target = C7;
        boolean actual = rook.canMove(Map.of(C5, true, C6, false, C7, true), source, target);

        assertThat(actual).isFalse();
    }
}
