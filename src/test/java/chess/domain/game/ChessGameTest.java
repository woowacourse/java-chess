package chess.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.position.Fixture.*;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessGameTest {

    /* white turn
    RNBQKBNR  8 (rank 8)
    PPPPPPPP  7
    ........  6
    ........  5
    ........  4
    .....n..  3
    pppppppp  2
    rnbqkb.r  1 (rank 1)

    abcdefgh
     */
    @DisplayName("턴을 진행하는 팀의 기물을 움직일 수 있다")
    @Test
    void should_CanMovePiece_When_PieceBelongTurnTeam() {
        ChessGame game = ChessGame.newGame();
        assertThatCode(() -> game.playTurn(G1, F3)).doesNotThrowAnyException();
    }

    /* white turn
    R.BQKBNR  8 (rank 8)
    PPPPPPPP  7
    ..N.....  6
    ........  5
    ........  4
    ........  3
    pppppppp  2
    rnbqkbnr  1 (rank 1)

    abcdefgh
     */
    @DisplayName("턴이 아닌 팀의 기물을 움직일 수 없다")
    @Test
    void should_ThrowIllegalArgumentException_When_MovePiece_Which_NotBelongTurnTeam() {
        ChessGame game = ChessGame.newGame();
        assertThatThrownBy(() -> game.playTurn(B8, C6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 턴을 진행하는 팀의 기물이 아닙니다.");
    }
}
