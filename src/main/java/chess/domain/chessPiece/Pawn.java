package chess.domain.chessPiece;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece {

    private static final String NAME = "P";

    public Pawn(Color color) {
        super(color, NAME);
    }

    @Override
    public List<Position> getInitWhitePosition() {
        List<Position> list = new ArrayList<>();
        for (int i = 'a'; i <= 'h'; i++) {
            list.add(new Position((char) i + "2"));
        }
        return list;
    }

    @Override
    public List<Position> getInitBlackPosition() {
        List<Position> list = new ArrayList<>();
        for (int i = 'a'; i <= 'h'; i++) {
            list.add(new Position((char) i + "7"));
        }
        return list;
    }

    @Override
    public void canMove(Position from, Position to) {

    }

}
