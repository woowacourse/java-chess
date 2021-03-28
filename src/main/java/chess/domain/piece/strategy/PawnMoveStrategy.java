package chess.domain.piece.strategy;

import chess.domain.piece.Pieces;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

import static chess.domain.piece.Direction.*;

public class PawnMoveStrategy extends OneCellMoveStrategy {
    private static final int FIRST_RANGE = 2;
    private static final int AFTER_FIRST_RANGE = 1;

    private boolean isFirst = true;

    @Override
    public List<Position> makeRoutes(final Pieces basePieces, final Pieces targetPieces,
                                     final Position position) {
        if (basePieces.isBlack()) {
            return blackPositions(basePieces, targetPieces, position);
        }
        return whitePositions(basePieces, targetPieces, position);
    }

    private List<Position> blackPositions(final Pieces basePieces, final Pieces targetPieces,
                                          final Position position) {
        if (isFirst) {
            positions.addAll(makeDownRoutes(basePieces, targetPieces, position, FIRST_RANGE));
            positions.addAll(makeDownRightRoutes(basePieces, targetPieces, position));
            positions.addAll(makeDownLeftRoutes(basePieces, targetPieces, position));
            this.isFirst = false;
            return positions;
        }
        positions.addAll(makeDownRoutes(basePieces, targetPieces, position, AFTER_FIRST_RANGE));
        positions.addAll(makeDownRightRoutes(basePieces, targetPieces, position));
        positions.addAll(makeDownLeftRoutes(basePieces, targetPieces, position));
        return positions;
    }

    private List<Position> makeDownRoutes(final Pieces basePieces, final Pieces targetPieces,
                                          final Position position, final int range) {
        List<Position> positions = new ArrayList<>();
        for (int index = 0; index < range; index++) {
            positions.addAll(findPositions(basePieces, targetPieces, position, DOWN, -index));
        }
        return positions;
    }

    private List<Position> makeDownRightRoutes(final Pieces basePieces, final Pieces targetPieces,
                                               final Position position) {
        return findPositions(basePieces, targetPieces, position, RIGHT_DOWN);
    }

    private List<Position> makeDownLeftRoutes(final Pieces basePieces, final Pieces targetPieces,
                                              final Position position) {
        return findPositions(basePieces, targetPieces, position, LEFT_DOWN);
    }

    private List<Position> whitePositions(final Pieces basePieces, final Pieces targetPieces,
                                          final Position position) {
        if (isFirst) {
            positions.addAll(makeUpRoutes(basePieces, targetPieces, position, FIRST_RANGE));
            positions.addAll(makeUpRightRoutes(basePieces, targetPieces, position));
            positions.addAll(makeUpLeftRoutes(basePieces, targetPieces, position));
            this.isFirst = false;
            return positions;
        }
        positions.addAll(makeUpRoutes(basePieces, targetPieces, position, AFTER_FIRST_RANGE));
        positions.addAll(makeUpRightRoutes(basePieces, targetPieces, position));
        positions.addAll(makeUpLeftRoutes(basePieces, targetPieces, position));
        return positions;
    }

    private List<Position> makeUpRoutes(final Pieces basePieces, final Pieces targetPieces,
                                        final Position position, final int range) {
        List<Position> positions = new ArrayList<>();
        for (int index = 0; index < range; index++) {
            positions.addAll(findPositions(basePieces, targetPieces, position, UP, index));
        }
        return positions;
    }

    private List<Position> makeUpRightRoutes(final Pieces basePieces, final Pieces targetPieces,
                                             final Position position) {
        return findPositions(basePieces, targetPieces, position, RIGHT_UP);
    }

    private List<Position> makeUpLeftRoutes(final Pieces basePieces, final Pieces targetPieces,
                                            final Position position) {
        return findPositions(basePieces, targetPieces, position, LEFT_UP);
    }
}
