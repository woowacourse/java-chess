package domain.jdbc;

import domain.ChessGame;
import domain.chessboard.ChessBoard;
import domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessGameDaoTest {

    private static final String CHESS_GAME_ID = Integer.toString(Integer.MAX_VALUE);
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
    void saveChessGame() { // 추후 테스트를 목 ChessGameDao 로 할 수 있도록하자.
        chessGameDao.save(new ChessGame(ChessBoard.generate()));
    }

}
