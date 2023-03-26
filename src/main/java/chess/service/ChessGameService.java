package chess.service;

import chess.dao.ChessGameJdbcDao;
import chess.domain.game.ChessGame;
import chess.domain.game.GameResult;
import chess.domain.position.Position;
import chess.dto.MoveHistory;

public class ChessGameService {
    private final ChessGame chessGame;
    private final ChessGameJdbcDao chessGameJdbcDao;

    public ChessGameService(ChessGame chessGame, ChessGameJdbcDao chessGameJdbcDao) {
        this.chessGame = chessGame;
        this.chessGameJdbcDao = chessGameJdbcDao;
    }

    public void move(MoveHistory moveHistory) {
        Position sourcePosition = Position.of(moveHistory.getSource());
        Position targetPosition = Position.of(moveHistory.getTarget());
        chessGame.move(sourcePosition, targetPosition);
        chessGameJdbcDao.save(moveHistory);
    }

    public GameResult loadBoard() {
        for (MoveHistory moveHistory : chessGameJdbcDao.findAll()) {
            Position sourcePosition = Position.of(moveHistory.getSource());
            Position targetPosition = Position.of(moveHistory.getTarget());
            chessGame.move(sourcePosition, targetPosition);
        }
        return chessGame.getResult();
    }

    public boolean isGameEnd() {
        if (chessGame.isEnd()) {
            clear();
            return true;
        }
        return false;
    }

    public void start() {
        chessGame.start();
    }

    public boolean isNotStart() {
        return chessGame.isNotInitialize();
    }

    public GameResult getGameResult() {
        return chessGame.getResult();
    }

    public void clear() {
        chessGameJdbcDao.deleteAll();
    }
}
