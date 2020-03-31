package chess.piece;

import chess.Board;
import chess.position.Position;
import chess.validator.MoveValidator;

public abstract class Piece {
    protected final Team team;
    protected final String symbol;
    protected boolean hasMoved;
    protected final MoveValidator moveValidator;

    public Piece(Team team, String symbol, MoveValidator moveValidator) {
        this.team = team;
        this.symbol = team.isBlack() ? symbol.toUpperCase() : symbol.toLowerCase();
        this.hasMoved = false;
        this.moveValidator = moveValidator;
    }

//    public boolean isMovable(Board board, Position source, Position target) {
//        return this.moveValidator.isMovable(board, source, target);
//    }

    public void throwExceptionIfNotMovable(Board board, Position source, Position target) {
        this.moveValidator.throwExceptionIfNotMovable(board, source, target);
    }

    public void updateHasMoved() {
        this.hasMoved = true;
    }

    public boolean isSameTeam(Piece piece) {
        return this.team == piece.team;
    }

    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    public boolean isNotSameTeam(Team turn) {
        return !isSameTeam(turn);
    }

    public boolean isWhite() {
        return this.team == Team.WHITE;
    }

    public boolean isBlack() {
        return this.team == Team.BLACK;
    }

    public Team getTeam() {
        return this.team;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public boolean getHasMoved() {
        return this.hasMoved;
    }
}
