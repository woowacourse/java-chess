package chess.domain.manager;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.statistics.ChessGameStatistics;

public abstract class NotRunningGameManager implements ChessGameManager{
    @Override
    public ChessGameManager start() {
        return ChessGameManagerFactory.createRunningGame();
    }

    @Override
    public ChessGameManager end() {
        return ChessGameManagerFactory.createEndGame(ChessGameStatistics.createNotStartGameResult());
    }

    @Override
    public void move(Position from, Position to) {
        throw new IllegalArgumentException("게임이 진행중이지 않아 실행할 수 없습니다.");
    }

    @Override
    public boolean isKingDead(){
        return false;
    }

    @Override
    public Board getBoard() {
        throw new IllegalArgumentException("게임이 진행중이지 않아 실행할 수 없습니다.");
    }

}
