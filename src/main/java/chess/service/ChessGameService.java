package chess.service;

import chess.dao.ChessGameJdbcDao;
import chess.domain.game.ChessGame;
import chess.domain.game.GameResult;
import chess.domain.position.Position;
import chess.dto.MoveDto;
import java.util.List;

public class ChessGameService {
    private final ChessGame chessGame;
    private final ChessGameJdbcDao chessGameJdbcDao;

    public ChessGameService(ChessGame chessGame, ChessGameJdbcDao chessGameJdbcDao) {
        this.chessGame = chessGame;
        this.chessGameJdbcDao = chessGameJdbcDao;
    }

    public void move(MoveDto moveDto) {
        Position sourcePosition = Position.of(moveDto.getSource());
        Position targetPosition = Position.of(moveDto.getTarget());
        chessGame.move(sourcePosition, targetPosition);
        chessGameJdbcDao.saveMove(moveDto);
    }

    public GameResult loadBoard() {
        List<MoveDto> histories = chessGameJdbcDao.findAll();
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
