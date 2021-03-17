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

        board.add(new Rook(Position.of("a1"), WHITE));
        board.add(new Knight(Position.of("b1"), WHITE));
        board.add(new Bishop(Position.of("c1"), WHITE));
        board.add(new Queen(Position.of("d1"), WHITE));
        board.add(new King(Position.of("e1"), WHITE));
        board.add(new Bishop(Position.of("f1"), WHITE));
        board.add(new Knight(Position.of("g1"), WHITE));
        board.add(new Rook(Position.of("h1"), WHITE));

        board.addAll(createWhitePawns());

        board.add(new Rook(Position.of("a8"), BLACK));
        board.add(new Knight(Position.of("b8"), BLACK));
        board.add(new Bishop(Position.of("c8"), BLACK));
        board.add(new Queen(Position.of("d8"), BLACK));
        board.add(new King(Position.of("e8"), BLACK));
        board.add(new Bishop(Position.of("f8"), BLACK));
        board.add(new Knight(Position.of("g8"), BLACK));
        board.add(new Rook(Position.of("h8"), BLACK));

        board.addAll(createBlackPawns());
        board.addAll(createEmptyPieces());

        return board;
    }

    private static List<Piece> createEmptyPieces() {
        return Rank.getBlankRanks().stream()
                .flatMap(rank -> Arrays.stream(File.values())
                        .map(file -> Position.of(file, rank)))
                .map(Blank::new)
                .collect(toList());
    }

    private static List<Piece> createWhitePawns() {
        return Arrays.stream(File.values())
                .map(file -> Position.of(file, Rank.TWO))
                .map(position -> new Pawn(position, WHITE))
                .collect(toList());
    }

    private static List<Piece> createBlackPawns() {
        return Arrays.stream(File.values())
                .map(file -> Position.of(file, Rank.SEVEN))
                .map(position -> new Pawn(position, BLACK))
                .collect(toList());
    }
}
