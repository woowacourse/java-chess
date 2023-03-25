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
        chessGame.move(moveHistory.getSourcePosition(), moveHistory.getTargetPosition());
        chessGameJdbcDao.save(moveHistory);
    }

    public GameResult loadBoard() {
        for (MoveHistory moveHistory : chessGameJdbcDao.findAll()) {
            Position sourcePosition = moveHistory.getSourcePosition();
            Position targetPosition = moveHistory.getTargetPosition();
            chessGame.move(sourcePosition, targetPosition);
        }
        return chessGame.getResult();
    }

    //왕이 죽었다면 true를 반환하고 db를 deleteAll 하는 메서드 입니다.
    // 이렇게 return과 작동 2가지 일을 하는 메서드도 괜찮을까요?
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
