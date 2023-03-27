package chess.piece;

import java.util.Map;

import chess.board.Position;

public class EmptyPiece extends Piece {

    public EmptyPiece() {
        super(Team.EMPTY, PieceType.EMPTY);
    }

    @Override
    public void validateMove(final Position from, final Position to, final Map<Position, Piece> board) {
        throw new IllegalArgumentException("빈 말은 움직일 수 없습니다.");
    }

    @Override
    protected void validatePathByType(final Position from, final Position to, final Map<Position, Piece> board) {
        throw new IllegalArgumentException("빈 말은 움직일 수 없습니다.");
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
