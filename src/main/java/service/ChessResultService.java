package service;

import domain.ChessGameResult;
import domain.piece.Piece;
import domain.player.PlayerName;
import domain.square.Square;
import dto.PlayerGameRecordDto;
import repository.ChessBoardDao;
import repository.ChessResultDao;

import java.sql.Connection;
import java.util.Map;

public class ChessResultService {

    private final ChessResultDao chessResultDao;
    private final ChessBoardDao chessBoardDao;

    public ChessResultService(final Connection connection) {
        this.chessResultDao = new ChessResultDao(connection);
        this.chessBoardDao = new ChessBoardDao(connection);
    }

    public void saveResult(final int gameId) {
        final ChessGameResult chessGameResult = calculateResult(gameId);
        chessResultDao.create(chessGameResult, gameId);
    }

    public ChessGameResult calculateResult(final int gameId) {
        final Map<Square, Piece> pieceSquares = chessBoardDao.findAll(gameId);

        return ChessGameResult.from(pieceSquares);
    }

    public PlayerGameRecordDto findGameRecord(final PlayerName name) {
        final int countWin = chessResultDao.countWin(name);
        final int countLose = chessResultDao.countLose(name);
        final int countDraw = chessResultDao.countDraw(name);

        return new PlayerGameRecordDto(countWin, countLose, countDraw);
    }
}
