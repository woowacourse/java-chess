package chess.dao;

import org.junit.jupiter.api.BeforeEach;

class CellDAOTest {

    private BoardDAO boardDao;
    private UserDAO userDAO;

    @BeforeEach
    public void setup() {
        boardDao = new BoardDAO();
        userDAO = new UserDAO();
    }
}