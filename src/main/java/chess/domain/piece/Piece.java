package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.position.Position;
import chess.domain.strategy.MoveStrategy;

public abstract class Piece {
    private final Team team;
    private final Symbol symbol;
    private final MoveStrategy moveStrategy;

    public Piece(Team team, Symbol symbol, MoveStrategy moveStrategy) {
        this.team = team;
        this.symbol = symbol;
        this.moveStrategy = moveStrategy;
    }

    public void move(Position source, Position target, ChessBoard chessBoard) {
        moveStrategy.isMovable(source, target, chessBoard);
    }

    public Team getTeam() {
        return team;
    }

    public String getSymbolByTeam() {
        return symbol.getSymbol(team);
    }

    public abstract double getScore();

    public abstract boolean isPawn();

    public abstract boolean isKing();
}
