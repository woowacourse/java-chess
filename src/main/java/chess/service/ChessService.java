package chess.service;

import chess.dao.CommandDao;
import chess.dao.RoomDao;
import chess.domain.*;
import chess.dto.CommandDto;

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
}
