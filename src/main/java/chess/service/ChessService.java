package chess.service;

import chess.dao.CommandDao;
import chess.domain.*;
import chess.dto.CommandDto;
import chess.exception.PositionIllegalArgumentException;
import chess.utils.PositionConverter;

import java.util.List;

public class ChessService {

    private final CommandDao commandDao;

    public ChessService(final CommandDao commandDao) {
        this.commandDao = commandDao;
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
            commandDto.setTurn(commandDao.findLatestRoundByRoomId(roomId));
            commandDao.add(commandDto);
            return true;
        }
        throw new PositionIllegalArgumentException("잘못 입력했습니다. 다시 입력해주세요.");
    }

    public List<Piece> getPieces(final Game game) {
        return game.getPieces();
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

    public ScoreCalculator createScoreCalculator(Game game) {
        return new ScoreCalculator(game.getPiecesExceptEmpty());
    }
}
