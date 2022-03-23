package chess.domain.piece;

import chess.domain.Position;

public final class Rook extends Piece {

    private static final String ROOK_NAME = "R";

    public Rook(Color color, Position position) {
        super(color, ROOK_NAME, position);
    }

    public void move(Position position) {
        if (!isPossibleToMove(position)) {
            throw new IllegalStateException("룩은 상하좌우 방향으로만 움직일 수 있습니다.");
        }
    }

    private boolean isPossibleToMove(Position position) {
        return position.equalsColumnOrRow(getPosition());
    }
}
