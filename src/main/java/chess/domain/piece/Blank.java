package chess.domain.piece;

import java.util.List;

import chess.domain.Color;
import chess.domain.position.Position;

public class Blank extends Piece {

    public Blank() {
        super(Color.NONE);
    }

    @Override
    public String baseSignature() {
        return ".";
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        throw new IllegalArgumentException("기물이 없는 위치입니다.");
    }

    @Override
    public List<Position> findRoute(Position source, Position target) {
        throw new IllegalArgumentException("기물이 없는 위치입니다.");
    }

    @Override
    public double score() {
        throw new IllegalArgumentException("기물이 없는 위치입니다.");
    }

    @Override
    public boolean isBlank() {
        return true;
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
    public boolean isRook() {
        return false;
    }
}
