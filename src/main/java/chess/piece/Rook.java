package chess.piece;

import chess.board.Position;

public final class Rook extends Piece {

    public Rook(final Team team) {
        super(team, PieceType.ROOK);
    }

    @Override
    public boolean isMovable(final Position from, final Position to) {

        return (from.getRank() == to.getRank()) || (from.getFile() == to.getFile());
    }
}
