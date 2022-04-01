package chess.model;

import chess.model.board.Board;
import chess.model.board.BoardFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {
    @Test
    @DisplayName("체스판 초기상태에서는 팀상태가 같다.")
    void getTeamScore() {
        Board board = BoardFactory.create();
        GameResult gameResult = GameResult.from(board);

        assertThat(gameResult.getWinningTeam())
                .isEqualTo(Team.NONE);
    }
}
