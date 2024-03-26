package chess.domain.piece;

import chess.domain.position.Position;

public final class NullPiece extends Piece {
    private static final NullPiece instance = new NullPiece(Team.EMPTY);

    private NullPiece(Team team) {
        super(team);
    }

    public static NullPiece getInstance() {
        return instance;
    }

    @Override
    public boolean canMove(Position start, Position destination, Piece destinationPiece) {
        throw new UnsupportedOperationException("NullPiece 객체입니다");
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
