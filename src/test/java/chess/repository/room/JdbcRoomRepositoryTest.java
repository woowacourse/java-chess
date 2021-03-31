package chess.repository.room;

import static org.junit.jupiter.api.Assertions.*;

import chess.domain.dto.RoomDto;
import chess.domain.game.Room;
import chess.domain.gamestate.running.Ready;
import chess.domain.team.Team;
import chess.utils.BoardUtil;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JdbcRoomRepositoryTest {

    JdbcRoomRepository repository;

    @BeforeEach
    void setUp() {
        repository = new JdbcRoomRepository();
    }

    @Test
    void insert() throws SQLException {
        repository.insert(0, "테스트123", new Room(new Ready(BoardUtil.generateInitialBoard()), Team.WHITE));
    }

    @Test
    void update() throws SQLException {
        repository.update(2, "테스트12", new Room(new Ready(BoardUtil.generateInitialBoard()), Team.BLACK));
    }

    @Test
    void findRoomById() throws SQLException {
        RoomDto roomDto = repository.findRoomById(2);
        System.out.println(roomDto.getId());
        System.out.println(roomDto.getName());
        System.out.println(roomDto.getState());
        System.out.println(roomDto.getCurrentTeam());
        System.out.println(roomDto.getCreatedAt());
    }

    @Test
    void findRoomsByUserId() throws SQLException {
        List<RoomDto> roomDtos = repository.findRoomsByUserId(0);

        roomDtos.forEach((roomDto -> {
            System.out.println(roomDto.getId());
            System.out.println(roomDto.getName());
            System.out.println(roomDto.getState());
            System.out.println(roomDto.getCurrentTeam());
            System.out.println(roomDto.getCreatedAt());
        }));
    }
}
