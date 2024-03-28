package service;

import domain.ChessGameResult;
import domain.WinStatus;
import domain.piece.Piece;
import domain.player.Player;
import domain.square.Square;
import dto.PlayerGameRecordDto;
import repository.ChessBoardDao;
import repository.ChessResultDao;

import java.sql.Connection;
import java.util.List;
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

    public PlayerGameRecordDto findGameRecord(final Player player) {
        final List<WinStatus> blackWinStatuses = chessResultDao.findBlackWinStatus(player);
        final List<WinStatus> whiteWinStatuses = chessResultDao.findWhiteWinStatus(player);

        final int countWin = countWinStatus(blackWinStatuses, WinStatus.BLACK_WIN)
                + countWinStatus(whiteWinStatuses, WinStatus.WHITE_WIN);
        final int countLose = countWinStatus(blackWinStatuses, WinStatus.WHITE_WIN)
                + countWinStatus(whiteWinStatuses, WinStatus.BLACK_WIN);
        final int countDraw = countWinStatus(blackWinStatuses, WinStatus.DRAW)
                + countWinStatus(blackWinStatuses, WinStatus.DRAW);

        return new PlayerGameRecordDto(countWin, countLose, countDraw);
    }

    private int countWinStatus(final List<WinStatus> winStatuses, final WinStatus winStatus) {
        return (int) winStatuses.stream()
                .filter(status -> status == winStatus)
                .count();
    }
}
