package chess.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MoveStateDAOTest {

    private MoveStateDAO moveStateDAO;

    @BeforeEach
    public void setup() {
        moveStateDAO = new MoveStateDAO();
    }

    @Test
    public void connection() {
        Connection con = moveStateDAO.getConnection();
        assertNotNull(con);
    }
}
