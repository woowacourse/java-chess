package chess.domain.piece;

import chess.domain.board.Position;
import chess.exception.PieceNotFoundException;

import java.util.List;

public class EmptyPiece extends Piece {
    private static final String NAME = ".";
    private static final double SCORE = 0;

    public EmptyPiece(PieceColor pieceColor, Position position) {
        super(NAME, SCORE, pieceColor, position);
    }

    @Override
    public List<Position> getPathTo(Position target) {
        return null;
    }

    @Override
    protected void validateDistance(Position targetPosition) {
        throw new PieceNotFoundException("움직일 수 있는 체스말이 아니므로 거리 확인이 불가능합니다.");
    }
}
