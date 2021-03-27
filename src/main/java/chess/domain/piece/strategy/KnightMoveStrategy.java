package chess.domain.piece.strategy;

import chess.domain.piece.Pieces;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.Collections;
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
        List<Position> positions = new ArrayList<>();
        int rank = position.getRank().getValue() + LEFT_TWO_UP.getRow();
        int file = position.getFile().getValue() + LEFT_TWO_UP.getColumn();
        if (rank > 8 || file < 1) {
            return positions;
        }
        return findPositions(basePieces, targetPieces, rank, file);
    }

    private List<Position> makeLeftTwoDownRoutes(final Pieces basePieces, final Pieces targetPieces,
                                                 final Position position) {
        List<Position> positions = new ArrayList<>();
        int rank = position.getRank().getValue() + LEFT_TWO_DOWN.getRow();
        int file = position.getFile().getValue() + LEFT_TWO_DOWN.getColumn();
        if (rank < 1 || file < 1) {
            return positions;
        }
        return findPositions(basePieces, targetPieces, rank, file);
    }

    private List<Position> makeRightTwoUpRoutes(final Pieces basePieces, final Pieces targetPieces,
                                                final Position position) {
        List<Position> positions = new ArrayList<>();
        int rank = position.getRank().getValue() + RIGHT_TWO_UP.getRow();
        int file = position.getFile().getValue() + RIGHT_TWO_UP.getColumn();
        if (rank > 8 || file > 8) {
            return positions;
        }
        return findPositions(basePieces, targetPieces, rank, file);
    }

    private List<Position> makeRightTwoDownRoutes(final Pieces basePieces, final Pieces targetPieces,
                                                  final Position position) {
        List<Position> positions = new ArrayList<>();
        int rank = position.getRank().getValue() + RIGHT_TWO_DOWN.getRow();
        int file = position.getFile().getValue() + RIGHT_TWO_DOWN.getColumn();
        if (rank < 1 || file > 8) {
            return positions;
        }
        return findPositions(basePieces, targetPieces, rank, file);
    }

    private List<Position> makeTwoLeftUp(final Pieces basePieces, final Pieces targetPieces,
                                         final Position position) {
        List<Position> positions = new ArrayList<>();
        int rank = position.getRank().getValue() + TWO_LEFT_UP.getRow();
        int file = position.getFile().getValue() + TWO_LEFT_UP.getColumn();
        if (rank > 8 || file < 1) {
            return positions;
        }
        return findPositions(basePieces, targetPieces, rank, file);
    }

    private List<Position> makeTwoLeftDown(final Pieces basePieces, final Pieces targetPieces,
                                           final Position position) {
        List<Position> positions = new ArrayList<>();
        int rank = position.getRank().getValue() + TWO_LEFT_DOWN.getRow();
        int file = position.getFile().getValue() + TWO_LEFT_DOWN.getColumn();
        if (rank < 1 || file < 1) {
            return positions;
        }
        return findPositions(basePieces, targetPieces, rank, file);
    }

    private List<Position> makeTwoRightUp(final Pieces basePieces, final Pieces targetPieces,
                                          final Position position) {
        int rank = position.getRank().getValue() + TWO_RIGHT_UP.getRow();
        int file = position.getFile().getValue() + TWO_RIGHT_UP.getColumn();
        if (rank > 8 || file > 8) {
            return Collections.emptyList();
        }
        return findPositions(basePieces, targetPieces, rank, file);
    }

    private List<Position> makeTwoRightDown(final Pieces basePieces, final Pieces targetPieces,
                                            final Position position) {
        int rank = position.getRank().getValue() + TWO_RIGHT_DOWN.getRow();
        int file = position.getFile().getValue() + TWO_RIGHT_DOWN.getColumn();
        if (rank < 1 || file > 8) {
            return Collections.emptyList();
        }
        return findPositions(basePieces, targetPieces, rank, file);
    }
}
