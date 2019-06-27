package model.board;

import model.game.Color;
import model.piece.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Board {
    public static final int WIDTH = 8;

    private final List<Piece> pieces;
    private Optional<Piece> cache;

    public Board() {
        this.pieces = BoardInitializer.newBoard();
        this.cache = resetCache();
    }

    private Optional<Piece> resetCache() {
        return this.pieces.isEmpty() ? Optional.empty() : Optional.of(this.pieces.get(0));
    }

    private Optional<Integer> getIndexOfPieceAt(final int coord1D, final int begin, final int end) {
        if (begin > end) {
            return Optional.empty();
        }
        final int targetIndex = (begin + end) >> 1;
        final Piece target = this.pieces.get(targetIndex);
        if (target.get1DCoord() > coord1D) {
            return getIndexOfPieceAt(coord1D, begin, targetIndex - 1);
        }
        if (target.get1DCoord() < coord1D) {
            return getIndexOfPieceAt(coord1D, targetIndex + 1, end);
        }
        return Optional.of(targetIndex);
    }

    private Optional<Integer> getIndexOfPieceAt(final Position position) {
        return getIndexOfPieceAt(position.get1DCoord(), 0, this.pieces.size() -1);
    }

    public Optional<Piece> getPieceAt(final Position position) {
        return (position == this.cache.map(Piece::position).orElse(null))
                ? this.cache
                : getIndexOfPieceAt(position).flatMap(i -> Optional.of(this.pieces.get(i)));
    }

    public Optional<Color> getColorOfPieceAt(final Position position) {
        return getPieceAt(position).map(Piece::team);
    }

    public boolean removePieceAt(final Position position) {
        return getIndexOfPieceAt(position).map(i -> {
                this.pieces.remove(i.intValue());
                this.cache = resetCache();
                return true;
        }).orElse(false);
    }

    public boolean movePieceFromTo(final Position src, final Position dest) {
        removePieceAt(dest);
        if (getPieceAt(src).map(p -> p.move(dest)).orElse(false)) {
            Collections.sort(this.pieces);
            return true;
        }
        return false;
    }

    public <T extends Piece> boolean changeTypeOfPieceAt(final Position position, final Class<T> targetType) {
        if (targetType.equals(Piece.class) || targetType.equals(Pawn.class) || targetType.equals(King.class)) {
            return false;
        }
        return getIndexOfPieceAt(position).map(i -> {
            try {
                this.pieces.set(
                        i,
                        targetType.getConstructor(new Class[] { Piece.class }).newInstance(this.pieces.get(i))
                );
                return true;
            } catch (Exception e) {
                return false;
            }
        }).orElse(false);
    }

    public Stream<Piece> getPieces() {
        return this.pieces.stream();
    }
}