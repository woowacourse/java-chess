package chess.repository;

import chess.domain.coordinate.Coordinate;
import chess.dto.MoveDto;
import chess.dto.RoomDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class MoveRepositoryImplTest {
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
    void add() throws SQLException {
        RoomDto roomDto = new RoomDto();
        int roomId = (int) roomRepository.create(roomDto).getObject();
        MoveDto moveDto = new MoveDto(roomId, Coordinate.of("a1"), Coordinate.of("b2"));
        assertThat(moveRepository.add(moveDto).isSuccess()).isTrue();
    }

    @Test
    void findById() throws SQLException {
        RoomDto roomDto = new RoomDto();
        int roomId = (int) roomRepository.create(roomDto).getObject();
        MoveDto moveDto = new MoveDto(roomId, Coordinate.of("a1"), Coordinate.of("b2"));
        int moveId = (int) moveRepository.add(moveDto).getObject();
        moveDto.setMoveId(moveId);
        MoveDto moveDto1 = (MoveDto) (moveRepository.findById(moveId).getObject());
        assertThat(moveDto1).isEqualTo(moveDto);
    }
}