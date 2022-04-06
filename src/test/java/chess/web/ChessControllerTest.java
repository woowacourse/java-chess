package chess.web;

import chess.dao.BoardDao;
import chess.dao.RoomDao;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChessControllerTest {
    @Test
    void test() {
        RoomDao roomDao = new RoomDao();
        roomDao.delete(1);
    }

    @Test
    void findById() {
        RoomDao roomDao = new RoomDao();
        Map<String, String> room = roomDao.findById(1);
        assertThat(room == null).isTrue();
    }

    @Test
    void delete() {
        BoardDao boardDao = new BoardDao();
        boardDao.delete();
    }
}