package chess.db;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.ChessGame;
import chess.domain.GameTurn;
import chess.domain.board.InitialBoardGenerator;

public class ChessGameDaoTest {

    @DisplayName("존재하는 게임에 대한 검색은 예외를 반환하지 않는다")
    @Test
    void findTurnByID() {
        ChessGameDao chessGameDao = new ChessGameDao();
        ChessGame chessGame = new ChessGame(new InitialBoardGenerator(), GameTurn.BLACK);
        chessGameDao.save("yaho", chessGame);

        assertThat(chessGameDao.findTurnByID("yaho")).isEqualTo("BLACK");
    }
}
