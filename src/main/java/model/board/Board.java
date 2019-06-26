package model.board;

import model.game.Player;
import model.piece.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Board {
    public static final int WIDTH = 8;

    private static final int WHITE_BASE_LINE = 1;
    private static final int WHITE_PAWN_LINE = 2;
    private static final int BLACK_PAWN_LINE = 7;
    private static final int BLACK_BASE_LINE = 8;

    private final List<Piece> pieces;

    public Board() {
        this.pieces = Stream.of(Player.values())
                            .map(p -> Stream.concat(generatePawns(p), generateOtherPieces(p)))
                            .reduce(Stream::concat)
                            .get()
                            .sorted()
                            .collect(Collectors.toList());
    }

    private Stream<Piece> generatePawns(final Player owner) {
        final int y = (owner == Player.WHITE) ? WHITE_PAWN_LINE : BLACK_PAWN_LINE;
        return initialHorizontalCoordsOfPieces().stream()
                                                .map(x -> new Pawn(owner, Position.of(x + y)));
    }

    private Stream<Piece> generateOtherPieces(final Player owner) {
        final List<String> x = initialHorizontalCoordsOfPieces();
        final int y = (owner == Player.WHITE) ? WHITE_BASE_LINE : BLACK_BASE_LINE;
        final List<Function<Position, Piece>> constructors = otherPiecesConstructors(owner);
        return IntStream.range(0, WIDTH)
                        .mapToObj(i -> constructors.get(i).apply(Position.of(x.get(i) + y)));
    }

    private List<String> initialHorizontalCoordsOfPieces() {
        return IntStream.range('a', 'a' + WIDTH)
                        .mapToObj(i -> String.valueOf((char) i))
                        .collect(Collectors.toList());
    }

    private List<Function<Position, Piece>> otherPiecesConstructors(final Player owner) {
        return Arrays.asList(
                pos -> new Rook(owner, pos),
                pos -> new Knight(owner, pos),
                pos -> new Bishop(owner, pos),
                pos -> new Queen(owner, pos),
                pos -> new King(owner, pos),
                pos -> new Bishop(owner, pos),
                pos -> new Knight(owner, pos),
                pos -> new Rook(owner, pos)
        );
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
        return getIndexOfPieceAt(position).flatMap(i -> Optional.of(this.pieces.get(i)));
    }

    public Optional<Player> getColorOfPieceAt(final Position position) {
        return getPieceAt(position).map(Piece::team);
    }

    public boolean removePieceAt(final Position position) {
        return getIndexOfPieceAt(position).map(i -> {
                                                this.pieces.remove(i.intValue());
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