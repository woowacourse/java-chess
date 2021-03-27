package chess.domain.piece.strategy;

import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        int rank = position.getRank().getValue() + DOWN.getRow();
        int file = position.getFile().getValue() + DOWN.getColumn();
        for (int index = 0; index < range; index++) {
            Position nextPosition = findNextPosition(rank - index, file);
            Optional<Piece> basePiece = findPiece(basePieces, nextPosition);
            Optional<Piece> targetPiece = findPiece(targetPieces, nextPosition);
            if (targetPiece.isPresent()) {
                break;
            }
            if (!basePiece.isPresent()) {
                positions.add(nextPosition);
                continue;
            }
            break;
        }
        return positions;
    }

    private List<Position> makeDownRightRoutes(final Pieces basePieces, final Pieces targetPieces,
                                               final Position position) {
        int rank = position.getRank().getValue() + RIGHT_DOWN.getRow();
        int file = position.getFile().getValue() + RIGHT_DOWN.getColumn();
        return findPositions(basePieces, targetPieces, rank, file);
    }

    private List<Position> makeDownLeftRoutes(final Pieces basePieces, final Pieces targetPieces,
                                              final Position position) {
        int rank = position.getRank().getValue() + LEFT_DOWN.getRow();
        int file = position.getFile().getValue() + LEFT_DOWN.getColumn();
        return findPositions(basePieces, targetPieces, rank, file);
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
        int rank = position.getRank().getValue() + UP.getRow();
        int file = position.getFile().getValue() + UP.getColumn();
        for (int index = 0; index < range; index++) {
            Position nextPosition = findNextPosition(rank + index, file);
            Optional<Piece> basePiece = findPiece(basePieces, nextPosition);
            Optional<Piece> targetPiece = findPiece(targetPieces, nextPosition);
            if (targetPiece.isPresent()) {
                break;
            }
            if (!basePiece.isPresent()) {
                positions.add(nextPosition);
                continue;
            }
            break;
        }
        return positions;
    }

    private List<Position> makeUpRightRoutes(final Pieces basePieces, final Pieces targetPieces,
                                             final Position position) {
        int rank = position.getRank().getValue() + RIGHT_UP.getRow();
        int file = position.getFile().getValue() + RIGHT_UP.getColumn();
        return findPositions(basePieces, targetPieces, rank, file);
    }

    private List<Position> makeUpLeftRoutes(final Pieces basePieces, final Pieces targetPieces,
                                            final Position position) {
        int rank = position.getRank().getValue() + LEFT_UP.getRow();
        int file = position.getFile().getValue() + LEFT_UP.getColumn();
        return findPositions(basePieces, targetPieces, rank, file);
    }
}
