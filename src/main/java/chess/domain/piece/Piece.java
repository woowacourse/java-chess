package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public abstract class Piece {
    protected final Team team;

    public Piece(Team team) {
        this.team = team;
    }

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
        if (chessBoard.findPieceByPosition(destination).isOtherTeam(this)) {
            return false;
        }
        return true;
    }

    abstract boolean canNotMoveByItsOwnInPassing(Position start, Position destination);

    abstract boolean canNotMoveByBoardStatus(Position start, Position destination, ChessBoard chessBoard);

    public boolean isBlackTeam() {
        return team == Team.BLACK;
    }

    public Rank teamInitialPawnRank() {
        return team.getInitialPawnRank();
    }

    public boolean isOtherTeam(Piece other) {
        return this.team != other.team;
    }
}
