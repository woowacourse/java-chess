package chess.domain.piece.strategy;

import chess.domain.piece.Pieces;
import chess.domain.position.Position;

import java.util.List;

public class LinearOrDiagonalMoveStrategy extends MultipleCellMoveStrategy {
    private static final int MAXIMUM_LINEAR_RANGE = 7;
    private static final int MAXIMUM_DIAGONAL_RANGE = 8;

    @Override
    public List<Position> makeLinearRoutes(final Pieces basePieces, final Pieces targetPieces,
                                           final Position position, final int range) {
        positions.addAll(makeUpRoutes(basePieces, targetPieces, position, range));
        positions.addAll(makeDownRoutes(basePieces, targetPieces,
                position, range - MAXIMUM_LINEAR_RANGE));
        positions.addAll(makeLeftRoutes(basePieces, targetPieces,
                position, range - MAXIMUM_LINEAR_RANGE));
        positions.addAll(makeRightRoutes(basePieces, targetPieces, position, range));
        return positions;
    }

    @Override
    public List<Position> makeDiagonalRoutes(final Pieces basePieces, final Pieces targetPieces,
                                             final Position position, final int range) {
        positions.addAll(makeLeftUpRoutes(basePieces, targetPieces, position, range));
        positions.addAll(makeLeftDownRoutes(basePieces, targetPieces,
                position, range - MAXIMUM_DIAGONAL_RANGE));
        positions.addAll(makeRightUpRoutes(basePieces, targetPieces, position, range));
        positions.addAll(makeRightDownRoutes(basePieces, targetPieces,
                position, range - MAXIMUM_DIAGONAL_RANGE));
        return positions;
    }
}
