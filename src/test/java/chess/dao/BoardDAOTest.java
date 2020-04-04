package chess.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BoardDAOTest {
    private BoardDAO boardDAO;

    @BeforeEach
    public void setup() {
        boardDAO = new BoardDAO();
    }

    @Test
    public void connection() {
        Connection con = boardDAO.getConnection();
        assertNotNull(con);
    }
}
