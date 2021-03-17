package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static java.util.stream.Collectors.toList;

public class BoardFactory {
    public static Board createBoard() {
        List<Piece> board = initialize();
        return new Board(board);
    }

    private static List<Piece> initialize() {
        List<Piece> board = new ArrayList<>();

        board.add(new Rook(WHITE, Position.of("a1")));
        board.add(new Knight(WHITE, Position.of("b1")));
        board.add(new Bishop(WHITE, Position.of("c1")));
        board.add(new Queen(WHITE, Position.of("d1")));
        board.add(new King(WHITE, Position.of("e1")));
        board.add(new Bishop(WHITE, Position.of("f1")));
        board.add(new Knight(WHITE, Position.of("g1")));
        board.add(new Rook(WHITE, Position.of("h1")));

        board.addAll(createWhitePawns());

        board.add(new Rook(BLACK, Position.of("a8")));
        board.add(new Knight(BLACK, Position.of("b8")));
        board.add(new Bishop(BLACK, Position.of("c8")));
        board.add(new Queen(BLACK, Position.of("d8")));
        board.add(new King(BLACK, Position.of("e8")));
        board.add(new Bishop(BLACK, Position.of("f8")));
        board.add(new Knight(BLACK, Position.of("g8")));
        board.add(new Rook(BLACK, Position.of("h8")));

        board.addAll(createBlackPawns());

        return board;
    }
    private static List<Piece> createWhitePawns() {
        return Arrays.stream(File.values())
                .map(file -> Position.of(file, Rank.TWO))
                .map(position -> new Pawn(WHITE, position))
                .collect(toList());
    }
    private static List<Piece> createBlackPawns() {
        return Arrays.stream(File.values())
                .map(file -> Position.of(file, Rank.SEVEN))
                .map(position -> new Pawn(BLACK, position))
                .collect(toList());
    }
}
