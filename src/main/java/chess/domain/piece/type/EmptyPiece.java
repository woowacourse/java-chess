package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;

public final class EmptyPiece extends Piece {

    public EmptyPiece() {
        super(PieceType.EMPTY_PIECE, Color.NONE);
    }

    @Override
    public boolean isMovable(Position start, Position end, Color colorOfDestination) {
        throw new IllegalArgumentException("이동할 수 있는 기물이 없습니다");
    }

    @Override
    protected void checkMovableDirection(Direction direction) {
        // EmptyPiece doesn't need to move
    }
}
