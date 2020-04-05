package chess.dao.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.util.Objects;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ChessGameDAOTest {

    private static ChessGameDAO CHESS_GAME_DAO;

    @BeforeAll
    public static void setup() {
        CHESS_GAME_DAO = new ChessGameDAO();
    }

    @Test
    public void connection() {
        Connection con = CHESS_GAME_DAO.getConnection();
        assertThat(Objects.nonNull(con)).isTrue();
    }
}
