package chess.domain.piece;

import chess.direction.Direction;
import chess.domain.Position;

import static chess.domain.score.Score.EMPTY_SCORE;

public final class EmptyPiece extends Piece {

    private static final String name = ".";

    public EmptyPiece(PieceInfo pieceInfo) {
        super(pieceInfo.getName(), pieceInfo.getColor(), EMPTY_SCORE.getScore());
    }

    @Override
    public boolean isMovable(Position start, Position end, Color colorOfDestination) {
        return false;
    }

    @Override
    public int calculateKing(int count) {
        return count;
    }

    @Override
    public int calculatePawn(int count, Color color) {
        return count;
    }

    @Override
    public boolean findDirection(Direction direction, Position start, Position end, Piece piece) {
        return false;
    }
}
