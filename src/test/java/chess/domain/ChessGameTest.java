package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ChessGameTest {

    @Test
    @DisplayName("우승팀을 반환한다")
    void judgeWinnerTest() {
        ChessGame chessGame = ChessGame.create(1111);
        chessGame.initialize(Team.WHITE, ChessBoardInitLogic.initialize());
        assertThat(chessGame.getWinner()).isEqualTo(Team.BLACK);
    }
}
