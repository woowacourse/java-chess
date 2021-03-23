package chess.domain.manager;

import chess.domain.statistics.ChessGameStatistics;

public class NotStartedChessGameManager extends NotRunningGameManager{
    @Override
    public boolean isNotEnd() {
        return true;
    }

    @Override
    public boolean isStart() {
        return false;
    }

    @Override
    public ChessGameStatistics getStatistics() {
        throw new IllegalArgumentException("게임이 진행중이지 않아 실행할 수 없습니다.");
    }
}
