package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;

import java.util.List;

public final class EmptyPiece extends Piece {

    private static final EmptyPiece emptyPiece = new EmptyPiece(PieceType.EMPTY_PIECE, Color.NONE);

    private EmptyPiece(PieceType pieceType, Color color) {
        super(pieceType, color);
    }

    public static EmptyPiece of() {
        return emptyPiece;
    }

    @Override
    public boolean isMovable(final Position start, final Position end, final Piece destinationPiece) {
        throw new IllegalArgumentException("이동할 수 있는 기물이 없습니다");
    }

    @Override
    public List<Position> createRoute(final Position start, final Position end) {
        throw new IllegalArgumentException("이동할 수 있는 기물이 없습니다");
    }

}
