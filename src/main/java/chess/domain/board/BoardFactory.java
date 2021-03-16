package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public class BoardFactory {
    public static Board createBoard() {
        Map<Position, Piece> board = initialize();
        return new Board(board);
    }

    private static Map<Position, Piece> initialize() {
        Map<Position, Piece> board = Rank.getBlankRanks().stream()
                .flatMap(rank ->
                        Arrays.stream(File.values())
                                .map(file -> Position.of(file, rank))
                )
                .collect(toMap(Function.identity(), position -> new Blank()));

        board.put(Position.of("a1"), new Rook(Color.WHITE));
        board.put(Position.of("b1"), new Knight(Color.WHITE));
        board.put(Position.of("c1"), new Bishop(Color.WHITE));
        board.put(Position.of("d1"), new Queen(Color.WHITE));
        board.put(Position.of("e1"), new King(Color.WHITE));
        board.put(Position.of("f1"), new Bishop(Color.WHITE));
        board.put(Position.of("g1"), new Knight(Color.WHITE));
        board.put(Position.of("h1"), new Rook(Color.WHITE));

        board.putAll(createWhitePawns());

        board.put(Position.of("a8"), new Rook(Color.BLACK));
        board.put(Position.of("b8"), new Knight(Color.BLACK));
        board.put(Position.of("c8"), new Bishop(Color.BLACK));
        board.put(Position.of("d8"), new Queen(Color.BLACK));
        board.put(Position.of("e8"), new King(Color.BLACK));
        board.put(Position.of("f8"), new Bishop(Color.BLACK));
        board.put(Position.of("g8"), new Knight(Color.BLACK));
        board.put(Position.of("h8"), new Rook(Color.BLACK));

        board.putAll(createBlackPawns());

        return board;
    }

    private static Map<Position, Piece> createWhitePawns() {
        return Arrays.stream(File.values())
                .map(file -> Position.of(file, Rank.TWO))
                .collect(toMap(Function.identity(), position -> new Pawn(Color.WHITE)));
    }

    private static Map<Position, Piece> createBlackPawns() {
        return Arrays.stream(File.values())
                .map(file -> Position.of(file, Rank.SEVEN))
                .collect(toMap(Function.identity(), position -> new Pawn(Color.BLACK)));
    }
}
