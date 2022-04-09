package chess.dao;

import chess.dto.RoomDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoomDaoTest {
    @Test
    void findById() {
        RoomDao roomDao = new RoomDao();
        RoomDto room = roomDao.findById(1);
        assertThat(room).isNotNull();
    }
}