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
        if (isBlack()) {
            return hasFollowedBlackRule(source, target, movePath);
        }
        return hasFollowedWhiteRule(source, target, movePath);
    }

    private boolean hasFollowedBlackRule(Position source, Position target, MovePath movePath) {
        Side side = Side.BLACK;

        boolean downDiagonalAttack = isDownDiagonal(source, target) && movePath.isOpponentTargetPiece(side);
        boolean notAttack = movePath.isTargetPieceEmpty();
        boolean downTwoAtInitialPosition = isInitialPosition(source, side) && isDownTwo(source, target);
        boolean downOne = isDownOne(source, target);

        return downDiagonalAttack || notAttack && (downTwoAtInitialPosition || downOne);
    }

    private boolean isDownDiagonal(Position source, Position target) {
        return source.hasHigherRankByOne(target) && source.hasOneFileGap(target);
    }

    private boolean isInitialPosition(Position source, Side side) {
        List<Position> positions = InitialPosition.PAWN.positions(side);
        return positions.contains(source);
    }

    private boolean isDownTwo(Position source, Position target) {
        return source.hasHigherRankByTwo(target) && source.isSameFile(target);
    }

    private boolean isDownOne(Position source, Position target) {
        return source.hasHigherRankByOne(target) && source.isSameFile(target);
    }

    private boolean hasFollowedWhiteRule(Position source, Position target, MovePath movePath) {
        Side side = Side.WHITE;

        return target.hasHigherRankByOne(source) && source.isSameFile(target);
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
