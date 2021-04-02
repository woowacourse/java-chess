package chess.webdao;

import chess.domain.ChessGame;
import chess.domain.team.Team;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChessGameDAOTest {
    @Test
    public void connection() {
        final ChessGameDAO chessGameDAO = new ChessGameDAO();
        Connection con = chessGameDAO.getConnection();
        assertNotNull(con);
    }

    @Test
    public void createChessGameDB() throws SQLException {
        final ChessGameDAO chessGameDAO = new ChessGameDAO();
        final ChessGame chessGame = new ChessGame(Team.blackTeam(), Team.whiteTeam());
        chessGameDAO.createChessGame(chessGame, "white");
    }

    @Test
    public void readChessGameDB() throws SQLException {
        final ChessGameDAO chessGameDAO = new ChessGameDAO();
        final ChessGame chessGame = chessGameDAO.readChessGame();
        assertThat(chessGame.isPlaying()).isTrue();
    }

    @Test
    public void deleteChessGameDB() throws SQLException {
        final ChessGameDAO chessGameDAO = new ChessGameDAO();
        chessGameDAO.deleteChessGame();
    }
}
