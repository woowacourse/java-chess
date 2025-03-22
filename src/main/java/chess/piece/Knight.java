package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    // Can move:
    // UP_UP_RIGHT, UP_UP_LEFT, DOWN_DOWN_RIGHT, DOWN_DOWN_LEFT, LEFT_LEFT_UP, LEFT_LEFT_DOWN, RIGHT_RIGHT_UP, RIGHT_RIGHT_DOWN
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

        while (currentPosition.canMove(Movement.UP_UP_RIGHT) && !currentPosition.equals(end)) {
            if (!pieces.containsKey(currentPosition)) positions.add(currentPosition);
            currentPosition = currentPosition.moveUp();
            currentPosition = currentPosition.moveUp();
            currentPosition = currentPosition.moveRight();
        }
        if (currentPosition != start) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (currentPiece.isDifferentColor(pieceAtPosition.color)) positions.add(currentPosition);
        }

        currentPosition = start;
        while (currentPosition.canMove(Movement.UP_UP_LEFT) && !currentPosition.equals(end)) {
            if (!pieces.containsKey(currentPosition)) positions.add(currentPosition);
            currentPosition = currentPosition.moveUp();
            currentPosition = currentPosition.moveUp();
            currentPosition = currentPosition.moveLeft();
        }
        if (currentPosition != start) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (currentPiece.isDifferentColor(pieceAtPosition.color)) positions.add(currentPosition);
        }

        currentPosition = start;
        while (currentPosition.canMove(Movement.DOWN_DOWN_RIGHT) && !currentPosition.equals(end)) {
            if (!pieces.containsKey(currentPosition)) positions.add(currentPosition);
            currentPosition = currentPosition.moveDown();
            currentPosition = currentPosition.moveDown();
            currentPosition = currentPosition.moveRight();
        }
        if (currentPosition != start) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (currentPiece.isDifferentColor(pieceAtPosition.color)) positions.add(currentPosition);
        }

        currentPosition = start;
        while (currentPosition.canMove(Movement.DOWN_DOWN_LEFT) && !currentPosition.equals(end)) {
            if (!pieces.containsKey(currentPosition)) positions.add(currentPosition);
            currentPosition = currentPosition.moveDown();
            currentPosition = currentPosition.moveDown();
            currentPosition = currentPosition.moveLeft();
        }
        if (currentPosition != start) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (currentPiece.isDifferentColor(pieceAtPosition.color)) positions.add(currentPosition);
        }

        currentPosition = start;
        while (currentPosition.canMove(Movement.LEFT_LEFT_DOWN) && !currentPosition.equals(end)) {
            if (!pieces.containsKey(currentPosition)) positions.add(currentPosition);
            currentPosition = currentPosition.moveLeft();
            currentPosition = currentPosition.moveLeft();
            currentPosition = currentPosition.moveDown();
        }
        if (currentPosition != start) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (currentPiece.isDifferentColor(pieceAtPosition.color)) positions.add(currentPosition);
        }

        currentPosition = start;
        while (currentPosition.canMove(Movement.LEFT_LEFT_UP) && !currentPosition.equals(end)) {
            if (!pieces.containsKey(currentPosition)) positions.add(currentPosition);
            currentPosition = currentPosition.moveLeft();
            currentPosition = currentPosition.moveLeft();
            currentPosition = currentPosition.moveUp();
        }
        if (currentPosition != start) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (currentPiece.isDifferentColor(pieceAtPosition.color)) positions.add(currentPosition);
        }

        currentPosition = start;
        while (currentPosition.canMove(Movement.RIGHT_RIGHT_DOWN) && !currentPosition.equals(end)) {
            if (!pieces.containsKey(currentPosition)) positions.add(currentPosition);
            currentPosition = currentPosition.moveRight();
            currentPosition = currentPosition.moveRight();
            currentPosition = currentPosition.moveDown();
        }
        if (currentPosition != start) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (currentPiece.isDifferentColor(pieceAtPosition.color)) positions.add(currentPosition);
        }

        currentPosition = start;
        while (currentPosition.canMove(Movement.RIGHT_RIGHT_UP) && !currentPosition.equals(end)) {
            if (!pieces.containsKey(currentPosition)) positions.add(currentPosition);
            currentPosition = currentPosition.moveLeft();
            currentPosition = currentPosition.moveLeft();
            currentPosition = currentPosition.moveUp();
        }
        if (currentPosition != start) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (currentPiece.isDifferentColor(pieceAtPosition.color)) positions.add(currentPosition);
        }

        return positions;
    }

    @Override
    public String toString() {
        return "N";
    }
}
