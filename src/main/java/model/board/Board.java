package model.board;

import model.game.Player;
import model.piece.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
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
                            .collect(Collectors.toList());
        Collections.sort(this.pieces);
    }

    private Stream<Piece> generatePawns(Player owner) {
        final int y = (owner == Player.WHITE) ? WHITE_PAWN_LINE : BLACK_PAWN_LINE;
        return horizontalCoords().stream()
                                .map(x -> new Pawn(owner, Position.of(x + y)));
    }

    private List<String> horizontalCoords() {
        return IntStream.range('a', 'a' + WIDTH)
                .mapToObj(i -> String.valueOf((char) i))
                .collect(Collectors.toList());
    }

    private Stream<Piece> generateOtherPieces(Player owner) {
        final List<String> x = horizontalCoords();
        final int y = (owner == Player.WHITE) ? WHITE_BASE_LINE : BLACK_BASE_LINE;
        final List<Function<Position, Piece>> constructors = otherPiecesConstructors(owner);
        return IntStream.range(0, WIDTH)
                        .mapToObj(i -> constructors.get(i).apply(Position.of(x.get(i) + y)));
    }

    private List<Function<Position, Piece>> otherPiecesConstructors(Player owner) {
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

    private Optional<Integer> getIndexOfAPieceAt(int coord1D, int begin, int end) {
        if (begin > end) {
            return Optional.empty();
        }
        final int targetIndex = (begin + end) >> 1;
        final Piece target = this.pieces.get(targetIndex);
        if (target.get1DCoord() > coord1D) {
            return getIndexOfAPieceAt(coord1D, begin, targetIndex - 1);
        }
        if (target.get1DCoord() < coord1D) {
            return getIndexOfAPieceAt(coord1D, targetIndex + 1, end);
        }
        return Optional.of(targetIndex);
    }

    private Optional<Integer> getIndexOfAPieceAt(Position position) {
        return getIndexOfAPieceAt(position.get1DCoord(), 0, this.pieces.size() -1);
    }

    public Optional<Piece> getPieceAt(Position position) {
        Optional<Integer> idx = getIndexOfAPieceAt(position);
        return idx.flatMap(i -> Optional.of(this.pieces.get(i)));
    }

    public Optional<Player> getColorOfPieceAt(Position position) {
        return getPieceAt(position).map(Piece::team);
    }

    public boolean removePieceAt(Position position) {
        Optional<Integer> idx = getIndexOfAPieceAt(position);
        return idx.map(i -> {
            this.pieces.remove(i.intValue());
            return true;
        }).orElse(false);
    }

    public boolean movePieceFromTo(Position from, Position to) {
        removePieceAt(to);
        if (getPieceAt(from).map(p -> p.move(to)).orElse(false)) {
            Collections.sort(this.pieces);
            return true;
        }
        return false;
    }

    public <T extends Piece> boolean changeTypeOfPieceAt(Position position, Class<T> targetType)
            throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        if (targetType == Piece.class || targetType == Pawn.class || targetType == King.class) {
            return false;
        }
        Optional<Integer> idx = getIndexOfAPieceAt(position);
        if (idx.isPresent()) {
            this.pieces.set(
                    idx.get(),
                    targetType.getConstructor(new Class[] { Piece.class }).newInstance(this.pieces.get(idx.get()))
            );
            return true;
        }
        return false;
    }

    public Stream<Piece> getPieces() {
        return this.pieces.stream();
    }
}