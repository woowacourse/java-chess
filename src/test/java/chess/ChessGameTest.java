package chess;

import chess.model.board.Board;
import chess.model.board.BoardFactory;
import chess.model.ChessGame;
import chess.model.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChessGameTest {

    @Test
    @DisplayName("체스판을 생성하는 테스트")
    void createBoard() {
        Board board = BoardFactory.create();
        ChessGame chessGame = new ChessGame(board);

        assertThat(chessGame).isExactlyInstanceOf(ChessGame.class);
    }

    @Test
    @DisplayName("왕이 죽었는지를 확인하는 테스트")
    void isKingDead() {
        Board board = BoardFactory.create();
        ChessGame chessGame = new ChessGame(board);
        assertThat(chessGame.isKingDead()).isFalse();
    }

    @Test
    @DisplayName("체스판 초기상태에서는 팀상태가 같다.")
    void getTeamScore() {
        Board board = BoardFactory.create();
        ChessGame chessGame = new ChessGame(board);

        assertThat(chessGame.getWhiteTeamScore())
                .isEqualTo(chessGame.getBlackTeamScore());
    }

    @Test
    @DisplayName("체스판 초기상태에서는 무승부이다.")
    void getWinningTeam() {
        Board board = BoardFactory.create();
        ChessGame chessGame = new ChessGame(board);

        Team winningTeam = chessGame.getWinTeam();

        assertThat(winningTeam).isEqualTo(Team.NONE);
    }
}
