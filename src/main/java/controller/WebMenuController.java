package controller;

import dao.GameDao;
import domain.ChessGame;
import dto.PiecesDto;
import dto.ResultDto;
import dto.StatusDto;

import java.util.List;

public class WebMenuController {
    private GameDao gameDao = new GameDao();

    public void startNewGame() {
        gameDao.start();
    }

    public void startGame(int gameID) {
        gameDao.start(gameID);
    }

    public ResultDto move(String source, String target) {
        ResultDto resultDto;
        try {
            ChessGame game = gameDao.move(source, target);
            resultDto = toResultDto(game);
        } catch (RuntimeException e) {
            resultDto = new ResultDto(null, false, e.getMessage());
        }

        return resultDto;
    }

    public ResultDto status() {
        return toResultDto(gameDao.status());
    }

    public int newGameId() {
        return gameDao.lastGameID();
    }

    private ResultDto toResultDto(ChessGame game) {
        return new ResultDto(new PiecesDto(game.getBoard().getPieceMap(),
                new StatusDto(game.blackScore(), game.whiteScore()),
                game.isEnd(), game.getTurn()), true, "");
    }

    public List<String> findGames() {
        return gameDao.findGames();
    }
}
