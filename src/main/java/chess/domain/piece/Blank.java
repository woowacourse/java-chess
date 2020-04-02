package chess.domain.piece;

import chess.domain.position.Position;
import chess.exception.PieceImpossibleMoveException;

public class Blank extends Piece {
    public Blank(final PieceType pieceType, final Position position) {
        super(pieceType, position);
    }

    public static Piece create(Position position) {
        return new Blank(PieceType.BLANK, position);
    }

    @Override
    public Piece moveTo(final Position toPosition) {
        throw new PieceImpossibleMoveException("빈 칸은 움직일 수 없습니다.");
    }
}
