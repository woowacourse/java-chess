package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.move.BlackPawnMoveStrategy;
import chess.domain.move.WhitePawnMoveStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    @DisplayName("Blank 가 아닌지 확인")
    void isBlank() {
        Pawn pawn = new Pawn(Team.BLACK);
        assertThat(pawn.isBlank()).isFalse();
    }

    @Test
    @DisplayName("킹이 아니다.")
    void isNotKing() {
        assertThat(new Pawn(Team.WHITE).isKing()).isFalse();
    }

    @Test
    @DisplayName("검정 폰 이동 전략을 반환한다.")
    void getBlackPawnMoveStrategy() {
        Pawn pawn = new Pawn(Team.BLACK);
        assertThat(pawn.getMoveStrategy()).isInstanceOf(BlackPawnMoveStrategy.class);
    }

    @Test
    @DisplayName("백 폰 이동 전략을 반환한다.")
    void getWhitePawnMoveStrategy() {
        Pawn pawn = new Pawn(Team.WHITE);
        assertThat(pawn.getMoveStrategy()).isInstanceOf(WhitePawnMoveStrategy.class);
    }
}
