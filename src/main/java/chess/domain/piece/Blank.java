package chess.domain.piece;

import chess.domain.game.Color;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public class Blank extends Piece {
    public Blank() {
        super(Color.NONE, PieceType.BLANK);
    }

    @Override
    public String baseSignature() {
        return ".";
    }

    @Override
    public boolean isMovable(Map<Position, Piece> board, Position source, Position target) {
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
}
