package chess.domain.piece.strategy;

import chess.domain.piece.Pieces;
import chess.domain.position.Position;

import java.util.List;

import static chess.domain.piece.Direction.*;

public class KnightMoveStrategy extends OneCellMoveStrategy {
    @Override
    public List<Position> makeRoutes(final Pieces basePieces, final Pieces targetPieces,
                                     final Position position) {
        positions.addAll(makeLeftTwoUpRoutes(basePieces, targetPieces, position));
        positions.addAll(makeLeftTwoDownRoutes(basePieces, targetPieces, position));
        positions.addAll(makeRightTwoUpRoutes(basePieces, targetPieces, position));
        positions.addAll(makeRightTwoDownRoutes(basePieces, targetPieces, position));
        positions.addAll(makeTwoLeftUp(basePieces, targetPieces, position));
        positions.addAll(makeTwoLeftDown(basePieces, targetPieces, position));
        positions.addAll(makeTwoRightUp(basePieces, targetPieces, position));
        positions.addAll(makeTwoRightDown(basePieces, targetPieces, position));
        return positions;
    }

    private List<Position> makeLeftTwoUpRoutes(final Pieces basePieces, final Pieces targetPieces,
                                               final Position position) {
        return findPositions(basePieces, targetPieces, position, LEFT_TWO_UP);
    }

    private List<Position> makeLeftTwoDownRoutes(final Pieces basePieces, final Pieces targetPieces,
                                                 final Position position) {
        return findPositions(basePieces, targetPieces, position, LEFT_TWO_DOWN);
    }

    private List<Position> makeRightTwoUpRoutes(final Pieces basePieces, final Pieces targetPieces,
                                                final Position position) {
        return findPositions(basePieces, targetPieces, position, RIGHT_TWO_UP);
    }

    private List<Position> makeRightTwoDownRoutes(final Pieces basePieces, final Pieces targetPieces,
                                                  final Position position) {
        return findPositions(basePieces, targetPieces, position, RIGHT_TWO_DOWN);
    }

    private List<Position> makeTwoLeftUp(final Pieces basePieces, final Pieces targetPieces,
                                         final Position position) {
        return findPositions(basePieces, targetPieces, position, TWO_LEFT_UP);
    }

    private List<Position> makeTwoLeftDown(final Pieces basePieces, final Pieces targetPieces,
                                           final Position position) {
        return findPositions(basePieces, targetPieces, position, TWO_LEFT_DOWN);
    }

    private List<Position> makeTwoRightUp(final Pieces basePieces, final Pieces targetPieces,
                                          final Position position) {
        return findPositions(basePieces, targetPieces, position, TWO_RIGHT_UP);
    }

    private List<Position> makeTwoRightDown(final Pieces basePieces, final Pieces targetPieces,
                                            final Position position) {
        return findPositions(basePieces, targetPieces, position, TWO_RIGHT_DOWN);
    }
}
