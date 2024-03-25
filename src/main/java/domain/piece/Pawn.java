package domain.piece;

import domain.Direction;
import domain.Rank;
import domain.Square;
import domain.Team;

import java.util.Map;

public class Pawn extends Piece {
    public Pawn(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Square source, final Square target, final Map<Square, Piece> pieces) {
        if (this.team == Team.BLACK) {
            if (pieces.containsKey(target)) {
                if (hasSameTeamPieceOnTarget(source, target, pieces)) {
                    return false;
                }
                return source.next(Direction.SOUTH_EAST).equals(target)
                        || source.next(Direction.SOUTH_WEST).equals(target);
            }
            return source.next(Direction.SOUTH).equals(target)
                    || (source.next(Direction.SOUTH_SOUTH).equals(target) && source.isRank(Rank.SEVEN));
        }
        if (pieces.containsKey(target)) {
            if (hasSameTeamPieceOnTarget(source, target, pieces)) {
                return false;
            }
            return source.next(Direction.NORTH_EAST).equals(target)
                    || source.next(Direction.NORTH_WEST).equals(target);
        }
        return source.next(Direction.NORTH).equals(target)
                || (source.next(Direction.NORTH_NORTH).equals(target) && source.isRank(Rank.TWO));
    }

    private static boolean hasSameTeamPieceOnTarget(final Square source, final Square target, final Map<Square, Piece> pieces) {
        final Piece targetPiece = pieces.get(target);
        final Piece sourcePiece = pieces.get(source);
        return targetPiece.isSameTeam(sourcePiece);
    }
}
