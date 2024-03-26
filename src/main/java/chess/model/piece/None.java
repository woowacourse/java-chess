package chess.model.piece;

import chess.model.material.Color;
import chess.model.position.Position;
import chess.model.position.Route;

public class None extends Piece {

    public None() {
        super(Color.NONE);
    }

    @Override
    public Route findRoute(Position source, Position target) {
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
