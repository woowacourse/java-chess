package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    // Can move:
    // UP or DOWN
    // Until it hits edge of board
    // Can also move diagonally only if there's an opposing piece (use Color enum)

    @Override
    public boolean canMove(Position start, Position end, Piece endPiece, Map<Position, Piece> pieces) {
        if (color.isBlack()) {
            return canMoveBlack(start, end, endPiece, pieces);
        }
        if (color.isWhite()) {
            return canMoveWhite(start, end, endPiece, pieces);
        }
        return false;
    }

    private boolean canMoveWhite(Position start, Position end, Piece endPiece, Map<Position, Piece> pieces) {
        List<Position> positions = getWhitePaths(start, end, pieces);

        return positions.contains(end);
    }

    private boolean canMoveBlack(Position start, Position end, Piece endPiece, Map<Position, Piece> pieces) {
        List<Position> positions = getBlackPaths(start, end, pieces);

        return positions.contains(end);
    }

    public List<Position> getBlackPaths(Position start, Position end, Map<Position, Piece> pieces) {
        Position currentPosition = start;
        List<Position> positions = new ArrayList<>();

        if (currentPosition.canMove(Movement.DOWN)) {
            currentPosition = currentPosition.moveDown();
        }
        if (currentPosition.equals(end)) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (pieceAtPosition.isEmpty()) positions.add(currentPosition);
        }

        if (currentPosition.canMove(Movement.LEFT_DOWN)) {
            currentPosition = currentPosition.moveLeftDown();
        }
        if (currentPosition.equals(end)) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (pieceAtPosition.isWhite()) positions.add(currentPosition);
        }

        if (currentPosition.canMove(Movement.RIGHT_DOWN)) {
            currentPosition = currentPosition.moveRightDown();
        }
        if (currentPosition.equals(end)) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (pieceAtPosition.isWhite()) positions.add(currentPosition);
        }

        return positions;
    }

    public List<Position> getWhitePaths(Position start, Position end, Map<Position, Piece> pieces) {
        Position currentPosition = start;
        List<Position> positions = new ArrayList<>();

        if (currentPosition.canMove(Movement.UP)) {
            currentPosition = currentPosition.moveUp();
        }
        if (currentPosition.equals(end)) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (pieceAtPosition.isEmpty()) positions.add(currentPosition);
        }

        if (currentPosition.canMove(Movement.LEFT_UP)) {
            currentPosition = currentPosition.moveLeftUp();
        }
        if (currentPosition.equals(end)) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (pieceAtPosition.isBlack()) positions.add(currentPosition);
        }

        if (currentPosition.canMove(Movement.RIGHT_UP)) {
            currentPosition = currentPosition.moveRightUp();
        }
        if (currentPosition.equals(end)) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (pieceAtPosition.isBlack()) positions.add(currentPosition);
        }

        return positions;
    }

    @Override
    public String toString() {
        return "P";
    }
}
