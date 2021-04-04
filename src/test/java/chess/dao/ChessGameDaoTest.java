package chess.dao;

import chess.domain.ChessGame;
import chess.domain.command.Command;
import chess.domain.command.StartOnCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChessGameDaoTest {
    private final ChessGameDao chessGameDao = new ChessGameDao();
    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame();
        Command startOnCommand = new StartOnCommand();
        String[] temp = new String[0];
        startOnCommand.execute(chessGame, temp);
    }

    @Test
    public void connection() {
        Connection con = chessGameDao.getConnection();
        assertNotNull(con);
    }

    @DisplayName("새로운 체스 게임을 생성한다.")
    @Test
    void addChessGame() throws Exception {
        chessGameDao.insertChessGameReturnId(chessGame);
    }

    @DisplayName("체스 게임 id들을 반환받는다.")
    @Test
    void selectAllChessGameId() throws Exception {
        System.out.println(chessGameDao.selectAllChessGameId());
    }
}
