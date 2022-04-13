package chess.service;

import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.db.ChessGameDao;
import chess.db.DBConnector;
import chess.db.PieceDao;
import chess.domain.ChessGame;
import chess.domain.GameTurn;
import chess.domain.board.InitialBoardGenerator;

public class DBServiceTest {

    @BeforeEach
    void initTable() {
        DBConnector dbConnector = new DBConnector();
        String deletePieceSql = "delete from piece where gameID = 'test'";

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(deletePieceSql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String deleteChessGameSql = "delete from chessGame where gameID = 'test'";

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteChessGameSql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ChessGameDao chessGameDao = new ChessGameDao();
        chessGameDao.save("test", new ChessGame(new InitialBoardGenerator(), GameTurn.WHITE));

        PieceDao pieceDao = new PieceDao();
        pieceDao.save("test");
    }

    // @Test
    // @DisplayName("gameID를 이용해 DB로부터 불러온 chessGame이 같은 조건의 chessGame과 일치한다")
    // void loadGame() {
    //     DBService dbService = new DBService();
    //     assertThat(dbService.loadGame("test")).isEqualTo(new ChessGame(new InitialBoardGenerator(), GameTurn.WHITE));
    // }

    @Test
    @DisplayName("gameID를 이용해 DB로부터 불러온 turn이 해당 gameTurn과 일치한다")
    void getTurn() {
        DBService dbService = new DBService();
        assertThat(dbService.getTurn("test")).isEqualTo(GameTurn.WHITE);
    }
}
