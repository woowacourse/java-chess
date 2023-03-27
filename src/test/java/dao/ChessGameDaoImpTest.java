package dao;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ChessGameDaoImpTest {

    private final ChessGameDaoImp chessGameDaoImp = new ChessGameDaoImp();


    @Test
    void getConnection() {
        try (final var connection = chessGameDaoImp.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
