package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public class Pawn extends LinearMovePiece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    protected String baseSignature() {
        return "p";
    }

    @Override
    public boolean isMovable(Map<Position, Piece> board, Position source, Position target) {
        return isForwardDoubleMove(source, target) || isStraightMove(board, source, target) || isDiagonalMove(board, source, target);
    }

    private boolean isForwardDoubleMove(Position source, Position target) {
        int displacementX = source.calculateDisplacementXTo(target);
        int displacementY = source.calculateDisplacementYTo(target);

        return displacementY == 2 * color.direction()
                && displacementX == 0
                && isNeverDisplaced();
    }

    private boolean isStraightMove(Map<Position, Piece> board, Position source, Position target) {
        Piece targetPiece = board.get(target);
        Color enemy = color.nextTurn();
        int displacementX = source.calculateDisplacementXTo(target);
        int displacementY = source.calculateDisplacementYTo(target);

        return displacementY == color.direction()
                && Math.abs(displacementX) == 0
                && !targetPiece.isSameColor(enemy);
    }

    private boolean isDiagonalMove(Map<Position, Piece> board, Position source, Position target) {
        Piece targetPiece = board.get(target);
        Color enemy = color.nextTurn();
        int displacementX = source.calculateDisplacementXTo(target);
        int displacementY = source.calculateDisplacementYTo(target);

        return displacementY == color.direction()
                && Math.abs(displacementX) == 1
                && targetPiece.isSameColor(enemy);
    }

    @Override
    public List<Position> findRoute(Position source, Position target) {
        return findLinearRoute(source, target);
    }

    @Override
    public double score() {
        return 1;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isEnPassantAvailable() {
        return moveCount == 1;
    }
}

