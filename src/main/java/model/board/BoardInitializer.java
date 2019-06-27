package model.board;

import model.game.Color;
import model.piece.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BoardInitializer {
    private static final int WHITE_BASE_LINE = 1;
    private static final int WHITE_PAWN_LINE = 2;
    private static final int BLACK_PAWN_LINE = 7;
    private static final int BLACK_BASE_LINE = 8;

    public static List<Piece> newBoard() {
        return Stream.concat(initWhitePieces(), initBlackPieces())
                    .sorted()
                    .collect(Collectors.toList());
    }

    private static Stream<Piece> initWhitePieces() {
        return Stream.concat(generatePawns(Color.WHITE), generateOtherPieces(Color.WHITE));
    }

    private static Stream<Piece> initBlackPieces() {
        return Stream.concat(generatePawns(Color.BLACK), generateOtherPieces(Color.BLACK));
    }


    private static Stream<Piece> generatePawns(final Color owner) {
        final int y = (owner == Color.WHITE) ? WHITE_PAWN_LINE : BLACK_PAWN_LINE;
        return IntStream.range(0, Board.WIDTH)
                        .mapToObj(i -> new Pawn(owner, Position.of(Coord.of(i), Coord.ofY(y))));
    }

    private static Stream<Piece> generateOtherPieces(final Color owner) {
        final int y = (owner == Color.WHITE) ? WHITE_BASE_LINE : BLACK_BASE_LINE;
        final List<Function<Position, Piece>> constructors = otherPiecesConstructors(owner);
        return IntStream.range(0, Board.WIDTH)
                        .mapToObj(i -> constructors.get(i).apply(Position.of(Coord.of(i), Coord.ofY(y))));
    }

    private static List<Function<Position, Piece>> otherPiecesConstructors(final Color owner) {
        return Stream.of(
                (Function<Color, Function<Position, Piece>>)
                player -> pos -> new Rook(player, pos),
                player -> pos -> new Knight(player, pos),
                player -> pos -> new Bishop(player, pos),
                player -> pos -> new Queen(player, pos),
                player -> pos -> new King(player, pos),
                player -> pos -> new Bishop(player, pos),
                player -> pos -> new Knight(player, pos),
                player -> pos -> new Rook(player, pos)
        ).map(f -> f.apply(owner))
        .collect(Collectors.toList());
    }
}