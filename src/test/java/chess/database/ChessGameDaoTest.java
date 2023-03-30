package chess.database;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.ChessBoard;
import chess.ChessGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {

    @Test
    @DisplayName("게임의 생성 정보를 올바르게 DB에 저장한다.")
    void shouldSuccessCreateGame() {
        ChessBoard chessBoard = ChessBoard.generateChessBoard(0);
        assertDoesNotThrow(() -> new ChessGame(chessBoard, 0));
    }

    @Test
    void findLastInsertGame() {
        ChessGameDao chessGameDao = new ChessGameDao(new JdbcConnector());
        assertDoesNotThrow(chessGameDao::findLastInsertGame);
    }

    @Test
    void findRemainGames() {
        ChessGameDao chessGameDao = new ChessGameDao(new JdbcConnector());
        assertDoesNotThrow(chessGameDao::findRemainGames);
    }
}
