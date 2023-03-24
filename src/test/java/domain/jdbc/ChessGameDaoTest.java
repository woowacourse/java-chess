package domain.jdbc;

import domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessGameDaoTest {

    private final ChessGameDao chessGameDao = new ChessGameDao();

    @Test
    @DisplayName("Connection 을 확인한다.")
    void checkConnection() {
        try (final Connection connection = chessGameDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }
    
    @Test
    @DisplayName("새로운 Chess Game 방을 만든다.")
    void saveChessBoard() {
        final String query = "INSERT INTO chess_game(turn) VALUES(?)";
        try (final Connection connection = chessGameDao.getConnection()){
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Color.BLACK.name());
            preparedStatement.executeUpdate();
            connection.rollback();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

}
