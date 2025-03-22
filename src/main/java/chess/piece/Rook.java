package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    // Can move:
    // UP, DOWN, LEFT, RIGHT
    // Until it hits edge of board

    @Override
    public boolean canMove(Position start, Position end, Piece endPiece, Map<Position, Piece> pieces) {
        List<Position> positions = getPaths(start, end, pieces);

        return positions.contains(end);
    }

    public List<Position> getPaths(Position start, Position end, Map<Position, Piece> pieces) {
        Position currentPosition = start;
        Piece currentPiece = pieces.get(start);
        List<Position> positions = new ArrayList<>();

        while (currentPosition.canMove(Movement.LEFT) && !currentPosition.equals(end)) {
            if (!pieces.containsKey(currentPosition)) positions.add(currentPosition);
            currentPosition = currentPosition.moveLeft();
        }
        if (currentPosition != start) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (currentPiece.isDifferentColor(pieceAtPosition.color)) positions.add(currentPosition);
        }

        currentPosition = start;
        while (currentPosition.canMove(Movement.RIGHT) && !currentPosition.equals(end)) {
            if (!pieces.containsKey(currentPosition)) positions.add(currentPosition);
            currentPosition = currentPosition.moveRight();
        }
        if (currentPosition != start) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (currentPiece.isDifferentColor(pieceAtPosition.color)) positions.add(currentPosition);
        }

        currentPosition = start;
        while (currentPosition.canMove(Movement.UP) && !currentPosition.equals(end)) {
            if (!pieces.containsKey(currentPosition)) positions.add(currentPosition);
            currentPosition = currentPosition.moveUp();
        }
        if (currentPosition != start) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (currentPiece.isDifferentColor(pieceAtPosition.color)) positions.add(currentPosition);
        }

        currentPosition = start;
        while (currentPosition.canMove(Movement.DOWN) && !currentPosition.equals(end)) {
            if (!pieces.containsKey(currentPosition)) positions.add(currentPosition);
            currentPosition = currentPosition.moveDown();
        }
        if (currentPosition != start) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (currentPiece.isDifferentColor(pieceAtPosition.color)) positions.add(currentPosition);
        }

        return positions;
    }

    @Override
    public String toString() {
        return "R";
    }
}
