package domain.piece;

import domain.position.Position;
import domain.position.Rank;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        if (source.hasRank(Rank.TWO) || source.hasRank(Rank.SEVEN)) {
            if (isBlack()) {
                return source.firstMoveOfBlackPawn(target) || source.canAttackDiagonalOfBlack(target);
            }
            return source.firstMoveOfWhitePawn(target) || source.canAttackDiagonalOfWhite(target);
        }
        if (isBlack()) {
            return source.notFirstMoveOfBlackPawn(target) || source.canAttackDiagonalOfBlack(target);
        }
        return source.notFirstMoveOfWhitePawn(target) || source.canAttackDiagonalOfWhite(target);
    }

    @Override
    public String display() {
        return "P";
    }
}
