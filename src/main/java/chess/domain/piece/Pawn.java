package chess.domain.piece;

import chess.domain.board.Coordinate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Pawn extends AbstractPiece {
    protected Pawn(final Team team) {
        super(PieceType.PAWN, team);
    }

    abstract Set<Entry<Integer, Integer>> straightWeights();

    abstract Set<Entry<Integer, Integer>> diagonalWeights();

    @Override
    public List<Coordinate> legalNextCoordinates(final Coordinate now, final Coordinate destination) {
        List<Coordinate> legalNextCoordinates = new ArrayList<>();
        legalNextCoordinates.addAll(straightLegalNextCoordinates(now));
        legalNextCoordinates.addAll(diagonalLegalNextCoordinates(now));
        return legalNextCoordinates;
    }

    private Set<Coordinate> straightLegalNextCoordinates(final Coordinate now) {
        return straightWeights().stream()
                .filter(entry -> now.canMove(entry.getKey(), entry.getValue()))
                .map(entry -> now.move(entry.getKey(), entry.getValue()))
                .collect(Collectors.toSet());
    }

    private Set<Coordinate> diagonalLegalNextCoordinates(final Coordinate now) {
        return diagonalWeights().stream()
                .filter(entry -> now.canMove(entry.getKey(), entry.getValue()))
                .map(entry -> now.move(entry.getKey(), entry.getValue()))
                .collect(Collectors.toSet());
    }
}
