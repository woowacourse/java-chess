package chess.domain.piece;

import chess.domain.position.Position;
import chess.exception.PieceImpossibleMoveException;

public class Blank extends Piece {
    public Blank(final PieceType pieceType, final char representation, final Team team, final Position position) {
        super(pieceType, representation, team, position);
    }

    public static Piece create(Position position) {
        return new Blank(PieceType.BLANK, '.', Team.BLANK, position);
    }

    @Override
    public Piece moveTo(final Position toPosition) {
        throw new PieceImpossibleMoveException("빈 칸은 움직일 수 없습니다.");
    }
}
