package domain.piece;

import domain.piece.Basis;

import java.util.List;

public class Pawn extends Basis {
    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean canMove(List<String> from, List<String> to) {
        return true;
    }
}
