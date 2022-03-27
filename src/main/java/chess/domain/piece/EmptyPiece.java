package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.attribute.Name;
import chess.domain.piece.attribute.Team;

public final class EmptyPiece extends Piece {

    private static final String NO_MOVE_MESSAGE = "해당 칸에는 움직일 기물이 없습니다.";

    public EmptyPiece() {
        super(new Name("."), Team.NONE, null);
    }

    @Override
    public boolean canMove(Piece targetPiece, Position from, Position to) {
        throw new IllegalArgumentException(NO_MOVE_MESSAGE);
    }

    @Override
    public boolean isPiece() {
        return false;
    }
}
