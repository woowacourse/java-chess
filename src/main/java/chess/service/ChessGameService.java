package chess.service;

import chess.dao.ChessGameJdbcDao;
import chess.domain.game.ChessGame;
import chess.domain.game.GameResult;
import java.util.List;

public class ChessGameService {
    private final ChessGame chessGame;
    private final ChessGameJdbcDao chessGameJdbcDao;

    public ChessGameService(ChessGame chessGame, ChessGameJdbcDao chessGameJdbcDao) {
        this.chessGame = chessGame;
        this.chessGameJdbcDao = chessGameJdbcDao;
    }

    public void move(Move move) {
        int gameId = chessGameJdbcDao.findGameIdByNotFinished();
        chessGame.move(move.getSource(), move.getTarget());
        chessGameJdbcDao.saveMove(move, gameId);
    }

    public GameResult loadBoard() {
        int gameId = chessGameJdbcDao.findGameIdByNotFinished();
        List<Move> moves = chessGameJdbcDao.findByGameId(gameId);

        MoveHistory moveHistory = new MoveHistory(moves);
        for (Move move : moveHistory.getSortHistory()) {
            chessGame.move(move.getSource(), move.getTarget());
        }
        return chessGame.getResult();
    }

    public boolean isGameEnd() {
        if (chessGame.isEnd()) {
            finishedGame();
            return true;
        }
        return false;
    }

    public void start() {
        if (!chessGameJdbcDao.isExistNotFinishedGame()) {
            chessGameJdbcDao.saveGame();
        }
        chessGame.start();
    }

    public boolean isNotStart() {
        return chessGame.isNotInitialize();
    }

    public GameResult getGameResult() {
        return chessGame.getResult();
    }

    public void finishedGame() {
        chessGameJdbcDao.finishedGame();
    }
}
