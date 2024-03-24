package chess.domain.piece;

import chess.domain.board.Coordinate;
import java.util.List;
import java.util.Map;
import java.util.Set;

abstract class FixedMovePiece extends AbstractPiece {

    protected FixedMovePiece(final PieceType type, Team team) {
        super(type, team);
    }

    abstract Set<Map.Entry<Integer, Integer>> weights();

    @Override
    public List<Coordinate> legalNextCoordinates(final Coordinate now, final Coordinate destination) {
        return weights().stream()
                .filter(entry -> now.canMove(entry.getKey(), entry.getValue()))
                .filter(entry -> now.move(entry.getKey(), entry.getValue()).equals(destination))
                .map(entry -> now.move(entry.getKey(), entry.getValue()))
                .toList();
    }
}
