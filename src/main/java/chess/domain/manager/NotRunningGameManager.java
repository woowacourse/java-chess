package chess.domain.manager;

import chess.domain.board.Board;
import chess.domain.order.MoveResult;
import chess.domain.piece.attribute.Color;
import chess.domain.position.Position;
import chess.domain.statistics.ChessGameStatistics;

public abstract class NotRunningGameManager implements ChessGameManager {
    private final long id;

    public NotRunningGameManager(long id) {
        this.id = id;
    }

    @Override
    public ChessGameManager start() {
        return ChessGameManagerFactory.createRunningGame(id);
    }

    @Override
    public ChessGameManager end() {
        return ChessGameManagerFactory.createEndGame(id, ChessGameStatistics.createNotStartGameResult());
    }

    @Override
    public MoveResult move(Position from, Position to) {
        throw new UnsupportedOperationException("게임이 진행중이지 않아 실행할 수 없습니다.");
    }

    @Override
    public Color nextColor() {
        throw new UnsupportedOperationException("게임이 진행중이지 않아 실행할 수 없습니다.");
    }

    @Override
    public boolean isKingDead() {
        return false;
    }

    @Override
    public Board getBoard() {
        throw new UnsupportedOperationException("게임이 진행중이지 않아 실행할 수 없습니다.");
    }

    @Override
    public long getId() {
        return id;
    }
}
