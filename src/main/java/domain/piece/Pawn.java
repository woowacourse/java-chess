package domain.piece;

import domain.InitialPosition;
import domain.MovePath;
import domain.Position;
import domain.Side;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Side side) {
        super(side);
    }

    @Override
    protected boolean hasFollowedRule(Position source, Position target, MovePath movePath) {
        boolean diagonalAttack = isDiagonalAttack(source, target, movePath);
        boolean notAttack = movePath.isTargetPieceEmpty();
        boolean forwardTwoAtInitialPosition = isInitialPosition(source) && isForwardTwo(source, target);
        boolean forwardOne = isForwardOne(source, target);

        return diagonalAttack || notAttack && (forwardTwoAtInitialPosition || forwardOne);
    }

    private boolean isDiagonalAttack(Position source, Position target, MovePath movePath) {
        return isAttackableDiagonal(source, target) && movePath.isOpponentTargetPiece(side());
    }

    private boolean isAttackableDiagonal(Position source, Position target) {
        if (isBlack()) {
            return source.hasHigherRankByOne(target) && source.hasOneFileGap(target);
        }
        return target.hasHigherRankByOne(source) && source.hasOneFileGap(target);
    }

    private boolean isInitialPosition(Position source) {
        List<Position> positions = InitialPosition.PAWN.positions(side());
        return positions.contains(source);
    }

    private boolean isForwardTwo(Position source, Position target) {
        if (isBlack()) {
            return source.hasHigherRankByTwo(target) && source.isSameFile(target);
        }
        return target.hasHigherRankByTwo(source) && source.isSameFile(target);
    }

    private boolean isForwardOne(Position source, Position target) {
        if (isBlack()) {
            return source.hasHigherRankByOne(target) && source.isSameFile(target);
        }
        return target.hasHigherRankByOne(source) && source.isSameFile(target);
    }

    private Side side() {
        if (isBlack()) {
            return Side.BLACK;
        }
        return Side.WHITE;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
