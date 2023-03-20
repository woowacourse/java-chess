package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.piece.info.Team;

public class Knight extends Piece {

    public Knight(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Position source, final Position destination) {
        if (source.equals(destination)) {
            return false;
        }
        int diffFile = destination.calculateFileDistance(source);
        int diffRank = destination.calculateRankDistance(source);

        return Math.abs(diffFile * diffRank) == 2;
    }

    @Override
    public boolean canAttack(final Position source, final Position destination) {
        return canMove(source, destination);
    }
    
    @Override
    public PieceType findType() {
        return PieceType.KNIGHT;
    }
}
