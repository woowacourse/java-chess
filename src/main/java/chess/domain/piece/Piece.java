package chess.domain.piece;

import java.util.Set;

import chess.domain.game.Team;
import chess.domain.move.Move;

public abstract class Piece {

    protected final Team team;
    protected final Set<Move> moves;

    public Piece(Team team, Set<Move> moves) {
        this.team = team;
        this.moves = moves;
    }

    public boolean isSameTeamWith(Piece otherPiece) {
        return team.equals(otherPiece.team);
    }

    public boolean hasTeam(Team other) {
        return team.equals(other);
    }

    public boolean hasMove(Move move) {
        return moves.stream()
                .anyMatch(it -> compareMove(it, move));
    }

    protected boolean compareMove(Move pieceMove, Move move) {
        return pieceMove.equals(move);
    }

    public boolean hasAttackMove(Move attackMove) {
        return hasMove(attackMove);
    }

    public Piece touch() {
        return this;
    }

    public boolean isEmpty() {
        return false;
    }

    public abstract PieceType getType();
}
