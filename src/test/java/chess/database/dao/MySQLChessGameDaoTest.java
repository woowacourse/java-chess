package chess.database.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.database.DataSource;
import chess.domain.piece.info.Team;
import chess.domain.position.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MySQLChessGameDaoTest {

    private ChessGameDao chessGameDao;
    private Connection connection;

    @BeforeEach
    void initialize() {
        try {
            connection = DataSource.getConnection();
            Objects.requireNonNull(connection).setAutoCommit(false);
            chessGameDao = new MySQLChessGameDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void rollback() {
        try {
            connection.rollback();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void insertGame() {
        chessGameDao.insertGame(connection);
    }

    @Test
    void insertMoveHistory() {
        chessGameDao.insertMoveHistory(1, "a2", "a4", connection);
    }

    @Test
    void updateStateToFinished() {
        chessGameDao.updateStateToFinished(1, Team.WHITE, connection);
    }

    @Test
    void findAllHistoryById() {
        final List<Path> paths = chessGameDao.findAllHistoryById(1, connection);
        assertThat(paths).isEqualTo(List.of(
            new Path("a2", "a4"),
            new Path("c7", "c6"),
            new Path("a4", "a5"))
        );
    }
}
