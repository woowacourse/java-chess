package chess.domain.manager;

import chess.domain.statistics.ChessGameStatistics;

public class EndChessGameManager extends NotRunningGameManager {
    private final ChessGameStatistics chessGameStatistics;

    public EndChessGameManager(ChessGameStatistics chessGameStatistics) {
        this.chessGameStatistics = chessGameStatistics;
    }

    @Override
    public boolean isNotEnd() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean isStart() {
        return true;
    }

    @Override
    public ChessGameStatistics getStatistics() {
        return this.chessGameStatistics;
    }
}
