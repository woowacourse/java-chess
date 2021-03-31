package chess.domain.dao;

import static org.junit.jupiter.api.Assertions.*;

import chess.domain.dto.BoardDto;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class BoardDaoTest {
    @Test
    void save() throws SQLException {
        final BoardDao boardDao = new BoardDao();
        boardDao.save(new BoardDto("Black", false));
    }
}