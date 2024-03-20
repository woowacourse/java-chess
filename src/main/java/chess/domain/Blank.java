package chess.domain;

import java.util.Set;

public class Blank extends Piece {
    public Blank(Position position) {
        super(position, Color.NONE);
    }

    @Override
    public Set<Position> findMovablePositions(Position destination) {
        throw new UnsupportedOperationException("해당 위치에 말이 없습니다.");
    }

    @Override
    public Piece update(Position destination) {
        throw new UnsupportedOperationException("해당 위치에 말이 없습니다.");
    }

    @Override
    public PieceType pieceType() {
        return PieceType.NONE;
    }
}
