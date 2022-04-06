package chess.web.dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import chess.domain.game.state.Player;
import chess.web.dto.RoomDto;

class RoomDaoTest {
    @Test
    void save() {
        final RoomDao roomDao = new RoomDao();
        assertThatCode(() -> {
            int savedId = roomDao.save(new RoomDto("동호방", Player.White));
            remove(savedId);
        }).doesNotThrowAnyException();
    }

    @Test
    void saveDuplicateName() throws SQLException {
        final RoomDao roomDao = new RoomDao();
        int savedId1 = roomDao.save(new RoomDto("동호방", Player.White));
        assertThatThrownBy(() -> {
            int savedId2 = roomDao.save(new RoomDto("동호방", Player.Black));
            remove(savedId1);
            remove(savedId2);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void findById() throws SQLException {
        final RoomDao roomDao = new RoomDao();
        int savedId = roomDao.save(new RoomDto("동호방", Player.White));
        RoomDto findRoom = roomDao.findById(savedId);
        assertThat(findRoom.getNumber()).isEqualTo(savedId);
        remove(savedId);
    }

    @Test
    void findAll() throws SQLException {
        final RoomDao roomDao = new RoomDao();
        RoomDto room1 = new RoomDto("동호방", Player.Black);
        RoomDto room2 = new RoomDto("차노방", Player.Black);
        RoomDto room3 = new RoomDto("미곤방", Player.White);

        List<Integer> ids = new ArrayList<>();
        ids.add(roomDao.save(room1));
        ids.add(roomDao.save(room2));
        ids.add(roomDao.save(room3));

        List<RoomDto> rooms = roomDao.findAll();
        List<String> roomsNames = rooms.stream()
            .map(RoomDto::getName)
            .collect(Collectors.toList());

        assertThat(roomsNames).hasSize(3)
            .contains(room1.getName(), room2.getName(), room3.getName());
        remove(ids);
    }

    private void remove(int id) throws SQLException {
        final RoomDao roomDao = new RoomDao();
        roomDao.remove(id);
    }

    private void remove(List<Integer> ids) throws SQLException {
        for (Integer id : ids) {
            remove(id);
        }
    }
}