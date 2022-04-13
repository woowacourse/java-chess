package chess.db;

import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.ChessGame;
import chess.domain.GameTurn;
import chess.domain.board.InitialBoardGenerator;

public class PieceDaoTest {

    @BeforeEach
    void initTable() {
        ChessGameDao chessGameDao = new ChessGameDao();
        chessGameDao.save("test", new ChessGame(new InitialBoardGenerator(), GameTurn.WHITE));
    }

    @AfterEach
    void deleteTable() {
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
    }

    @DisplayName("존재하는 게임에 대한 검색은 예외를 반환하지 않는다")
    @Test
    void findByGameID() {
        PieceDao pieceDao = new PieceDao();
        pieceDao.save("test");

        assertThatNoException().isThrownBy(() -> pieceDao.findByGameID("test"));
    }

    @DisplayName("존재하지 않는 게임에 대한 검색은 예외를 반환한다")
    @Test
    void findByGameID_NoSuchGame() {
        PieceDao pieceDao = new PieceDao();
        pieceDao.save("test");

        assertThatThrownBy(() -> pieceDao.findByGameID("test1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("헉.. 저장 안한거 아냐? 그런 게임은 없어!");
    }
}
