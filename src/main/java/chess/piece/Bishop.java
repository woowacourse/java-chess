package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    // Can move:
    // LEFT_UP, RIGHT_UP, LEFT_DOWN, RIGHT_DOWN
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

        while (currentPosition.canMove(Movement.LEFT_UP) && !currentPosition.equals(end)) {
            if (!pieces.containsKey(currentPosition)) positions.add(currentPosition);
            currentPosition = currentPosition.moveLeftUp();
        }
        if (currentPosition != start) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (currentPiece.isDifferentColor(pieceAtPosition.color)) positions.add(currentPosition);
        }

        currentPosition = start;
        while (currentPosition.canMove(Movement.RIGHT_UP) && !currentPosition.equals(end)) {
            if (!pieces.containsKey(currentPosition)) positions.add(currentPosition);
            currentPosition = currentPosition.moveRightUp();
        }
        if (currentPosition != start) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (currentPiece.isDifferentColor(pieceAtPosition.color)) positions.add(currentPosition);
        }

        currentPosition = start;
        while (currentPosition.canMove(Movement.LEFT_DOWN) && !currentPosition.equals(end)) {
            if (!pieces.containsKey(currentPosition)) positions.add(currentPosition);
            currentPosition = currentPosition.moveLeftDown();
        }
        if (currentPosition != start) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (currentPiece.isDifferentColor(pieceAtPosition.color)) positions.add(currentPosition);
        }

        currentPosition = start;
        while (currentPosition.canMove(Movement.RIGHT_DOWN) && !currentPosition.equals(end)) {
            if (!pieces.containsKey(currentPosition)) positions.add(currentPosition);
            currentPosition = currentPosition.moveRightDown();
        }
        if (currentPosition != start) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (currentPiece.isDifferentColor(pieceAtPosition.color)) positions.add(currentPosition);
        }

        return positions;
    }

    @Override
    public String toString() {
        return "B";
    }
}
