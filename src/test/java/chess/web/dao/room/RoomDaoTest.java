package chess.web.dao.room;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.web.Room;
import java.sql.Connection;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RoomDaoTest {
    private static final int CURRENT_CREATE_ROOM_ID = 10;
    private RoomDao roomDao;

    @BeforeEach
    void setUp() {
        roomDao = new RoomDao();
    }

    //connection
    @DisplayName("room 테이블에 대한 connection 생성한다.")
    @Test
    void connection() {
        final Connection connection = roomDao.getConnection();
        assertThat(connection).isNotNull();
    }

    //save_by_insert_into (C) with tearDown removeById(D)
    @DisplayName("room 테이블에 데이터를 생성한다.")
    @Test
    void save() {
        assertDoesNotThrow(
            () -> roomDao.save("방이름")
        );
    }


    //findById_by_select_where (R)
    @DisplayName("room 테이블에서 id를 통해 특정 데이터를 가져와 해당 class 객체로 응답받는다.")
    @Test
    void findById() {
        roomDao.save("방이름");

        final Room room = roomDao.findById(CURRENT_CREATE_ROOM_ID);

        System.out.println(room);

        assertThat(room.getName()).isEqualTo("방이름");
    }


    //findAll_by_select_noWhere(R2)
    @DisplayName("room 테이블에서 모든 데이터를 가져와 해당 class 객체들들로 응받는다.")
    @Test
    void select_findAll() {
        roomDao.save("방이름");

        final List<Room> rooms = roomDao.findAll();

        assertThat(rooms).isNotEmpty();
    }

    //
//    @DisplayName("room 테이블에 있는 특정 데이터를 id로 찾아 name 값을 변경한다.")
//    @Test
//    void updateNameById() {
//        //바뀌기전 데이터
//        roomDao.save(new LectureMember("테스트용Id", "테스트용Name"));
//
//        //바뀐후 데이터
//        roomDao.updateNameById("테스트용Id", "바뀐이름");
//        final LectureMember lectureMember = roomDao.findById("테스트용Id");
//
//        assertThat(lectureMember.getName()).isEqualTo("바뀐이름");
//    }
//
    @AfterEach
    void tearDown() {
        roomDao.removeById(CURRENT_CREATE_ROOM_ID);
    }
}
