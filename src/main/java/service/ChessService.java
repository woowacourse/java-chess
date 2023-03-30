package service;

import controller.command.Move;
import dao.GameDao;
import dao.GameDto;
import domain.game.Game;
import domain.game.GameStatus;
import domain.game.Position;
import java.util.List;

public class ChessService {
    private final GameDao gameDao;

    public ChessService() {
        this.gameDao = new GameDao();
    }

    public void saveChessBoard(Game game) {
        gameDao.saveChessBoard(game);
    }

    public void create(Game game) {
        gameDao.create(game);
    }

    public List<GameDto> findGamesByUserName(String userName) {
        return this.gameDao.findGamesByUserName(userName);
    }

    public Game findGameById(String gameId) {
        return this.gameDao.findGameById(gameId);
    }

    public boolean isEnd(Game game) {
        return !game.checkStatus().equals(GameStatus.IN_PROGRESS);
    }

    public void moveByCommand(Game game, Move moveCommand) {
        Position sourcePosition = Position.of(moveCommand.getSourceFile(), moveCommand.getSourceRank());
        Position targetPosition = Position.of(moveCommand.getTargetFile(), moveCommand.getTargetRank());
        game.move(sourcePosition, targetPosition);
    }
}
