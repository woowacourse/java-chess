package chess.domain.piece;

import chess.domain.board.InitialPosition;
import chess.domain.route.Route;
import chess.domain.square.Square;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Side side) {
        super(side);
    }

    @Override
    protected boolean hasFollowedRule(Square source, Square target, Route route) {
        boolean diagonalAttack = isDiagonalAttack(source, target, route);
        boolean notAttack = route.isTargetPieceEmpty();
        boolean forwardTwoAtInitialPosition = isInitialPosition(source) && isForwardTwo(source, target);
        boolean forwardOne = isForwardOne(source, target);

        return diagonalAttack || notAttack && (forwardTwoAtInitialPosition || forwardOne);
    }

    private boolean isDiagonalAttack(Square source, Square target, Route route) {
        return isAttackableDiagonal(source, target) && route.isOpponentTargetPiece(side());
    }

    private boolean isAttackableDiagonal(Square source, Square target) {
        if (isBlack()) {
            return source.hasHigherRankByOne(target) && source.hasOneFileGap(target);
        }
        return target.hasHigherRankByOne(source) && source.hasOneFileGap(target);
    }

    private boolean isInitialPosition(Square source) {
        List<Square> squares = InitialPosition.PAWN.positions(side());
        return squares.contains(source);
    }

    private boolean isForwardTwo(Square source, Square target) {
        if (isBlack()) {
            return source.hasHigherRankByTwo(target) && source.isSameFile(target);
        }
        return target.hasHigherRankByTwo(source) && source.isSameFile(target);
    }

    private boolean isForwardOne(Square source, Square target) {
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
    public PieceType pieceType() {
        return PieceType.PAWN;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
