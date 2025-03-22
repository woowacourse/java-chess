package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    // Can move:
    // LEFT_UP, RIGHT_UP, LEFT_DOWN, RIGHT_DOWN, UP, DOWN, LEFT, RIGHT
    // Once

    @Override
    public boolean canMove(Position start, Position end, Piece endPiece, Map<Position, Piece> pieces) {
        List<Position> positions = getPaths(start, end, pieces);

        return positions.contains(end);
    }

    public List<Position> getPaths(Position start, Position end, Map<Position, Piece> pieces) {
        Position currentPosition = start;
        Piece currentPiece = pieces.get(start);
        List<Position> positions = new ArrayList<>();

        if (currentPosition.canMove(Movement.LEFT_UP)) {
            currentPosition = currentPosition.moveLeftUp();
        }
        if (currentPosition != start) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (currentPiece.isDifferentColor(pieceAtPosition.color)) positions.add(currentPosition);
        }

        currentPosition = start;
        if (currentPosition.canMove(Movement.RIGHT_UP)) {
            currentPosition = currentPosition.moveRightUp();
        }
        if (currentPosition != start) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (currentPiece.isDifferentColor(pieceAtPosition.color)) positions.add(currentPosition);
        }

        currentPosition = start;
        if (currentPosition.canMove(Movement.LEFT_DOWN)) {
            currentPosition = currentPosition.moveLeftDown();
        }
        if (currentPosition != start) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (currentPiece.isDifferentColor(pieceAtPosition.color)) positions.add(currentPosition);
        }

        currentPosition = start;
        if (currentPosition.canMove(Movement.RIGHT_DOWN)) {
            currentPosition = currentPosition.moveRightDown();
        }
        if (currentPosition != start) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (currentPiece.isDifferentColor(pieceAtPosition.color)) positions.add(currentPosition);
        }

        currentPosition = start;
        if (currentPosition.canMove(Movement.LEFT)) {
            currentPosition = currentPosition.moveLeft();
        }
        if (currentPosition != start) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (currentPiece.isDifferentColor(pieceAtPosition.color)) positions.add(currentPosition);
        }

        currentPosition = start;
        if (currentPosition.canMove(Movement.RIGHT)) {
            currentPosition = currentPosition.moveRight();
        }
        if (currentPosition != start) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (currentPiece.isDifferentColor(pieceAtPosition.color)) positions.add(currentPosition);
        }

        currentPosition = start;
        if (currentPosition.canMove(Movement.UP)) {
            currentPosition = currentPosition.moveUp();
        }
        if (currentPosition != start) {
            Piece pieceAtPosition = pieces.getOrDefault(currentPosition, new BlankPiece(Color.EMPTY));
            if (currentPiece.isDifferentColor(pieceAtPosition.color)) positions.add(currentPosition);
        }

        currentPosition = start;
        if (currentPosition.canMove(Movement.DOWN)) {
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
        return "K";
    }
}
