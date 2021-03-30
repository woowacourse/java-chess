package chess.domain.piece;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pawn extends Division {

    public static final int PAWN_SCORE = 1;

    public Pawn(Color color) {
        super(color, "p");
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double score() {
        return PAWN_SCORE;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    public List<List<Position>> movablePositions(Position from) {
        List<Position> positions = new ArrayList<>();
        positions.add(from.move(0, color.moveUnit()));
        if (from.hasRow(color.initPawnRow())) {
            positions.add(from.move(0, 2 * color.moveUnit()));
        }
        return Collections.singletonList(positions);
    }

    @Override
    public List<List<Position>> killablePositions(Position from) {
        List<Position> positions = new ArrayList<>();
        positions.add(from.move(1, color.moveUnit()));
        positions.add(from.move(-1, color.moveUnit()));
        return Collections.singletonList(positions);
    }
}
