package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public abstract class Piece {
    protected final Team team;

    public Piece(Team team) {
        this.team = team;
    }

    public abstract boolean canMove(Position start, Position destination, ChessBoard chessBoard);

    public boolean isBlackTeam() {
        return team == Team.BLACK;
    }

    public Rank teamInitialPawnRow() {
        return team.getInitialPawnRank();
    }

    public boolean isOtherTeam(Piece other) {
        return this.team != other.team;
    }
}
