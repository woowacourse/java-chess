package chess.model.piece;

import chess.model.material.Color;
import chess.model.material.Type;
import chess.model.position.Position;
import java.util.Map;

public class None extends Piece {

    public None(Type type, Color color) {
        super(type, color);
    }

    @Override
    public void move(Position source, Position target, Map<Position, Piece> pieces) {
        throw new IllegalArgumentException("이동할 기물이 존재하지 않습니다.");
    }
}
