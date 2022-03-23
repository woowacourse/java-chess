package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;

import java.util.List;

public class Blank extends Piece {
    public Blank() {
        super(Color.NONE);
    }

    @Override
    public String baseSignature() {
        return ".";
    }

    @Override
    public boolean isBlank() {
        return true;
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        throw new IllegalArgumentException("기물이 없는 위치입니다.");
    }

    @Override
    public List<Position> findRoute(Position source, Position target) {
        throw new IllegalArgumentException("기물이 없는 위치입니다.");
    }
}
