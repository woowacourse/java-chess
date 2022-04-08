package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.move.PawnBlackMoveStrategy;
import chess.domain.move.PawnWhiteMoveStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    private Pawn pawn = new Pawn(Team.WHITE);

    @Test
    @DisplayName("Blank 인지 확인")
    void isBlank() {
        assertThat(pawn.isBlank()).isFalse();
    }

    @Test
    @DisplayName("King 인지 확인")
    void isKing() {
        assertThat(pawn.isKing()).isFalse();
    }

    @Test
    @DisplayName("Pawn 인지 확인")
    void isPawn() {
        assertThat(pawn.isPawn()).isTrue();
    }

    @Test
    @DisplayName("백팀 Pawn 이동전략을 생성한다.")
    void getMoveStrategy_WhenWhite() {
        assertThat(pawn.getMoveStrategy()).isInstanceOf(PawnWhiteMoveStrategy.class);
    }

    @Test
    @DisplayName("흑팀 Pawn 이동전략을 생성한다.")
    void getMoveStrategy_WhenBlack() {
        Pawn pawn = new Pawn(Team.BLACK);

        assertThat(pawn.getMoveStrategy()).isInstanceOf(PawnBlackMoveStrategy.class);
    }
}
