package chess.domain.chessPiece;

import chess.domain.position.Position;

import java.util.List;

public class Rook extends ChessPiece {

    private static final String NAME = "R";

    public Rook(Color color) {
        super(color, NAME);
    }

    @Override
    public List<Position> getInitWhitePosition() {
        return List.of(new Position("a1"), new Position("h1"));
    }

    @Override
    public List<Position> getInitBlackPosition() {
        return List.of(new Position("a8"), new Position("h8"));
    }

}
