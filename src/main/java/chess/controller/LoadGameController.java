package chess.controller;

import java.util.List;
import java.util.stream.Collectors;

import chess.domain.database.Database;
import chess.domain.database.LoadGameDao;
import chess.domain.dto.ChessGameDto;
import chess.domain.dto.GameDto;
import chess.domain.game.ChessGame;
import chess.domain.game.User;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class LoadGameController {

    private final LoadGameDao loadGameDao = new LoadGameDao(Database.PRODUCT);

    public ChessGameDto getChessGameDtoByCommand(User user, Command newGameCommand) {
        String userId = user.getId();
        if (newGameCommand == Command.NEW) {
            return createNewGame(userId);
        }
        return getExistGame(userId);
    }

    private ChessGameDto createNewGame(String userId) {
        ChessGame chessGame = new ChessGame();
        loadGameDao.createGame(userId);
        String gameId = loadGameDao.getLastGameId(userId);
        return new ChessGameDto(gameId, chessGame);
    }

    private ChessGameDto getExistGame(String userId) {
        List<GameDto> gamesDto = loadGameDao.getGamesById(userId);
        if (gamesDto.isEmpty()) {
            OutputView.printNoGameExistMessage();
            return createNewGame(userId);
        }
        String gameId = selectGame(gamesDto);
        return new ChessGameDto(gameId, getGameById(gameId));
    }

    private String selectGame(List<GameDto> gamesDto) {
        OutputView.printGames(gamesDto);
        List<String> gameIds = getGameIds(gamesDto);
        return readGameIdUntilIdIsValid(gameIds);
    }

    private List<String> getGameIds(List<GameDto> gamesDto) {
        return gamesDto.stream()
                .map(GameDto::getGameId)
                .collect(Collectors.toList());
    }

    private String readGameIdUntilIdIsValid(List<String> gameIds) {
        String gameId;
        do {
            OutputView.printSelectGameMessage();
            gameId = InputView.readNext();
        } while (!gameIds.contains(gameId));
        return gameId;
    }

    private ChessGame getGameById(String id) {
        int lastTurn = loadGameDao.getLastTurnById(id);
        return loadGameDao.getGameById(id, lastTurn);
    }


}
