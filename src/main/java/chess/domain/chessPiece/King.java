package chess.domain.chessPiece;

import chess.domain.position.Position;

import java.util.List;

public class King extends ChessPiece {

    private static final String NAME = "K";

    public King(Color color) {
        super(color, NAME);
    }

    @Override
    public List<Position> getInitWhitePosition() {
        return List.of(new Position("e1"));
    }

    @Override
    public List<Position> getInitBlackPosition() {
        return List.of(new Position("e8"));
    }

}
