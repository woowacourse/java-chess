package domain.piece;

import static domain.PieceMoveResult.FAILURE;

import domain.PieceMoveResult;
import domain.PiecesOnChessBoard;
import domain.Position;
import domain.Team;

public abstract class Piece {
    private final Team team;
    private Position position;

    public Piece(Position position, Team team) {
        this.position = position;
        this.team = team;
    }

    protected abstract PieceMoveResult tryMove(Position targetPosition, PiecesOnChessBoard piecesOnChessBoard);

    public abstract PieceType getPieceType();

    public final PieceMoveResult move(Position targetPosition, PiecesOnChessBoard piecesOnChessBoard) {
        if (targetPosition == position) {
            return FAILURE;
        }
        PieceMoveResult pieceMoveResult = tryMove(targetPosition, piecesOnChessBoard);
        if (!pieceMoveResult.equals(FAILURE)) {
            position = targetPosition;
        }
        return pieceMoveResult;
    }

    public boolean isOn(Position position) {
        return this.position.equals(position);
    }

    public Team getTeam() {
        return team;
    }

    public int getColumn() {
        return position.getColumn();
    }

    public int getRow() {
        return position.getRow();
    }

    protected Position getPosition() {
        return position;
    }
}
