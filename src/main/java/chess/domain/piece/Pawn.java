package chess.domain.piece;

import chess.domain.board.InitialPosition;
import chess.domain.route.Route;
import chess.domain.position.Position;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Side side) {
        super(side);
    }

    @Override
    protected boolean hasFollowedRule(Position source, Position target, Route route) {
        boolean diagonalAttack = isDiagonalAttack(source, target, route);
        boolean notAttack = route.isTargetPieceEmpty();
        boolean forwardTwoAtInitialPosition = isInitialPosition(source) && isForwardTwo(source, target);
        boolean forwardOne = isForwardOne(source, target);

        return diagonalAttack || notAttack && (forwardTwoAtInitialPosition || forwardOne);
    }

    private boolean isDiagonalAttack(Position source, Position target, Route route) {
        return isAttackableDiagonal(source, target) && route.isOpponentTargetPiece(side());
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
}
