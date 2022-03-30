package chess;

import chess.model.Board;
import chess.model.Team;
import chess.model.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @Test
    @DisplayName("체스판을 생성하는 테스트")
    void createBoard() {
        Board board = Board.create(Pieces.create());
        assertThat(board).isExactlyInstanceOf(Board.class);
    }

    @Test
    @DisplayName("왕이 죽었는지를 확인하는 테스트")
    void isKingDead() {
        Board board = Board.create(Pieces.create());
        assertThat(board.isKingDead()).isFalse();
    }

    @Test
    @DisplayName("체스판 초기상태에서는 팀상태가 같다.")
    void getTeamScore() {
        Board board = Board.create(Pieces.create());
        assertThat(board.getWhiteTeamScore())
                .isEqualTo(board.getBlackTeamScore());
    }

    @Test
    @DisplayName("체스판 초기상태에서는 무승부이다.")
    void getWinningTeam() {
        Board board = Board.create(Pieces.create());
        Team winningTeam = board.getWinTeam();

        assertThat(winningTeam).isEqualTo(Team.NONE);
    }
}
