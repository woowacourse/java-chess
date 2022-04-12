package chess.web.dao.room;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.web.Room;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RoomDaoTest {
    
    private RoomDao roomDao;

    @BeforeEach
    void setUp() {
        roomDao = new FakeRoomDao();
    }

    @DisplayName("room 테이블에 데이터를 생성한다.")
    @Test
    void save() {
        assertDoesNotThrow(
            () -> roomDao.save("방이름")
        );
    }


    @DisplayName("room 테이블에서 id를 통해 특정 데이터를 가져와 해당 class 객체로 응답받는다.")
    @Test
    void findById() {
        roomDao.save("방이름");

        final Room room = roomDao.findById(1);

        assertThat(room.getName()).isEqualTo("방이름");
    }


    @DisplayName("room 테이블에서 모든 데이터를 가져와 해당 class 객체들들로 응받는다.")
    @Test
    void select_findAll() {
        roomDao.save("방이름");

        final List<Room> rooms = roomDao.findAll();

        assertThat(rooms).isNotEmpty();
    }

    @DisplayName("현재 room에 대한 id로 방이름인 name을 변경할 수 있다.")
    @Test
    void update_name_by_id() {
        roomDao.save("방이름");

        roomDao.updateNameById(1, "바뀐 방이름");

        assertThat(roomDao.findById(1).getName()).isEqualTo("바뀐 방이름");
    }

    @DisplayName("현재 room에 대한 id로 방이름을 제외한 모든 정보를 변경할 수 있다.")
    @Test
    void update_room_by_id() {
        roomDao.save("방이름");

        roomDao.updateRoom(1, 0, "BLACK");

        assertThat(roomDao.findById(1).getCurrentCamp()).isEqualTo("BLACK");
    }


    @AfterEach
    void tearDown() {
        roomDao.removeById(1);
    }
}
