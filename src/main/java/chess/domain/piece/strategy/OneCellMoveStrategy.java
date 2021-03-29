package chess.domain.piece.strategy;

import chess.domain.piece.Direction;
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
        return findPositions(basePieces, targetPieces, position, UP);
    }

    protected List<Position> makeDownRoutes(final Pieces basePieces, final Pieces targetPieces,
                                            final Position position) {
        return findPositions(basePieces, targetPieces, position, DOWN);
    }

    protected List<Position> makeLeftRoutes(final Pieces basePieces, final Pieces targetPieces,
                                            final Position position) {
        return findPositions(basePieces, targetPieces, position, LEFT);
    }

    protected List<Position> makeLeftUpRoutes(final Pieces basePieces, final Pieces targetPieces,
                                              final Position position) {
        return findPositions(basePieces, targetPieces, position, LEFT_UP);
    }

    protected List<Position> makeLeftDownRoutes(final Pieces basePieces, final Pieces targetPieces,
                                                final Position position) {
        return findPositions(basePieces, targetPieces, position, LEFT_DOWN);
    }

    protected List<Position> makeRightRoutes(final Pieces basePieces, final Pieces targetPieces,
                                             final Position position) {
        return findPositions(basePieces, targetPieces, position, RIGHT);
    }

    protected List<Position> makeRightUpRoutes(final Pieces basePieces, final Pieces targetPieces,
                                               final Position position) {
        return findPositions(basePieces, targetPieces, position, RIGHT_UP);
    }

    protected List<Position> makeRightDownRoutes(final Pieces basePieces, final Pieces targetPieces,
                                                 final Position position) {
        return findPositions(basePieces, targetPieces, position, RIGHT_DOWN);
    }

    public List<Position> findPositions(final Pieces basePieces, final Pieces targetPieces,
                                        final Position position, final Direction direction) {
        Position nextPosition = position.findNextPosition(direction);
        return findPositionByNextPosition(basePieces, targetPieces, nextPosition);
    }

    private List<Position> findPositionByNextPosition(final Pieces basePieces, final Pieces targetPieces,
                                                      final Position nextPosition) {
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

    public List<Position> findPositions(final Pieces basePieces, final Pieces targetPieces,
                                        final Position position, final Direction direction, final int index) {
        Position nextPosition = position.findNextPosition(direction, index);
        return findPositionByNextPawnPosition(basePieces, targetPieces, nextPosition);
    }

    private List<Position> findPositionByNextPawnPosition(final Pieces basePieces, final Pieces targetPieces,
                                                          final Position nextPosition) {
        Optional<Piece> basePiece = findPiece(basePieces, nextPosition);
        Optional<Piece> targetPiece = findPiece(targetPieces, nextPosition);
        if (targetPiece.isPresent()) {
            return Collections.emptyList();
        }
        if (!basePiece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    abstract public List<Position> makeRoutes(final Pieces basePieces, final Pieces targetPieces,
                                              final Position position);
}
