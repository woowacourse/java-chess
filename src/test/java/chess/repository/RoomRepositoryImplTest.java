package chess.repository;

import chess.dto.RoomDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class RoomRepositoryImplTest {
    private MoveRepository moveRepository;
    private RoomRepository roomRepository;

    @BeforeEach
    void setup() {
        moveRepository = new MoveRepositoryImpl();
        roomRepository = new RoomRepositoryImpl();
    }

    @AfterEach
    void tearDown() throws SQLException {
        moveRepository.deleteAll();
        roomRepository.deleteAll();
    }

    @Test
    void create() throws SQLException {
        RoomDto roomDto = new RoomDto();
        assertThat(roomRepository.create(roomDto).isSuccess()).isTrue();
    }

    @Test
    void findById() throws SQLException {
        RoomDto roomDto = new RoomDto();
        int roomId = (int) roomRepository.create(roomDto).getObject();
        roomDto.setRoomId(roomId);
        RoomDto roomDto1 = (RoomDto) (roomRepository.findById(roomId).getObject());
        assertThat(roomDto1).isEqualTo(roomDto);
    }

    @Test
    void findByName() throws SQLException {
        RoomDto roomDto = new RoomDto();
        roomDto.setName("test");
        int roomId = (int) roomRepository.create(roomDto).getObject();
        roomDto.setRoomId(roomId);
        RoomDto roomDto1 = (RoomDto) (roomRepository.findByName("test").getObject());
        assertThat(roomDto1).isEqualTo(roomDto);
    }
}