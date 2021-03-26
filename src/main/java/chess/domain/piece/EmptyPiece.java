package chess.domain.piece;

import chess.domain.board.Paths;
import chess.domain.position.Coordinate;

public class EmptyPiece implements Piece {

    private static final EmptyPiece EMPTY_PIECE = new EmptyPiece();

    private EmptyPiece() {
    }

    public static EmptyPiece getInstance() {
        return EMPTY_PIECE;
    }

    @Override
    public Paths possiblePaths(Coordinate currentCoordinate) {
        throw new IllegalArgumentException("선택하신 곳은 빈 공간입니다.");
    }

    @Override
    public boolean isColor(PieceColor color) {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
