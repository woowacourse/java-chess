package chess.domain.piece;

import chess.domain.game.Color;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public class PawnMovingPattern implements MovingPattern {
    public boolean isMovable(Map<Position, Piece> board, Position source, Position target) {
        return isForwardDoubleMove(board, source, target) || isStraightMove(board, source, target) || isDiagonalMove(board, source, target);
    }

    private boolean isForwardDoubleMove(Map<Position, Piece> board, Position source, Position target) {
        int displacementX = source.calculateDisplacementXTo(target);
        int displacementY = source.calculateDisplacementYTo(target);

        Piece sourcePiece = board.get(source);
        Color color = sourcePiece.getColor();

        return displacementY == 2 * color.direction()
                && displacementX == 0
                && sourcePiece.isNeverDisplaced();
    }

    private boolean isStraightMove(Map<Position, Piece> board, Position source, Position target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);
        Color color = sourcePiece.getColor();
        Color enemy = color.nextTurn();

        int displacementX = source.calculateDisplacementXTo(target);
        int displacementY = source.calculateDisplacementYTo(target);

        return displacementY == color.direction()
                && Math.abs(displacementX) == 0
                && !targetPiece.isSameColor(enemy);
    }

    private boolean isDiagonalMove(Map<Position, Piece> board, Position source, Position target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);
        Color color = sourcePiece.getColor();
        Color enemy = color.nextTurn();

        int displacementX = source.calculateDisplacementXTo(target);
        int displacementY = source.calculateDisplacementYTo(target);

        return displacementY == color.direction()
                && Math.abs(displacementX) == 1
                && targetPiece.isSameColor(enemy);
    }

    public List<Position> findRoute(Position source, Position target) {
        return List.of();
    }
}

