package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static chess.domain.position.Rank.SEVEN;
import static chess.domain.position.Rank.TWO;
import static java.util.stream.Collectors.toMap;

public class BoardFactory {
    public static ChessBoard createBoard() {
        Map<Position, Piece> board = initialize();
        return new ChessBoard(board);
    }

    private static Map<Position, Piece> initialize() {
        Map<Position, Piece> board = Rank.getBlankRanks().stream()
                .flatMap(rank ->
                        Arrays.stream(File.values())
                                .map(file -> Position.of(file, rank))
                )
                .collect(toMap(Function.identity(), position -> (new Blank())));

        board.put(Position.of("a1"), new Rook(WHITE));
        board.put(Position.of("b1"), new Knight(WHITE));
        board.put(Position.of("c1"), new Bishop(WHITE));
        board.put(Position.of("d1"), new Queen(WHITE));
        board.put(Position.of("e1"), new King(WHITE));
        board.put(Position.of("f1"), new Bishop(WHITE));
        board.put(Position.of("g1"), new Knight(WHITE));
        board.put(Position.of("h1"), new Rook(WHITE));

        board.putAll(createPawns(TWO, WHITE));

        board.put(Position.of("a8"), (new Rook(BLACK)));
        board.put(Position.of("b8"), (new Knight(BLACK)));
        board.put(Position.of("c8"), (new Bishop(BLACK)));
        board.put(Position.of("d8"), (new Queen(BLACK)));
        board.put(Position.of("e8"), (new King(BLACK)));
        board.put(Position.of("f8"), (new Bishop(BLACK)));
        board.put(Position.of("g8"), (new Knight(BLACK)));
        board.put(Position.of("h8"), (new Rook(BLACK)));

        board.putAll(createPawns(SEVEN, BLACK));

        return board;
    }

    private static Map<Position, Piece> createPawns(Rank rank, Color color) {
        return Arrays.stream(File.values())
                .map(file -> Position.of(file, rank))
                .collect(toMap(Function.identity(), position -> (new Pawn(color))));
    }
}

