package chess.domain.piece.king;

import chess.domain.board.position.Position;
import chess.domain.direction.Direction;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.Score;

import java.util.List;

public abstract class King extends Piece {

    private static final int ABLE_DISTANCE_TO_MOVE = 1;

    public King(final Owner owner) {
        this(owner, new Score(0.0d), Direction.allDirections());
    }

    public King(final Owner owner, Score score, List<Direction> directions) {
        super(owner, score, directions);
    }

    public static King getInstanceOf(final Owner owner) {
        if (owner.isSameTeam(Owner.BLACK)) {
            return BlackKing.getInstance();
        }

        if (owner.isSameTeam(Owner.WHITE)) {
            return WhiteKing.getInstance();
        }

        throw new IllegalArgumentException("Invalid King");
    }

    @Override
    public boolean isReachable(final Position source, final Position target, final Piece targetPiece) {
        return true;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public int getMaxDistance() {
        return ABLE_DISTANCE_TO_MOVE;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}