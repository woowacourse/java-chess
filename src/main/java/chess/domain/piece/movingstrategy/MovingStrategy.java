package chess.domain.piece.movingstrategy;

import chess.domain.piece.PieceColor;
import chess.domain.position.Position;

public abstract class MovingStrategy {

    public boolean isAbleToMove(Position from, Position to, PieceColor pieceColor) {
        if (from.equals(to)) {
            return false;
        }

        if (!isPossibleStep(from, to, pieceColor)) {
            return false;
        }

        return isPossibleDirection(from, to, pieceColor);
    }

    abstract boolean isPossibleStep(Position from, Position to, PieceColor pieceColor);

    abstract boolean isPossibleDirection(Position from, Position to, PieceColor pieceColor);

    public boolean isAbleToAttack(Position from, Position to, PieceColor pieceColor) {
        return isAbleToMove(from, to, pieceColor);
    }
}
