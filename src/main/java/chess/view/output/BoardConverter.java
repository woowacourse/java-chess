package chess.view.output;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardConverter {
    private static final String NEXT_LINE = System.lineSeparator();
    private static final Map<PieceType, String> SYMBOLS;

    static {
        SYMBOLS = Map.of(
                PieceType.PAWN, "P",
                PieceType.KNIGHT, "N",
                PieceType.BISHOP, "B",
                PieceType.ROOK, "R",
                PieceType.QUEEN, "Q",
                PieceType.KING, "K",
                PieceType.EMPTY, "."
        );
    }

    private BoardConverter() {
    }

    public static String convert(final Map<Position, Piece> board) {
        final String result = Arrays.stream(Rank.values())
                .sorted(Comparator.reverseOrder())
                .map(rank -> generatePieceSymbols(board, rank))
                .collect(Collectors.joining(NEXT_LINE));
        return result + NEXT_LINE;
    }

    private static String generatePieceSymbols(final Map<Position, Piece> board, final Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> Position.of(file, rank))
                .map(position -> generatePieceSymbol(board.get(position)))
                .collect(Collectors.joining());
    }

    private static String generatePieceSymbol(final Piece piece) {
        final String result = SYMBOLS.get(piece.type());
        if (piece.isSameColor(Color.WHITE)) {
            return result.toLowerCase();
        }
        return result;
    }
}
