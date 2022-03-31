package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.position.Position;
import chess.domain.strategy.MoveStrategy;

public abstract class Piece {
    private final Team team;
    private final MoveStrategy moveStrategy;

    public Piece(MoveStrategy moveStrategy, Team team) {
        this.moveStrategy = moveStrategy;
        this.team = team;
    }

    public void move(Position source, Position target, ChessBoard chessBoard) {
        moveStrategy.isMovable(source, target, chessBoard);
    }

    public abstract String getSymbol();

    public Team getTeam() {
        return team;
    }

    public abstract double getScore();

    public abstract boolean isPawn();

    public abstract boolean isKing();
}
