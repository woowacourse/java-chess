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
    boolean hasFollowedRule(Position source, Position target, Route route) {
        if (isBlack()) {
            return hasFollowedBlackRule(source, target, route);
        }
        return hasFollowedWhiteRule(source, target, route);
    }

    private boolean hasFollowedBlackRule(Position source, Position target, Route route) {
        Side side = Side.BLACK;

        boolean diagonalAttack = isDownDiagonal(source, target) && route.isOpponentTargetPiece(side);
        boolean notAttack = route.isTargetPieceEmpty();
        boolean downTwoAtInitialPosition = isInitialPosition(source, side) && isDownTwo(source, target);
        boolean downOne = isDownOne(source, target);

        return diagonalAttack || notAttack && (downTwoAtInitialPosition || downOne);
    }

    private boolean hasFollowedWhiteRule(Position source, Position target, Route route) {
        Side side = Side.WHITE;

        boolean diagonalAttack = isUpDiagonal(source, target) && route.isOpponentTargetPiece(side);
        boolean notAttack = route.isTargetPieceEmpty();
        boolean upTwoAtInitialPosition = isInitialPosition(source, side) && isUpTwo(source, target);
        boolean upOne = isUpOne(source, target);

        return diagonalAttack || notAttack && (upTwoAtInitialPosition || upOne);
    }

    private boolean isDownDiagonal(Position source, Position target) {
        return source.hasHigherRankByOne(target) && source.hasOneFileGap(target);
    }

    private boolean isUpDiagonal(Position source, Position target) {
        return target.hasHigherRankByOne(source) && source.hasOneFileGap(target);
    }

    private boolean isInitialPosition(Position source, Side side) {
        List<Position> positions = InitialPosition.PAWN.positions(side);
        return positions.contains(source);
    }

    private boolean isDownTwo(Position source, Position target) {
        return source.hasHigherRankByTwo(target) && source.isSameFile(target);
    }

    private boolean isUpTwo(Position source, Position target) {
        return target.hasHigherRankByTwo(source) && source.isSameFile(target);
    }

    private boolean isDownOne(Position source, Position target) {
        return source.hasHigherRankByOne(target) && source.isSameFile(target);
    }

    private boolean isUpOne(Position source, Position target) {
        return target.hasHigherRankByOne(source) && source.isSameFile(target);
    }
}
