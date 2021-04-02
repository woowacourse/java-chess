package database;

import chess.domain.board.Board;
import chess.domain.board.InitBoardGenerator;
import chess.domain.game.ChessGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ChessGameDaoTest {
    private ChessGameDao chessGameDao;

    @BeforeEach
    public void setup() {
        chessGameDao = new ChessGameDao();
    }

    @Test
    @DisplayName("db 연결 확인")
    public void connection() {
        Connection con = chessGameDao.getConnection();
        assertNotNull(con);
    }

    @Test
    @DisplayName("db create 확인")
    public void addChessGame() throws Exception {
        ChessGame chessGame = new ChessGame(new Board(InitBoardGenerator.initLines()));
        chessGame.start();
        chessGameDao.addChessGame(chessGame);
    }

    @Test
    @DisplayName("db 조회 확인")
    public void findByGameId() throws Exception {
        chessGameDao.findByGameId("1");
        assertNotNull(chessGameDao.findByGameId("1"));
    }

    @Test
    @DisplayName("db 삭제 확인")
    public void deleteChessGame() throws Exception {
        chessGameDao.deleteChessGame("1");
        assertNull(chessGameDao.findByGameId("1"));
    }
}
