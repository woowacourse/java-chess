package chess.service;

import chess.dao.ChessGameJdbcDao;
import chess.domain.game.ChessGame;
import chess.domain.game.GameResult;
import chess.domain.position.Position;
import chess.dto.MoveDto;
import java.util.List;

public class ChessGameService {
    private static final int NOT_EXIST = -1;
    
    private final ChessGame chessGame;
    private final ChessGameJdbcDao chessGameJdbcDao;

    public ChessGameService(ChessGame chessGame, ChessGameJdbcDao chessGameJdbcDao) {
        this.chessGame = chessGame;
        this.chessGameJdbcDao = chessGameJdbcDao;
    }

    public void move(MoveDto moveDto) {
        int gameId = chessGameJdbcDao.findGameIdByNotFinished();
        Position sourcePosition = Position.of(moveDto.getSource());
        Position targetPosition = Position.of(moveDto.getTarget());
        chessGame.move(sourcePosition, targetPosition);
        chessGameJdbcDao.saveMove(moveDto, gameId);
    }

    public GameResult loadBoard() {
        int gameId = chessGameJdbcDao.findGameIdByNotFinished();
        List<MoveDto> histories = chessGameJdbcDao.findByGameId(gameId);
        MoveHistory moveHistory = new MoveHistory(histories);
        for (MoveDto moveDto : moveHistory.getSortHistory()) {
            Position sourcePosition = Position.of(moveDto.getSource());
            Position targetPosition = Position.of(moveDto.getTarget());
            chessGame.move(sourcePosition, targetPosition);
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
        if (chessGameJdbcDao.findGameIdByNotFinished() == NOT_EXIST) {
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
