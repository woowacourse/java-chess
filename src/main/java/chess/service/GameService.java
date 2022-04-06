package chess.service;

import chess.dao.GameDao;
import chess.domain.game.ChessGame;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.dto.GameDto;
import java.util.Locale;

public class GameService {

    private GameDao gameDao;

    public GameService(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public void saveGame(ChessGame chessGame) {
        State state = chessGame.getState();
        String nowState = state.toString().toLowerCase(Locale.ROOT);

        GameDto gameDto = new GameDto(nowState);

        gameDao.save(gameDto);
    }

    public int findGameId() {
        return gameDao.findGameId();
    }

    public State loadState() {
        try {
            GameDto gameDto = gameDao.findGameState();
            String value = gameDto.getState();
            return State.of(value);
        } catch (NullPointerException e) {
            return new Ready();
        }
    }

    public void update(ChessGame chessGame) {
        State state = chessGame.getState();
        String nowState = state.toString().toLowerCase(Locale.ROOT);

        GameDto gameDto = new GameDto(nowState);
        int gameId = findGameId();

        gameDao.update(gameDto, gameId);
    }

    public void delete() {
        gameDao.delete();
    }
}
