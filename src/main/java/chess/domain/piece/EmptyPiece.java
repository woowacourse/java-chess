package chess.domain.piece;

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
}
