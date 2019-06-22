package chess.service;

import chess.dao.CommandDao;
import chess.dao.RoomDao;
import chess.domain.*;
import chess.dto.CommandDto;
import chess.utils.PositionConverter;

import java.util.List;

public class ChessService {

    private final CommandDao commandDao;
    private final RoomDao roomDao;

    public ChessService(final CommandDao commandDao, final RoomDao roomDao) {
        this.commandDao = commandDao;
        this.roomDao = roomDao;
    }

    public Game initGame() {
        Board board = new Board(BoardGenerator.generate());
        return Game.from(board);
    }

    public boolean action(final Game game, final Position origin, final Position target, final long roomId) {
        if (game.action(origin, target)) {
            CommandDto commandDto = new CommandDto();
            commandDto.setOrigin(origin.toString());
            commandDto.setTarget(target.toString());
            commandDto.setRoomId(roomId);
            commandDto.setRound(commandDao.findLatestRoundByRoomId(roomId));
            commandDao.add(commandDto);
            return true;
        }
        return false;
    }

    public List<Square> getSquares(final Game game) {
        return game.values();
    }

    public List<CommandDto> findByRoomId(final long roomId) {
        return commandDao.findByRoomId(roomId);
    }

    public Game load(final List<CommandDto> commandDtos) {
        Board board = new Board(BoardGenerator.generate());
        Game game = Game.from(board);
        for (final CommandDto commandDto : commandDtos) {
            Position origin = PositionConverter.convert(commandDto.getOrigin());
            Position target = PositionConverter.convert(commandDto.getTarget());
            game.action(origin, target);
        }
        return game;
    }

}
