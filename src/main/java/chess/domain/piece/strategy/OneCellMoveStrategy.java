package chess.domain.piece.strategy;

import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static chess.domain.piece.Direction.*;

public abstract class OneCellMoveStrategy implements MoveStrategy {
    protected final List<Position> positions = new ArrayList<>();

    protected List<Position> makeUpRoutes(final Pieces basePieces, final Pieces targetPieces,
                                          final Position position) {
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        return findPositions(basePieces, targetPieces,
                rank + UP.getRow(), file + UP.getColumn());
    }

    protected List<Position> makeDownRoutes(final Pieces basePieces, final Pieces targetPieces,
                                            final Position position) {
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        return findPositions(basePieces, targetPieces,
                rank + DOWN.getRow(), file + DOWN.getColumn());
    }

    protected List<Position> makeLeftRoutes(final Pieces basePieces, final Pieces targetPieces,
                                            final Position position) {
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        return findPositions(basePieces, targetPieces,
                rank + LEFT.getRow(), file + LEFT.getColumn());
    }

    protected List<Position> makeLeftUpRoutes(final Pieces basePieces, final Pieces targetPieces,
                                              final Position position) {
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        return findPositions(basePieces, targetPieces,
                rank + LEFT_UP.getRow(), file + LEFT_UP.getColumn());
    }

    protected List<Position> makeLeftDownRoutes(final Pieces basePieces, final Pieces targetPieces,
                                                final Position position) {
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        return findPositions(basePieces, targetPieces,
                rank + LEFT_DOWN.getRow(), file + LEFT_DOWN.getColumn());
    }

    protected List<Position> makeRightRoutes(final Pieces basePieces, final Pieces targetPieces,
                                             final Position position) {
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        return findPositions(basePieces, targetPieces,
                rank + RIGHT.getRow(), file + RIGHT.getColumn());
    }

    protected List<Position> makeRightUpRoutes(final Pieces basePieces, final Pieces targetPieces,
                                               final Position position) {
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        return findPositions(basePieces, targetPieces,
                rank + RIGHT_UP.getRow(), file + RIGHT_UP.getColumn());
    }

    protected List<Position> makeRightDownRoutes(final Pieces basePieces, final Pieces targetPieces,
                                                 final Position position) {
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        return findPositions(basePieces, targetPieces,
                rank + RIGHT_DOWN.getRow(), file + RIGHT_DOWN.getColumn());
    }

    public List<Position> findPositions(final Pieces basePieces, final Pieces targetPieces,
                                        final int rank, final int file) {
        Position nextPosition = Position.valueOf(rank, file);
        Optional<Piece> basePiece = findPiece(basePieces, nextPosition);
        Optional<Piece> targetPiece = findPiece(targetPieces, nextPosition);
        if (targetPiece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        if (!basePiece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    abstract public List<Position> makeRoutes(final Pieces basePieces, final Pieces targetPieces,
                                              final Position position);
}
