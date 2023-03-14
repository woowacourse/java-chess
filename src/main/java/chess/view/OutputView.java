package chess.view;

import chess.domain.board.position.PiecePosition;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Staunton;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

    private static final Map<Staunton, String> stauntonFormat = new HashMap<>();

    static {
        stauntonFormat.put(Staunton.ROOK, "r");
        stauntonFormat.put(Staunton.PAWN, "p");
        stauntonFormat.put(Staunton.BISHOP, "b");
        stauntonFormat.put(Staunton.KING, "k");
        stauntonFormat.put(Staunton.KNIGHT, "n");
        stauntonFormat.put(Staunton.QUEEN, "q");
    }

    public static void showBoard(Map<PiecePosition, Piece> chessBoard) {
        for (int rank = 8; rank >= 1; rank--) {
            System.out.println(makeLineFormat(chessBoard, rank));
        }
    }

    private static String makeLineFormat(final Map<PiecePosition, Piece> chessBoard, final int rank) {
        return IntStream.rangeClosed('a', 'h')
                .mapToObj(file -> makeFormat(chessBoard, rank, (char) file))
                .collect(Collectors.joining(""));
    }

    private static String makeFormat(final Map<PiecePosition, Piece> chessBoard, final int rank, final char file) {
        return Optional.ofNullable(chessBoard.get(PiecePosition.of(rank, file)))
                .map(OutputView::convertCaseAccordingToColor)
                .orElse(".");
    }

    private static String convertCaseAccordingToColor(final Piece piece) {
        String format = stauntonFormat.get(piece.staunton());
        if (piece.color() == Color.BLACK) {
            format = format.toUpperCase();
        }
        return format;
    }
}
