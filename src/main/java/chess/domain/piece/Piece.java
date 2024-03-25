package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;

public abstract class Piece {
    protected final Team team;

    protected Piece(Team team) {
        this.team = team;
    }

    abstract boolean canNotMoveByItsOwnInPassing(Position start, Position destination);

    abstract boolean canNotMoveByBoardStatus(Position start, Position destination, ChessBoard chessBoard);

    public final boolean canMove(Position start, Position destination, ChessBoard chessBoard) {
        if (start == destination) {
            return false;
        }
        if (isFriendlyPieceAtDestination(destination, chessBoard)) {
            return false;
        }
        if (canNotMoveByItsOwnInPassing(start, destination)) {
            return false;
        }
        if (canNotMoveByBoardStatus(start, destination, chessBoard)) {
            return false;
        }
        return true;
    }

    private boolean isFriendlyPieceAtDestination(Position destination, ChessBoard chessBoard) {
        if (chessBoard.positionIsEmpty(destination)) {
            return false;
        }
        if (chessBoard.findPieceByPosition(destination).isOtherTeam(team)) {
            return false;
        }
        return true;
    }

    public boolean isBlackTeam() {
        return team == Team.BLACK;
    }

    public boolean isOtherTeam(Team other) {
        return this.team != other;
    }
}
