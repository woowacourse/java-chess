package chess.domain.chessPiece;

import chess.domain.position.Position;

import java.util.List;

public class Bishop extends ChessPiece {

    private static final String NAME = "B";

    public Bishop(Color color) {
        super(color, NAME);
    }

    @Override
    public List<Position> getInitWhitePosition() {
        return List.of(new Position("c1"), new Position("f1"));
    }

    @Override
    public List<Position> getInitBlackPosition() {
        return List.of(new Position("c8"), new Position("f8"));
    }


}
