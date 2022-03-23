package chess.domain.chessPiece;

import chess.domain.position.Position;

import java.util.List;

public class Queen extends ChessPiece {

    private static final String NAME = "Q";

    public Queen(Color color) {
        super(color, NAME);
    }

    @Override
    public List<Position> getInitWhitePosition() {
        return List.of(new Position("d1"));
    }

    @Override
    public List<Position> getInitBlackPosition() {
        return List.of(new Position("d8"));
    }

    @Override
    public void canMove(Position from, Position to) {

    }

}
