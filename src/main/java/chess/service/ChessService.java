package chess.service;

import chess.controller.dto.BoardDto;
import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.domain.ChessGame;
import chess.domain.Color;
import java.util.List;
import java.util.Map;

public class ChessService {
    private ChessGame chessGame;
    private final GameDao gameDao;
    private final PieceDao pieceDao;

    public ChessService(GameDao gameDao, PieceDao pieceDao) {
        this.gameDao = gameDao;
        this.pieceDao = pieceDao;
    }

    public List<Integer> findPossibleGameIds() {
        return gameDao.findAllPossibleId();
    }

    public List<Integer> findImpossibleGameIds() {
        return gameDao.findAllImpossibleId();
    }

    public void loadChessGame(int gameId) {
        ChessGame existedChessGame = gameDao.findById(gameId);

        if (existedChessGame == null) {
            this.chessGame = new ChessGame();
            gameDao.save(gameId, chessGame);
            return;
        }

        this.chessGame = existedChessGame;
    }

    public void start(int gameId) {
        chessGame.start();
        gameDao.updateById(gameId, chessGame); // Board 초기화 때문에 ..
    }

    public void move(int gameId, List<String> arguments) {
        chessGame.move(arguments);
        gameDao.updateById(gameId, chessGame);
    }

    public Map<Color, Double> status() {
        return chessGame.status();
    }

    public void end(int gameId) {
        chessGame.end();
        gameDao.updateById(gameId, chessGame);
    }

    public boolean canPlay() {
        return !chessGame.isEnd() && !chessGame.isCatch();
    }

    public boolean isCatch() {
        return chessGame.isCatch();
    }

    public Color getWinner() {
        return chessGame.getTurn().reverse();
    }

    public BoardDto getBoard() {
        return BoardDto.create(chessGame.getBoard());
    }
}
