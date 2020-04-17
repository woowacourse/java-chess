package chess.domain.piece;

import chess.exception.PieceImpossibleMoveException;

public class Blank extends Piece {
    public Blank(final PieceType pieceType) {
        super(pieceType);
    }

    @Override
    public Piece getNextPiece() {
        throw new PieceImpossibleMoveException("빈칸은 움직일 수 없습니다.");
    }
}
