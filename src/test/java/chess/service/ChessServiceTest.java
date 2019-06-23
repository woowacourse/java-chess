package chess.service;

import chess.config.DataSource;
import chess.config.DbConnector;
import chess.dao.CommandDao;
import chess.domain.Board;
import chess.domain.BoardGenerator;
import chess.domain.Game;
import chess.domain.Position;
import chess.dto.CommandDto;
import chess.exception.PositionIllegalArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChessServiceTest {
    private ChessService chessService;
    private Game game;

    @BeforeEach
    public void setUp() {
        final CommandDao commandDao = CommandDao.from(DataSource.getInstance());
        chessService = new ChessService(commandDao);
        Board board = new Board(BoardGenerator.generate());
        game = Game.from(board);
    }

    @Test
    public void action_잘못_입력시_예외처리_테스트() {
        Position origin = Position.of("2", "a");
        Position target = Position.of("5", "a");

        assertThrows(PositionIllegalArgumentException.class, () -> chessService.action(game, origin, target, 1));
    }

    @Test
    public void action_제대로_입력시_true_테스트() {
        Position origin = Position.of("2", "a");
        Position target = Position.of("4", "a");

        // DB에 초기값이 없어서 에러가 발생하는데 true만 return 해주면 맞음
        assertTrue(chessService.action(game, origin, target, 1));
    }

    @Test
    public void loadTest() {
        List<CommandDto> commandDtos = new ArrayList<>();
        CommandDto commandDto = new CommandDto();
        commandDto.setOrigin("a2");
        commandDto.setTarget("a4");
        commandDto.setRound(1);
        commandDto.setRoomId(100L);

        commandDtos.add(commandDto);

        Game expected = chessService.load(commandDtos);

        Position origin = Position.of("2", "a");
        Position target = Position.of("4", "a");
        game.action(origin, target);

        assertEquals(expected, game);
    }
}

