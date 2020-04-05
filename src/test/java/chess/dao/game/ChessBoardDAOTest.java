package chess.dao.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.util.Objects;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ChessBoardDAOTest {

    private static ChessBoardDAO CHESS_BOARD_DAO;

    @BeforeAll
    public static void setup() {
        CHESS_BOARD_DAO = new ChessBoardDAO();
    }

    @Test
    public void connection() {
        Connection con = CHESS_BOARD_DAO.getConnection();
        assertThat(Objects.nonNull(con)).isTrue();
    }
}
