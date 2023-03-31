package chess.piece;

import java.util.Map;

import chess.board.Position;

public class Rook extends Piece {

    public Rook(final Team team) {
        super(team, PieceType.ROOK);
    }

    @Override
    protected void validatePathByType(final Position from, final Position to, final Map<Position, Piece> board) {
        validateStraight(from, to, board);
    }
}
