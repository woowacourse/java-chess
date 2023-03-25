package chess.domain.board;

import chess.domain.game.Camp;
import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class BoardFactory {
    private static final int BOARD_LINE_SIZE = 8;

    private BoardFactory() {
    }

    public static Map<Square, Piece> createBoard() {
        final Map<Square, Piece> board = new LinkedHashMap<>();
        final List<Square> squares = createSquares();
        final List<Piece> pieces = createPieces(squares);

        for (int i = 0; i < squares.size(); i++) {
            board.put(squares.get(i), pieces.get(i));
        }

        return board;
    }

    private static List<Square> createSquares() {
        final List<Rank> ranks = Arrays.asList(Rank.values()).subList(0, 8);

        return ranks.stream()
                .flatMap(BoardFactory::createFilesByRank)
                .collect(Collectors.toList());
    }

    private static Stream<Square> createFilesByRank(final Rank rank) {
        final List<File> files = Arrays.asList(File.values()).subList(0, 8);

        return files.stream()
                .map(file -> new Square(file, rank));
    }

    private static List<Piece> createPieces(final List<Square> squares) {
        final List<Piece> pieces = new ArrayList<>();

        pieces.addAll(createFirstLine(Camp.BLACK, squares.subList(0, 8)));
        pieces.addAll(createSecondLine(Camp.BLACK, squares.subList(8, 16)));
        pieces.addAll(createEmptyLine(squares.subList(16, 24)));
        pieces.addAll(createEmptyLine(squares.subList(24, 32)));
        pieces.addAll(createEmptyLine(squares.subList(32, 40)));
        pieces.addAll(createEmptyLine(squares.subList(40, 48)));
        pieces.addAll(createSecondLine(Camp.WHITE, squares.subList(48, 56)));
        pieces.addAll(createFirstLine(Camp.WHITE, squares.subList(56, 64)));

        return pieces;
    }

    private static List<Piece> createFirstLine(final Camp camp, final List<Square> squares) {
        return List.of(
                new Rook(camp, squares.get(0)),
                new Knight(camp, squares.get(1)),
                new Bishop(camp, squares.get(2)),
                new Queen(camp, squares.get(3)),
                new King(camp, squares.get(4)),
                new Bishop(camp, squares.get(5)),
                new Knight(camp, squares.get(6)),
                new Rook(camp, squares.get(7))
        );
    }

    private static List<Piece> createSecondLine(final Camp camp, final List<Square> squares) {
        final List<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < BOARD_LINE_SIZE; i++) {
            pieces.add(new Pawn(camp, squares.get(i)));
        }

        return pieces;
    }

    private static List<Piece> createEmptyLine(final List<Square> squares) {
        final List<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < BOARD_LINE_SIZE; i++) {
            pieces.add(new Empty(squares.get(i)));
        }

        return pieces;
    }
}
