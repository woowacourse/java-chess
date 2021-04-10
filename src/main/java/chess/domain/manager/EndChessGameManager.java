package chess.domain.manager;

import chess.domain.board.Board;
import chess.domain.statistics.ChessGameStatistics;

public class EndChessGameManager extends NotRunningGameManager {
    private final ChessGameStatistics chessGameStatistics;
    private final Board board;

    public EndChessGameManager(long id, ChessGameStatistics chessGameStatistics, Board board) {
        super(id);
        this.chessGameStatistics = chessGameStatistics;
        this.board = board;
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
    public Board getBoard() {
        return board;
    }

    @Override
    public ChessGameStatistics getStatistics() {
        return this.chessGameStatistics;
    }
}
