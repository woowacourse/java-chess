package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class PieceDaoTest {
    private final PieceDao pieceDao = new PieceDao();

    @DisplayName("DB에 연결할 수 있다.")
    @Test
    void connectionTest(){
        try(Connection connection = pieceDao.getConnection()){
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
