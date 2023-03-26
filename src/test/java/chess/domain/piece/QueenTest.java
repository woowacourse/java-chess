package chess.domain.piece;

import chess.domain.piece.normal.Queen;
import chess.domain.piece.property.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.BishopTest.C5;
import static chess.domain.piece.KingTest.C6;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QueenTest {

    public static final Position A3 = new Position(File.A, Rank.THREE);
    public static final Position B2 = new Position(File.B, Rank.TWO);
    public static final Position B3 = new Position(File.B, Rank.THREE);
    public static final Position C3 = new Position(File.C, Rank.THREE);
    public static final Position C4 = new Position(File.C, Rank.FOUR);
    public static final Position D4 = new Position(File.D, Rank.FOUR);
    public static final Position E5 = new Position(File.E, Rank.FIVE);
    public static final Position F6 = new Position(File.F, Rank.SIX);
    public static final Position F3 = new Position(File.F, Rank.THREE);
    public static final Position D3 = new Position(File.D, Rank.THREE);
    public static final Position E3 = new Position(File.E, Rank.THREE);
    public static final Position H7 = new Position(File.H, Rank.SEVEN);

    @DisplayName("대각선에 있으면 이동경로에 포함된다")
    @Test
    void computePath_upRight() {
        final var queen = new Queen(Color.BLACK);
        final var source = C3;
        final var target = F6;

        assertThat(queen.computePath(source, target)).containsExactlyInAnyOrder(F6, E5, D4);
    }

    @DisplayName("대각선에 있으면 이동경로에 포함된다")
    @Test
    void computePath_downLeft() {
        final var queen = new Queen(Color.BLACK);
        final var source = E5;
        final var target = B2;

        assertThat(queen.computePath(source, target)).containsExactlyInAnyOrder(B2, C3, D4);
    }

    @DisplayName("같은 Rank에 있으면 이동경로에 포함된다")
    @Test
    void computePath_left() {
        final var queen = new Queen(Color.BLACK);
        final var source = C3;
        final var target = A3;

        assertThat(queen.computePath(source, target)).containsExactlyInAnyOrder(A3, B3);
    }

    @DisplayName("같은 File에 있으면 이동경로에 포함된다")
    @Test
    void computePath_right() {
        final var queen = new Queen(Color.BLACK);
        final var source = C3;
        final var target = F3;

        assertThat(queen.computePath(source, target)).containsExactlyInAnyOrder(F3, E3, D3);
    }

    @DisplayName("같은 File에 있으면 이동경로에 포함된다")
    @Test
    void computePath_up() {
        final var queen = new Queen(Color.BLACK);
        final var source = C3;
        final var target = C6;

        assertThat(queen.computePath(source, target)).containsExactlyInAnyOrder(C6, C5, C4);
    }

    @DisplayName("잘못된 target이면 예외가 발생한다")
    @Test
    void computePath_illegalTarget_exception() {
        final var queen = new Queen(Color.BLACK);
        final var source = C3;
        final var target = H7;

        assertThatThrownBy(() -> queen.computePath(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
