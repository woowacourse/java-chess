package chess.view;

import static java.util.stream.Collectors.toList;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEXT_LINE = System.lineSeparator();
    private static final Map<PieceType, String> SYMBOLS;
    private static final List<File> FILES;

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
        FILES = Arrays.stream(File.values())
                .collect(toList());
        Collections.reverse(FILES);
    }

    public void printBoard(final Map<Position, Piece> board) {
        final String result = FILES.stream()
                .map(file -> generatePieceSymbols(board, file))
                .collect(Collectors.joining(NEXT_LINE));
        System.out.println(result);
    }

    private String generatePieceSymbols(final Map<Position, Piece> board, final File file) {
        return Arrays.stream(Rank.values())
                .map(rank -> Position.of(rank, file))
                .map(position -> generatePieceSymbol(board.get(position)))
                .collect(Collectors.joining());
    }

    private String generatePieceSymbol(final Piece piece) {
        final String result = SYMBOLS.get(piece.type());
        if (piece.color() == Color.WHITE) {
            return result.toLowerCase();
        }
        return result;
    }
}
