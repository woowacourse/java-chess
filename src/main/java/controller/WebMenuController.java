package controller;

import dao.GameDao;
import domain.Board;
import domain.ChessGame;
import domain.state.Running;
import dto.PiecesDto;
import dto.ResultDto;
import dto.StatusDto;

import java.util.HashMap;
import java.util.List;

public class WebMenuController {
    private GameDao gameDao = new GameDao();

    public void startNewGame() {
        ChessGame game = new ChessGame(new Running(new Board()));
        gameDao.saveNewGame(game);
    }

    public ResultDto move(int chessId, String source, String target) {
        try {
            return toResultDto(gameDao.move(chessId, source, target));
        } catch (RuntimeException e) {
            return new ResultDto(new PiecesDto(new HashMap<>(),
                    new StatusDto(0, 0), false, true), false, e.getMessage());
        }
    }

    public ResultDto status(int chessId) {
        ChessGame chessGame = gameDao.findGameById(chessId);
        if (chessGame.isEnd()) {
            gameDao.deleteGame(chessId);
        }
        return toResultDto(chessGame);
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
