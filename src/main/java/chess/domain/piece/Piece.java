package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;

public abstract class Piece {
    private final Team team;

    public Piece(Team team) {
        this.team = team;
    }

    public abstract boolean canMove(Position start, Position destination, ChessBoard chessBoard);

    public boolean isBlackTeam() {
        return team == Team.BLACK;
    }

    public boolean isSameTeam(Piece otherPiece) {
        return this.team == otherPiece.team;
    }

    public boolean isOtherTeam(Piece otherPiece) {
        return !isSameTeam(otherPiece);
    }

    public boolean isOtherTeam(Team team) {
        return this.team != team;
    }
}
