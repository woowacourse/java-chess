package chess.dao.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.util.Objects;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CastlingElementsDAOTest {

    private static CastlingElementsDAO CASTLING_ELEMENTS_DAO;

    @BeforeAll
    public static void setup() {
        CASTLING_ELEMENTS_DAO = new CastlingElementsDAO();
    }

    @Test
    public void connection() {
        Connection con = CASTLING_ELEMENTS_DAO.getConnection();
        assertThat(Objects.nonNull(con)).isTrue();
    }
}
