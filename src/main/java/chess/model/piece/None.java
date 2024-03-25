package chess.model.piece;

import chess.model.material.Color;
import chess.model.position.Position;
import java.util.Map;

public class None extends Piece {

    public None(Color color) {
        super(color);
    }

    @Override
    public void move(Position source, Position target, Map<Position, Piece> pieces) {
        throw new IllegalArgumentException("이동할 기물이 존재하지 않습니다.");
    }

    @Override
    public boolean isExist() {
        return !isNone();
    }

    @Override
    public boolean isNone() {
        return true;
    }
}
