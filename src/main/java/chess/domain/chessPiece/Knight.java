package chess.domain.chessPiece;

import chess.domain.position.Position;

import java.util.List;

public class Knight extends ChessPiece {

    private static final String NAME = "N";

    public Knight(Color color) {
        super(color, NAME);
    }

    @Override
    public List<Position> getInitWhitePosition() {
        return List.of(new Position("b1"), new Position("g1"));
    }

    @Override
    public List<Position> getInitBlackPosition() {
        return List.of(new Position("b8"), new Position("g8"));
    }

    @Override
    public void canMove(Position from, Position to) {

    }

}
