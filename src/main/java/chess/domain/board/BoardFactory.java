package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Camp;
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

    public static Map<Square, Piece> createBoard() {
        final LinkedHashMap<Square, Piece> board = new LinkedHashMap<>();
        final List<Square> squares = createSquares();
        final List<Piece> pieces = createPieces();

        for (int i = 0; i < squares.size(); i++) {
            board.put(squares.get(i), pieces.get(i));
        }

        return board;
    }

    private static List<Square> createSquares() {
        return Arrays.stream(Rank.values())
                .flatMap(BoardFactory::createFilesByRank)
                .collect(Collectors.toList());
    }

    private static Stream<Square> createFilesByRank(final Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> new Square(file, rank));
    }

    private static List<Piece> createPieces() {
        final List<Piece> pieces = new ArrayList<>();

        pieces.addAll(createFirstLine(Camp.BLACK));
        pieces.addAll(createSecondLine(Camp.BLACK));
        pieces.addAll(createEmptyLine());
        pieces.addAll(createEmptyLine());
        pieces.addAll(createEmptyLine());
        pieces.addAll(createEmptyLine());
        pieces.addAll(createSecondLine(Camp.WHITE));
        pieces.addAll(createFirstLine(Camp.WHITE));

        return pieces;
    }

    private static List<Piece> createFirstLine(final Camp camp) {
        final List<Piece> pieces = new ArrayList<>();

        pieces.add(new Rook(camp));
        pieces.add(new Knight(camp));
        pieces.add(new Bishop(camp));
        pieces.add(new Queen(camp));
        pieces.add(new King(camp));
        pieces.add(new Bishop(camp));
        pieces.add(new Knight(camp));
        pieces.add(new Rook(camp));

        return pieces;
    }

    private static List<Piece> createSecondLine(final Camp camp) {
        final List<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < BOARD_LINE_SIZE; i++) {
            pieces.add(new Pawn(camp));
        }

        return pieces;
    }

    private static List<Piece> createEmptyLine() {
        final List<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < BOARD_LINE_SIZE; i++) {
            pieces.add(Empty.of());
        }

        return pieces;
    }
}
