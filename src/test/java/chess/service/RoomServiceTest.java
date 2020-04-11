package chess.service;

import chess.dto.RoomDto;
import chess.repository.CachedRoomRepository;
import chess.repository.MoveRepository;
import chess.repository.MoveRepositoryImpl;
import chess.repository.RoomRepository;
import chess.utils.IdGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class RoomServiceTest {
    private RoomService roomService;

    @BeforeEach
    void setup() {
        roomService = new RoomService();
    }

    @AfterEach
    void tearDown() throws SQLException {
        MoveRepository moveRepository = new MoveRepositoryImpl();
        moveRepository.deleteAll();
        RoomRepository roomRepository = new CachedRoomRepository();
        roomRepository.deleteAll();
    }

    @Test
    void create() {
        RoomDto roomDto = new RoomDto();
        assertThat((int) roomService.create(roomDto).getObject())
                .isEqualTo(IdGenerator.generateRoomId() - 1);
    }
}
