package chess.domain.piece.strategy;

import chess.domain.piece.Pieces;
import chess.domain.position.Position;

import java.util.List;

public class KingMoveStrategy extends OneCellMoveStrategy {
    @Override
    public List<Position> makeRoutes(final Pieces basePieces, final Pieces targetPieces,
                                     final Position position) {
        positions.addAll(makeUpRoutes(basePieces, targetPieces, position));
        positions.addAll(makeDownRoutes(basePieces, targetPieces, position));
        positions.addAll(makeLeftRoutes(basePieces, targetPieces, position));
        positions.addAll(makeRightRoutes(basePieces, targetPieces, position));
        positions.addAll(makeLeftUpRoutes(basePieces, targetPieces, position));
        positions.addAll(makeLeftDownRoutes(basePieces, targetPieces, position));
        positions.addAll(makeRightUpRoutes(basePieces, targetPieces, position));
        positions.addAll(makeRightDownRoutes(basePieces, targetPieces, position));
        return positions;
    }
}
