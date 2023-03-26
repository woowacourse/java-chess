package chess.domain.winningstatus;

import chess.domain.piece.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class WinningStatusByKingTest {
    private static WinningStatusByKing winningStatusByKing;

    @BeforeEach
    void setUp() {
        winningStatusByKing = new WinningStatusByKing(Team.BLACK);
    }

    @Test
    void 승자가_결정되었는지_물어보면_true를_반환한다() {
        assertThat(winningStatusByKing.isWinnerDetermined()).isTrue();
    }

    @Test
    void 점수를_요청하면_예외가_발생한다() {
        assertThatThrownBy(() -> winningStatusByKing.getScores()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 승자를_반환한다() {
        assertThat(winningStatusByKing.getWinner()).isEqualTo(Team.BLACK);
    }
}