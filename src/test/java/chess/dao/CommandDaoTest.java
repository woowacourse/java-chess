package chess.dao;

import chess.config.DataSource;
import chess.config.DbConnector;
import chess.config.TableCreator;
import chess.domain.Position;
import chess.dto.CommandDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CommandDaoTest {

    private CommandDao commandDao;
    private long roomId = 10L;

    @BeforeEach
    public void setUp() throws Exception {
        DbConnector dbConnector = new DbConnector(DataSource.getInstance());
        commandDao = CommandDao.from(dbConnector);
        new TableCreator(dbConnector).create();

        CommandDto commandDto = new CommandDto();
        commandDto.setTarget("a1");
        commandDto.setOrigin("a2");
        commandDto.setRoomId(roomId);
        commandDto.setRound(100);
        commandDao.add(commandDto);
    }

    @Test
    public void addTest() {
        Position origin = Position.of("1", "a");
        Position target = Position.of("2", "a");
        long round = 1;
        long room_id = 2;
        CommandDto commandDto = new CommandDto();
        commandDto.setTarget(origin.toString());
        commandDto.setOrigin(target.toString());
        commandDto.setRoomId(room_id);
        commandDto.setRound(round);

        assertDoesNotThrow(() -> commandDao.add(commandDto));
    }

    @Test
    public void findByRoomIdTest() {
        List<CommandDto> commandDtos = commandDao.findByRoomId(roomId);
        assertThat(commandDtos.size()).isEqualTo(1);
    }
}