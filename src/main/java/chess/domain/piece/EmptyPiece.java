package chess.domain.piece;

import chess.domain.position.Position;

public class EmptyPiece extends Piece {

    private static EmptyPiece instance;

    private static final int EMPTY_POINT = 0;

    private EmptyPiece() {
        super(Color.EMPTY, Symbol.EMPTY);
    }

    public static EmptyPiece getInstance() {
        if (instance == null) {
            instance = new EmptyPiece();
        }
        return instance;
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        throw new IllegalStateException("비어있는 곳은 움직일 수 없습니다.");
    }

    @Override
    public double getPoint() {
        return EMPTY_POINT;
    }
}
